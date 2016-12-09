//package utility;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import javaServer.JavaMultiChatServer;
//import shared.Student;
//
//public class ClientLoginCheck {
//	/* Fields */
//	//private ClientLoginInfo clientLoginInfo;
//	private RegisteredStudentDB registeredStudentDB; // --> 회원가입된 회원 명단
//	private ArrayList<Student> students;
//	private JavaMultiChatServer jmServer; // jmServer.getClients() --> 서버에 접속중인 회원명단
//	
//	/* getters and setters */
//	
//	
//	/* Constructors */
//	public ClientLoginCheck(JavaMultiChatServer jmServer){
//		this.jmServer = jmServer; // 현재 접속중인 클라이언트라면 로그인 못하도록 막기 위해 jmServer의 clients를 참조
//		this.registeredStudentDB = new RegisteredStudentDB();
//		this.students = new ArrayList<Student>();
//	}
//	
//	public Student login(Student loginInfo){
//			
//		if(loginInfo == null){ //return "[CLOGINFAIL] ClientLoginInfo is empty";
//			loginInfo.setLoginResult("[CLOGINFAIL] ClientLoginInfo is empty");
//			return loginInfo;
//		}
//		students = registeredStudentDB.getRegisteredStudents();
//		
//		loginInfo.setLoginResult("[CLOGINFAIL] Not Registered ID");
//		for(int i=0; i<students.size(); i++){
//			if(students.get(i).getID().equals(loginInfo.getID())) // 1. 아이디 체크
//				if(students.get(i).getPW().equals(loginInfo.getPW())) // 2. 비밀번호 체크
//					if(isConnectedClient(jmServer.getClients(), students.get(i)) == false){ // 3. 접속중인 사용자인지 체크
//						
//						students.get(i).setLoginResult("[CLOGINSUCC]"); //return "[CLOGINSUCC]"; // 로그인 성공!
//						return students.get(i); // 로그인한 사용자에게는 사용자정보를 넘겨주자
//						
//					} 
//					else loginInfo.setLoginResult("[CLOGINFAIL] Already Connected User");
//				else loginInfo.setLoginResult("[CLOGINFAIL] Wrong PW");
//		} return loginInfo; // Student객체에 loginResult결과가 채워져 리턴
//	}
//	
//	/* Methods */
//	/* 하드코딩된 서버의 PreStudentDB내 클라이언트인지 클라이언트로부터 전달받은 ClientLoginInfo와 비교연산 수행.
//	 * 클라이언트가 작성한 ID와 PW정보가 담겨진 ClientLoginInfo에 ID,PW가 없으면 "[CLOGINFAIL] ClientLoginInfo is empty"
//	 * 클라이언트가 작성한 ID가 PreStudentDB에 존재하지 않으면 "[CLOGINFAIL] Not Registered ID"
//	 * 클라이언트가 작성한 ID가 존재하고 PW가 맞지 않다면 "[CLOGINFAIL] Wrong PW"
//	 * 로그인 성공시 "[CLOGINSUCC]" 을 리턴
//	 */
////	public String isClientInDB(ClientLoginInfo clientLoginInfo){
////		if(clientLoginInfo == null) return "[CLOGINFAIL] ClientLoginInfo is empty";
////		students = preStudentDB.getFakeStudents();
////		for(int i=0; i<students.size(); i++){
////			if(students.get(i).getID().equals(clientLoginInfo.getID())){ // 1. 아이디 체크
////				if(students.get(i).getPW().equals(clientLoginInfo.getPW())) // 2. 비밀번호 체크
////					if(isConnectedClient(jmServer.getClients(), students.get(i)) == false) // 3. 접속중인 사용자인지 체크
////						return "[CLOGINSUCC]"; // 로그인 성공!
////					else return "[CLOGINFAIL] Already Connected User";
////				else return "[CLOGINFAIL] Wrong PW";
////			}
////		}
////		return "[CLOGINFAIL] Not Registered ID";
////	}
////	
////	public Student getLoginSuccClient(ClientLoginInfo clientLoginInfo){
////		if(clientLoginInfo == null) return null;
////		students = preStudentDB.getFakeStudents();
////		for(int i=0; i<students.size(); i++){
////			if(students.get(i).getID().equals(clientLoginInfo.getID())){ // 1. 아이디 체크
////				if(students.get(i).getPW().equals(clientLoginInfo.getPW())) // 2. 비밀번호 체크
////					return students.get(i); // 로그인 성공한 회원
////				else return null;
////			}
////		}
////		return null;
////	}
//	
//	/* 현재 서버에 접속중인 클라이언트에 로그인시도한 경우 true를 반환
//	 * 접속중이지 않은 클라이언트에 로그인 시도한 경우 false 반환
//	 * ClientLoginCheck클래스 isClientInDB()에서 이 isConnectedClient()를 로그인검증 3단계로 호출 */
//	public boolean isConnectedClient(HashMap clients, Student isConnected){
//		Iterator<Student> it = clients.keySet().iterator();
//		while(it.hasNext()){
//			Student student = it.next();
//			if(isConnected.getID().equals(student.getID()))
//				return true; // 접속중인 사용자있음. 로그인 차단
//		}
//		return false; // 접속중인 사용자 없음. 로그인허용
//	}
//	
//
//}
