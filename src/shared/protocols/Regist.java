package shared.protocols;

import java.io.Serializable;
import java.util.StringTokenizer;

/* DataType = 0
 * 송신ID, 송신IP, 송신port */
// This class is send Client's info PDU
// PDU organized only head(type, client's id, client's ip, client's port)
public class Regist implements Serializable {
	static final long serialVersionUID = 3518731767529258119L;
	private String head = null;
	private String body = null;//
	final static String CRLF = "\r\n";
	
	public Regist() {
		
	}
	
	public void setHead(int type, String SourceID, String SourceIP, int SourcePort, String SourcePW, String LoginResult) {
		head = type + CRLF + SourceID + CRLF + SourceIP + CRLF + SourcePort + CRLF + SourcePW + CRLF + LoginResult;
	}
	
	public void setBody(String str){
		this.body = str; // Student객체 toString()결과 (ID, PW, name, school, department ...)
	}
	
	public int getType() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = null;
		int type = 0;
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			
			if (i == 0) {
				type = Integer.parseInt(str);
				return type;
			}
			else i++;
		}
		return 0;
	}
		
	public String getSourceID() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = null;
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			
			if (i == 1) {
				return str;
			}
			else i++;
		}
		return null;
	}
	
	public String getSourceIP() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = null;
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			
			if (i == 2) {
				return str;
			}
			else i++;
		}
		return null;
	}
	
	public int getSourcePort() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = null;
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			
			if (i == 3) {
				return Integer.parseInt(str);
			}
			else i++;
		}
		return 0;
	}
	
	public String getSourcePW() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = null;
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			
			if (i == 4) {
				return str;
			}
			else i++;
		}
		return null;
	}
	
	public String getLoginResult() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = null;
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			
			if (i == 5) {
				return str;
			}
			else i++;
		}
		return null;
	}
	
	public String getBody(){
		return this.body;
	}
	
	public String encodeRegist() {
		return head + CRLF + body;
	}
	
	public void decodeRegist(String str) {
		StringTokenizer st = new StringTokenizer(str, CRLF);
		String str2 = "", type = "", sourceID = "", sourceIP = "", sourcePort = ""
				, sourcePW = "", loginResult = "", body = "";
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str2 = st.nextToken();
			
			if (i == 0) type = str2;
			else if (i == 1) sourceID = str2;
			else if (i == 2) sourceIP = str2;
			else if (i == 3) sourcePort = str2;
			else if (i == 4) sourcePW = str2;
			else if (i == 5) loginResult = str2;
			else if (i == 6) body = str2;
			
			if (i <= 6) i++;
			else break;
		}
		
		this.setHead(Integer.parseInt(type), sourceID, sourceIP, Integer.parseInt(sourcePort), sourcePW, loginResult);
		this.setBody(body);
	}
}