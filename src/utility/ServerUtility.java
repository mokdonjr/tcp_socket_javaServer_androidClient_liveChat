package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUtility {
	/* ���� ��ǻ���� ���� �ð��� ���� */
	public static String getServerTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
}
