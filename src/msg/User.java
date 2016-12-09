package msg;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class User {

	// here is an example for user entry information
	// add or delete  as you design
	private String receiverIP = null;
	private int receiverPort = 0;
	public Socket receiverSocket = null;
	public ObjectOutputStream outToReceiver = null;
	public boolean userState = true;
	
	/* Fields */
    private String ID; // 로그인 시 필요한 아이디
    private String PW; // 로그인 시 필요한 비밀번호
    private String name; // 이름
    private String school; // 학교
    private String department; // 학과
    private int myImage; // 프로필사진 // : 근데 왜 int이지?
    private String myState; // 프로필명(상태메시지)
    public String loginResult; // 로그인시도 결과 (로그인 시도로 서버가 저장)
	
	/**
	 * Constructor
	 */
	
	public User()
	{
		
	}
	
	public User(String ID, Socket receiverSocket, ObjectOutputStream receiverStream)
	{
		this.ID = ID; 
		this.receiverSocket = receiverSocket;
		this.outToReceiver = receiverStream;
		
	}
	
	public User(String ID, String receiverIP, int receiverPort, Socket receiverSocket, ObjectOutputStream receiverStream)
	{
		this.ID = ID; 
		this.receiverIP = receiverIP;
		this.receiverPort = receiverPort;
		this.receiverSocket = receiverSocket;
		this.outToReceiver = receiverStream;
		
	}
	
	
	/* userState, loginResult는 제외 <-- 서버에서 주입해줄 예정 */
	public User(String receiverIP, int receiverPort, Socket receiverSocket, ObjectOutputStream outToReceiver, String ID,
			String PW, String name, String school, String department, int myImage, String myState) {
		this.receiverIP = receiverIP;
		this.receiverPort = receiverPort;
		this.receiverSocket = receiverSocket;
		this.outToReceiver = outToReceiver;
		this.ID = ID;
		this.PW = PW;
		this.name = name;
		this.school = school;
		this.department = department;
		this.myImage = myImage;
		this.myState = myState;
	}

	public String getID() { return ID; }
	public String getIP() { return receiverIP; }
	public int getPort() { return receiverPort; }
	public Socket getSocket() { return receiverSocket; }
	public ObjectOutputStream getOutputStream() { return outToReceiver; }	
	public boolean getUserState() { return userState; }
	public String getPW() { return this.PW; }
    public String getName() { return this.name; }
    public String getSchool() { return this.school; }
    public String getDepartment() { return this.department; }
    public int getMyImage() { return this.myImage; }
    public String getMyState() { return this.myState; }
    public String getLoginResult() { return this.loginResult; }
	
}
