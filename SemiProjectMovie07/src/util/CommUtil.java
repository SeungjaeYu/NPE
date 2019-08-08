package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;



/**
 *  이 클래스는 공통적으로 사용하는 모듈을 모아 놓은 클래스이다.
 *
 * @since 2019-07-23
 * @author sj Yu
 *
 */

public class CommUtil {
	
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		CommUtil commUtil = new CommUtil();
		System.out.println(commUtil.getTimeZone());
		
	
	}
	
	/**
	 * 현재 서울의 년/월/일 시:분:초를 구하여 문자열로 반환한다.
	 * @return
	 */
	public String getTimeZone() {
		return getTimeZone("yyyy/MM/dd HH:mm:ss");
	}
	
	/**
	 * 현재 서울의 formatString를 구하여 문자열로 반환한다.
	 * @return
	 */
	
	public String getTimeZone(String formatString) {
		TimeZone time;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(
        		formatString);
        
        time = TimeZone.getTimeZone("Asia/Seoul");
        df.setTimeZone(time);
        return df.format(date);
	}
	

	/**
	 * 
	 *  스캐너를 입력받아 정수형을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public int getIntScanner() {
			return Integer.parseInt(sc.nextLine());
	}
	/**
	 * 
	 *  스캐너를 입력받아 문자열을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public String getStrScanner() {
		return sc.nextLine();
	}
	
	
	
	
	
	

	
	
	

}
