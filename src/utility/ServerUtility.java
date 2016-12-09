package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUtility {
	/* 서버 컴퓨터의 현재 시각을 리턴 */
	public static String getServerTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
}
