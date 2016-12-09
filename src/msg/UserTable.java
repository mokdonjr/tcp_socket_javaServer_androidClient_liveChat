package msg;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserTable {
	
	List<User> uTable;
	private int nMaxUser = 10;
	private int cuser = 0; // number of users currently registered
	
	
	UserTable(){
		// use your favorite data strucure
		uTable = Collections.synchronizedList(new ArrayList<User>());
	}
	
	UserTable(int max){
		nMaxUser = max;
		uTable = Collections.synchronizedList(new ArrayList<User>(nMaxUser));
	}
	
	public int getCuser() {
		return cuser;
	}
	
	public boolean addUser(String ID, Socket receiverSocket, ObjectOutputStream receiverStream ) {
		User userInform = new User(ID, receiverSocket, receiverStream);
		
		if (cuser >= 10) return false;
		else {
			uTable.add(cuser++, userInform);
			return true;
		}
	}
	
//	public boolean addUserDetail(String ID, String receiverIP, int receiverPort, Socket receiverSocket, ObjectOutputStream receiverStream) {
//		User userInform = new User(ID, receiverIP, receiverPort, receiverSocket, receiverStream);
//		
//		if (cuser >= 10) return false;
//		else {
//			uTable.add(cuser++, userInform);
//			return true;
//		}
//	}
	public boolean addUserDetail(String ID, String receiverIP, int receiverPort, Socket receiverSocket, ObjectOutputStream receiverStream) {
		User userInform = new User(ID, receiverIP, receiverPort, receiverSocket, receiverStream);
		
		if (cuser >= 10) return false;
		else {
			uTable.add(cuser++, userInform);
			return true;
		}
	}
	
	public boolean updateUser(String ID, String receiverIP, int receiverPort) {		
		if (findUserIndex(ID) != -1) {
			User exuser = findUser(ID);
			User user = new User(ID, receiverIP, receiverPort, exuser.getSocket(), exuser.getOutputStream());
			user.userState = true;
			uTable.set(findUserIndex(user.getID()), user);
			
			return true;
		}
		else return false;
			
	}
	
	public boolean deleteUser(String ID) {
		int index;
		
		index = findUserIndex(ID);
		if (index == -1) return false;
		else {
			uTable.remove(index);
			cuser--;
			return true;
		}
	}
	
	public User findUser(String ID) {
		return uTable.get(findUserIndex(ID));
	}
	
	public int findUserIndex(String ID) {
		User findUser = null;
		boolean check = false;
		int i;
		
		for (i=0; i<cuser; i++) {
			findUser = uTable.get(i);
			if (findUser.getID().equals(ID)) {
				check = true;
				break;
			}
		}
		if (check == true) return i;
		else {i = -1; return i;}
	}
		
	public boolean isRegistered(String ID) {
		if (findUserIndex(ID) == -1) return false;
		else return true;
	}
	
	public Socket getReceiverSocket(String ID){
		User userInform = null;
		userInform = findUser(ID);
		
		return userInform.getSocket();
	}	
	
	public ObjectOutputStream getReceiverOutputStream(String ID){
		User userInform = null;
		userInform = findUser(ID);
		
		return userInform.getOutputStream();
	}
	
	void printTable() {
		User userInform = null;
		
		System.out.println("# of users: " + cuser);
		for (int i=0; i<cuser; i++) {
			userInform = uTable.get(i);
			System.out.println("(" + i + ")" + "userID: " + userInform.getID() + ", userIP: " + userInform.getIP() + ", userPort: " + userInform.getPort() + ", userSocket: " + userInform.getSocket() + ", userStream: " + userInform.getOutputStream());
		}
	}
}
