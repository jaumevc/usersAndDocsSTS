package app.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilsTime {
	
	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
		}
	
	public static String nowName() {
		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("d-M-yy_HH-mm-ss");
		SimpleDateFormat sdf = new SimpleDateFormat("d-M-yy");
		return sdf.format(cal.getTime());
		}
}
