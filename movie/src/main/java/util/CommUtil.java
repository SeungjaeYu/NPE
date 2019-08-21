package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



/**
 *  이 클래스는 공통적으로 사용하는 모듈을 모아 놓은 클래스이다.
 *
 * @since 2019-07-23
 * @author sj Yu
 *
 */


public class CommUtil {
	public SimpleDateFormat loginSdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	private static Scanner sc = new Scanner(System.in);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 년/월/일 시:분:초를 구하여 문자열로 반환한다.
	 * @return
	 */
	public static String getDate() {
		return getDate(DATE_FORMAT);
	}
	
	/**
	 * formatString를 구하여 문자열로 반환한다.
	 * @return
	 */
	
	public static String getDate(String formatString) {
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        DateFormat df = new SimpleDateFormat(formatString);
        return df.format(c.getTime());
	}
	

	/**
	 * 	parseDate를 구하여 문자열로 반환한다.
	 * 	@return
	 */
	public static String getDate(Date parseDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(parseDate);
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(c.getTime());
	}

	/**
	 * 
	 *  스캐너를 입력받아 정수형을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public static String getStr(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return sc.nextLine();
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력하셨습니다. 제대로 된 문자를 넣어주세요");
			}
			
		}
	}
	/**
	 * 
	 *  스캐너를 입력받아 문자열을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력하셨습니다. 제대로 된 숫자를 넣어주세요");
			}
			
		}
	}
	

	/**
	 * 
	 *  좌석 행을 입력받아 문자로 반환해준다.
	 * 
	 * @param reservCol
	 * @return
	 */
	public static char getReservRow(int reservRow) {
		return (char)('A' + reservRow);
	}
	
	/**
	 * 
	 *  좌석 열을 입력받아 좌석자리를 반환해준다.
	 * 
	 * @param reservRow
	 * @return
	 */
	public static int getReservCol(int reservCol) {
		return reservCol + 1;
	}
	
	/**
	 *  좌석 행을 입력받아 숫자로 반환해준다.
	 * @param reservCol
	 * @return
	 */
	public static int parseReservRow(String reservRow) throws ArithmeticException {
		if (reservRow.length() > 1) {
			throw new ArithmeticException("좌석 열의 값은 A~Z까지로 한정되어있습니다.");
		}
		char parseReservRow = Character.toUpperCase(reservRow.charAt(0));
		if (parseReservRow < 'A' || parseReservRow > 'Z') {
			throw new ArithmeticException("좌석 열의 값은 A~Z까지로 한정되어있습니다.");
		}
		return (char) (parseReservRow - 'A');
	}
	
	/**
	 *  좌석 열을 입력받아 숫자로 반환해준다.
	 * @param reservRow
	 * @return
	 */
	public static int parseReservCol(int reservCol) {
		return reservCol - 1;
	}

	
	
	public static String[] movieTimeList = new String[] {"09:00","11:30", "13:10", "15:20", "17:00", "19:10", "21:30", "23:00", "24:40" };
	
}