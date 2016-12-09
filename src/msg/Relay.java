package msg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.StringTokenizer;

import shared.protocols.ConnectCheck;
import shared.protocols.Message;
import shared.protocols.Regist;

public class Relay implements Serializable {
	static final long serialVersionUID = 3518731767529258119L;
	static int relayPort = 6666;
	static ServerSocket relaySocket = null;
	

	/**
	 * @param args
	 * @throws SocketException 
	 */
	public static void main(String[] args) {
		boolean ip = false;
		try {
			// get server ip
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address ) {
	                	// print Server IP
	                	System.out.println("Server IP is : " + inetAddress.getHostAddress().toString() + "\n");
	                	ip = true;
	                	break;
	                }
	            }
	            if (ip == true) break;
	        }
		} catch (SocketException ex) {

		}
		
		
		try {
			// 서버 소켓이 하프 오픈 상태에서 대기
			relaySocket = new ServerSocket(relayPort);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Relay: Server Socket Exception");
			e.printStackTrace();
		}

				
		// wait for a request from a message client (MsgReceiver/MsgSender)
		// a register request from a MsgReceiver
		// or a message relay request from a MsgSender

		// there could be a lot of alternative designs/implementations
		// here is a simple but ineffective one

		// allocate a thread, which  will process successive requests 
		// a register request is not successive  -> the thread may be terminated 
		
		SharedUserTable users = new SharedUserTable();
		Socket requestSocket = null;
		ProcessRequest request = null;
		UserTableManageMent manage = null;
		try {
			manage = new UserTableManageMent(users);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread Mthread = new Thread(manage);
		Mthread.start();
		
		while (true) {				
			try {
				requestSocket = relaySocket.accept();			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Construct an object to process a request message.
			try {
				request = new ProcessRequest(users, requestSocket);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Create a new thread to process the request.
			Thread Pthread = new Thread(request);
			// Start the thread.
			Pthread.start();
		
		}							
	}
		
	private static void printHelp() {
		System.err.println("Relay [ <relay port> ] ");
		System.err.println("where the default port is 6666");		
	}	
}	


class SharedUserTable {
	UserTable users = new UserTable();
	
}


// this thread is client's individual thread
// receive client msg, send msg to another client, and update user info and state
final class ProcessRequest implements Runnable {
	//static UserTable users = new UserTable(); // shared by all threads
	SharedUserTable usertable;
	final static String CRLF = "\r\n";
	
	private Socket socket;
	private ObjectInputStream rInFromClient;	
	private ObjectOutputStream rOutToClient;

	// Constructor
	public ProcessRequest(SharedUserTable users, Socket socket) throws Exception {
		this.usertable = users;
		this.socket = socket;
		//this.socket.setSoTimeout(10*1000);
	}

	// Implement the run() method of the Runnable interface.
	public void run() {
		try {
			processRequest();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void processRequest() throws Exception {
		Message msg = new Message();
		Regist reg = new Regist();
		ConnectCheck conn = new ConnectCheck();
		String clientMsg = "", str = "", myClient = "";
		StringTokenizer st;
		
		// add ghost client for UserTable management
		//usertable.users.addUserDetail("Client1", null, 2, null, null);
		//usertable.users.addUserDetail("Client2", null, 2, null, null);
		//usertable.users.addUserDetail("Client3", null, 2, null, null);
		//usertable.users.addUserDetail("Client4", null, 2, null, null);
		
		try {
			// read a message from the client
			rInFromClient = new ObjectInputStream(this.socket.getInputStream());
			rOutToClient = new ObjectOutputStream(this.socket.getOutputStream());
//			rOutToClient.writeObject(reg);
			clientMsg = (String)rInFromClient.readObject();
			st = new StringTokenizer(clientMsg, CRLF);
			str = st.nextToken();
			

			if (Integer.parseInt(str) == 0) {
				// convert String type to Regist type
				reg.decodeRegist(clientMsg);
				
				// : 로그인 체크 <-- 하지말자..
				
//				User adduser = new User(reg.getSourceID(), reg.getSourceIP(), reg.getSourcePort(), this.socket, rOutToClient);
//				User adduser = new User(reg.getSourceIP(), reg.getSourcePort(), this.socket, rOutToClient, reg.getSourceID(),
//						reg.getSourcePW(), "default name", "default school", "defualt department", 0, "default state");
				usertable.users.addUserDetail(reg.getSourceID(), reg.getSourceIP(), reg.getSourcePort(), this.socket, rOutToClient);
				myClient = reg.getSourceID();
				
				System.out.println("Add user: " + reg.getSourceID() + ", " + reg.getSourceIP());
				System.out.println("---- UserTable ----");
				usertable.users.printTable();
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
         		
		while (true) {
			try {
				clientMsg = (String)rInFromClient.readObject();
				st = new StringTokenizer(clientMsg, CRLF);
				str = st.nextToken();
				
				// update users msg
				if (Integer.parseInt(str) == 1) {
					// convert String type to ConnectCheck type
					conn.decodeConnectCheck(clientMsg);
					Regist body = new Regist();
					body.decodeRegist(conn.getBody());
					usertable.users.updateUser(body.getSourceID(), body.getSourceIP(), body.getSourcePort());
					
					// And send userlist to thread's client
					// Using loop, find 0 to n client exclusive of thread's Client
					User toSend = usertable.users.findUser(conn.getSourceID());
					int index = usertable.users.getCuser();
					String str2 = "Nothing";
					if (index > 1) {
						User connectUser = usertable.users.uTable.get(0);
						// str2: all users in UserTable, exclusive of thread's Client
						if (!(connectUser.getID().equals(conn.getSourceID())))
							str2 = connectUser.getID();
						for (int i=1; i<index; i++) {
							connectUser = usertable.users.uTable.get(i);
							if (!(connectUser.getID().equals(conn.getSourceID())))
								str2 += CRLF + connectUser.getID();
						}
					}
					conn.setHead(1, "Server");
					conn.setBody(str2);
					// convert ConnectCheck type to String type
					String ServerMsg = conn.encodeConnectCheck();
					toSend.outToReceiver.writeObject(ServerMsg);
				}
				// user send msg
				else if (Integer.parseInt(str) == 2) {
					// convert String type to Message type
					msg.decodeMessge(clientMsg);
					
					// if Source Client exist in usertable 
					if (usertable.users.findUserIndex(msg.getSourceID()) != -1) {
						System.out.println("\n(Receive)From: " + msg.getSourceID() + ", To: " + msg.getDestID() + ", Message: " + msg.getBody());
						
						// broadcast msg
						if (msg.getDestID().equals("To All")) {
							System.out.println("Broadcast msg: ");
							int index = usertable.users.getCuser();
							// Using loop, send msg to all clients
							for (int i=0; i<index; i++) {
								User toSend = usertable.users.uTable.get(i);
								Message toMsg = new Message();
								toMsg.setHead(2, msg.getSourceID(), toSend.getID());
								toMsg.setBody(msg.getSourceID() + ": " + msg.getBody());
								String serverMsg = toMsg.encodeMessage();
									
								toSend.outToReceiver.writeObject(serverMsg);
								System.out.println("(Send)From: " + toMsg.getSourceID() + ", To: " + toMsg.getDestID() + ", Message: " + toMsg.getBody());
							}
						}
						// unicast msg
						else {
							System.out.println("Unicast msg: ");
							if (usertable.users.findUserIndex(msg.getDestID()) == -1) {
								// Using Dest id, find Client's ObjectOutputStream
								User toSend = new User(msg.getSourceID(), usertable.users.getReceiverSocket(msg.getSourceID()), usertable.users.getReceiverOutputStream(msg.getSourceID()));
								Message toMsg = new Message();
								toMsg.setHead(2, "Server", toSend.getID());
								toMsg.setBody("Server: " + msg.getDestID() + " is not connected.");
								// convert Message type to String type
								String serverMsg = toMsg.encodeMessage();
								
								toSend.outToReceiver.writeObject(serverMsg);
								System.out.println("(Send)From: " + toMsg.getSourceID() + ", To: " + toMsg.getDestID() + ", Message: " + toMsg.getBody());
							}
							else {
								// Using Dest id, find Client's ObjectOutputStream
								User toSend = new User(msg.getDestID(), usertable.users.getReceiverSocket(msg.getDestID()), usertable.users.getReceiverOutputStream(msg.getDestID()));
								Message toMsg = new Message();
								toMsg.setHead(2, msg.getSourceID(), toSend.getID());
								toMsg.setBody(msg.getSourceID() + ": " + msg.getBody());
								// convert Message type to String type
								String serverMsg = toMsg.encodeMessage();
								
								toSend.outToReceiver.writeObject(serverMsg);
								System.out.println("(Send)From: " + toMsg.getSourceID() + ", To: " + toMsg.getDestID() + ", Message: " + toMsg.getBody());
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {			
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
	} 
}

// this thread check user state in usertable.
// if user dose not send info msg during 5sec, then thread delete user info
class UserTableManageMent extends Thread {
	SharedUserTable usertable;
	final static String CRLF = "\r\n";
	
	public UserTableManageMent(SharedUserTable users) throws Exception {
		this.usertable = users;
		//this.socket.setSoTimeout(10*1000);
	}
	
	public void run() {
		try {
			management();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void management() {
		while (true) {
			int index = usertable.users.getCuser();
			
			for (int i=0; i<index; i++) {
				User user = new User();
				user = usertable.users.uTable.get(i);
				user.userState = false;
				usertable.users.uTable.set(i, user);
			}
			
			try {
				// timeout: 10sec
				sleep(5*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			index = usertable.users.getCuser();
			for (int i=index-1; i>=0; i--) {
				User user = new User();
				user = usertable.users.uTable.get(i);
				if (user.userState == false) {
					// if client dose not send info msg, then delete it
					System.out.println("Timeout: " + user.getID());
					System.out.println("---- UserTable ----");
					usertable.users.deleteUser(user.getID());
					usertable.users.printTable();
					System.out.println();
				}
			}
		}
		
	}
}
	



		
		
			