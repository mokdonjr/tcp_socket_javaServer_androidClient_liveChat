package shared.protocols;

import java.io.Serializable;
import java.util.StringTokenizer;

// This class is normal msg PDU
// PDU organized head(type, source id, source id) and body(msg)
public class Message implements Serializable {
	static final long serialVersionUID = 3518731767529258119L;
	private String head = null;
	private String body = null;
	final static String CRLF = "\r\n";
	
	public Message() {
		
	}
	
	public void setHead(int type, String sourceID, String destID) {
		head = type + CRLF + sourceID + CRLF + destID;
	}
	
	public void setBody(String str) {
		body = str;
	}
	
	public int getType() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = "";
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
		String str = "";
		
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

		
	public String getDestID() {
		StringTokenizer st = new StringTokenizer(head, CRLF);
		String str = "";
		
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

	public String getBody() {
		return body;
	}	
	
	public String encodeMessage() {
		return head + CRLF + body;
	}
	
	public void decodeMessge(String str) {
		StringTokenizer st = new StringTokenizer(str, CRLF);
		String str2 = "", type = "", sourceID = "", destID = "", body = "";
		
		int i = 0;
		while (st.hasMoreTokens()) {
			str2 = st.nextToken();
			
			if (i == 0) type = str2;
			else if (i == 1) sourceID = str2;
			else if (i == 2) destID = str2;
			else if (i == 3) body = str2;
			
			if (i <= 3) i++;
			else break;
		}
		
		this.setHead(Integer.parseInt(type), sourceID, destID);
		this.setBody(body);
	}
}
