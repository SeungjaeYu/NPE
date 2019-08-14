package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;



/**
 *  이 클래스는 공통적으로 사용하는 모듈을 모아 놓은 클래스이다.
 *
 * @since 2019-07-23
 * @author sj Yu
 *
 */

public class CommUtil {
	protected SimpleDateFormat loginSdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	private static Scanner sc = new Scanner(System.in);
	
	
	/**
	 * 년/월/일 시:분:초를 구하여 문자열로 반환한다.
	 * @return
	 */
	public static String getDate() {
		return getDate("yyyy/MM/dd HH:mm:ss");
	}
	
	/**
	 * formatString를 구하여 문자열로 반환한다.
	 * @return
	 */
	
	public static String getDate(String formatString) {
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        DateFormat df = new SimpleDateFormat(formatString);
        return df.format(c);
	}
	

	/**
	 * 
	 *  스캐너를 입력받아 정수형을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public static String getStr(String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}
	/**
	 * 
	 *  스캐너를 입력받아 문자열을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String msg) {
		return Integer.parseInt(getStr(msg));
	}
	
	
	
	
	
	

	
	
	

}
