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
//	private RegisteredStudentDB registeredStudentDB; // --> ȸ�����Ե� ȸ�� ���
//	private ArrayList<Student> students;
//	private JavaMultiChatServer jmServer; // jmServer.getClients() --> ������ �������� ȸ�����
//	
//	/* getters and setters */
//	
//	
//	/* Constructors */
//	public ClientLoginCheck(JavaMultiChatServer jmServer){
//		this.jmServer = jmServer; // ���� �������� Ŭ���̾�Ʈ��� �α��� ���ϵ��� ���� ���� jmServer�� clients�� ����
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
//			if(students.get(i).getID().equals(loginInfo.getID())) // 1. ���̵� üũ
//				if(students.get(i).getPW().equals(loginInfo.getPW())) // 2. ��й�ȣ üũ
//					if(isConnectedClient(jmServer.getClients(), students.get(i)) == false){ // 3. �������� ��������� üũ
//						
//						students.get(i).setLoginResult("[CLOGINSUCC]"); //return "[CLOGINSUCC]"; // �α��� ����!
//						return students.get(i); // �α����� ����ڿ��Դ� ����������� �Ѱ�����
//						
//					} 
//					else loginInfo.setLoginResult("[CLOGINFAIL] Already Connected User");
//				else loginInfo.setLoginResult("[CLOGINFAIL] Wrong PW");
//		} return loginInfo; // Student��ü�� loginResult����� ä���� ����
//	}
//	
//	/* Methods */
//	/* �ϵ��ڵ��� ������ PreStudentDB�� Ŭ���̾�Ʈ���� Ŭ���̾�Ʈ�κ��� ���޹��� ClientLoginInfo�� �񱳿��� ����.
//	 * Ŭ���̾�Ʈ�� �ۼ��� ID�� PW������ ����� ClientLoginInfo�� ID,PW�� ������ "[CLOGINFAIL] ClientLoginInfo is empty"
//	 * Ŭ���̾�Ʈ�� �ۼ��� ID�� PreStudentDB�� �������� ������ "[CLOGINFAIL] Not Registered ID"
//	 * Ŭ���̾�Ʈ�� �ۼ��� ID�� �����ϰ� PW�� ���� �ʴٸ� "[CLOGINFAIL] Wrong PW"
//	 * �α��� ������ "[CLOGINSUCC]" �� ����
//	 */
////	public String isClientInDB(ClientLoginInfo clientLoginInfo){
////		if(clientLoginInfo == null) return "[CLOGINFAIL] ClientLoginInfo is empty";
////		students = preStudentDB.getFakeStudents();
////		for(int i=0; i<students.size(); i++){
////			if(students.get(i).getID().equals(clientLoginInfo.getID())){ // 1. ���̵� üũ
////				if(students.get(i).getPW().equals(clientLoginInfo.getPW())) // 2. ��й�ȣ üũ
////					if(isConnectedClient(jmServer.getClients(), students.get(i)) == false) // 3. �������� ��������� üũ
////						return "[CLOGINSUCC]"; // �α��� ����!
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
////			if(students.get(i).getID().equals(clientLoginInfo.getID())){ // 1. ���̵� üũ
////				if(students.get(i).getPW().equals(clientLoginInfo.getPW())) // 2. ��й�ȣ üũ
////					return students.get(i); // �α��� ������ ȸ��
////				else return null;
////			}
////		}
////		return null;
////	}
//	
//	/* ���� ������ �������� Ŭ���̾�Ʈ�� �α��νõ��� ��� true�� ��ȯ
//	 * ���������� ���� Ŭ���̾�Ʈ�� �α��� �õ��� ��� false ��ȯ
//	 * ClientLoginCheckŬ���� isClientInDB()���� �� isConnectedClient()�� �α��ΰ��� 3�ܰ�� ȣ�� */
//	public boolean isConnectedClient(HashMap clients, Student isConnected){
//		Iterator<Student> it = clients.keySet().iterator();
//		while(it.hasNext()){
//			Student student = it.next();
//			if(isConnected.getID().equals(student.getID()))
//				return true; // �������� ���������. �α��� ����
//		}
//		return false; // �������� ����� ����. �α������
//	}
//	
//
//}
