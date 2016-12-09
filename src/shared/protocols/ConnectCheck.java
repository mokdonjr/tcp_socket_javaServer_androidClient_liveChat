package shared.protocols;

import java.io.Serializable;
import java.util.StringTokenizer;


// This class is send client's info, all Client's in usertable PDU
// PDU organized head(type, source id) and body(info or clients list)
public class ConnectCheck implements Serializable {
	static final long serialVersionUID = 3518731767529258119L;
	private String head = null;
	private String body = null;
	final static String CRLF = "\r\n";
	
	public ConnectCheck() {
		
	}
	
	public void setHead(int type, String SourceID) {
		head = type + CRLF + SourceID;
	}
	
	public void setBody(String msg) {
		body = msg;
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
	
	public String getBody() {
		return body;
	}
	
	public String encodeConnectCheck() {
		return head + CRLF + body;
	}
	
	public void decodeConnectCheck(String str) {
		StringTokenizer st = new StringTokenizer(str, CRLF);
		String str2 = "", type = "", sourceID = "";
		
		int i = 0; body = "";
		while (st.hasMoreTokens()) {
			str2 = st.nextToken();
			
			if (i == 0) type = str2;
			else if (i == 1) sourceID = str2;
			else if (i == 2) {
				body = str2;
				while (st.hasMoreTokens()) {
					str2 = st.nextToken();
					body += CRLF + str2;
				}
				break;
			}
			
			if (i <= 2) i++;
			else break;
		}
		
		this.setHead(Integer.parseInt(type), sourceID);
	}
}