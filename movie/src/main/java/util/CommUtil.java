package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.security.MessageDigest;

/**
 * 이 클래스는 공통적으로 사용하는 모듈을 모아 놓은 클래스이다.
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
	 * 
	 * @return
	 */
	public static String getDate() {
		return getDate(DATE_FORMAT_TIME);
	}

	/**
	 * formatString를 구하여 문자열로 반환한다.
	 * 
	 * @return
	 */

	public static String getDate(String formatString) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		DateFormat df = new SimpleDateFormat(formatString);
		return df.format(c.getTime());
	}

	/**
	 * parseDate를 구하여 문자열로 반환한다.
	 * 
	 * @return
	 */
	public static String getDate(Date parseDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(parseDate);
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(c.getTime());
	}

	/**
	 * 
	 * 스캐너를 입력받아 정수형을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public static String getStr(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				String resultStr = sc.nextLine();
				if ("".equals(resultStr.trim())) {
					System.out.println("공백은 입력하실 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				return resultStr;
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력하셨습니다. 제대로 된 문자를 넣어주세요");
			}

		}
	}

	/**
	 * 
	 * 스캐너를 입력받아 문자열을 리턴해준다.
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				int num = Integer.parseInt(sc.nextLine());
				if (num < 0) {
					System.out.println("0보다 작은 수는 입력하실 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				return num;
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력하셨습니다. 제대로 된 숫자를 넣어주세요");
			}

		}
	}

	/**
	 * 
	 * 좌석 행을 입력받아 문자로 반환해준다.
	 * 
	 * @param reservCol
	 * @return
	 */
	public static char getReservRow(int reservRow) {
		return (char) ('A' + reservRow);
	}

	/**
	 * 
	 * 좌석 열을 입력받아 좌석자리를 반환해준다.
	 * 
	 * @param reservRow
	 * @return
	 */
	public static int getReservCol(int reservCol) {
		return reservCol + 1;
	}

	/**
	 * 좌석 행을 입력받아 숫자로 반환해준다.
	 * 
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
	 * 좌석 열을 입력받아 숫자로 반환해준다.
	 * 
	 * @param reservRow
	 * @return
	 */
	public static int parseReservCol(int reservCol) {
		return reservCol - 1;
	}

	public static String[] movieTimeList = new String[] { "09:00", "11:30", "13:10", "15:20", "17:00", "19:10", "21:30",
			"23:00", "24:40" };

	
	/**
	 *  회원가입 이메일 인증 코드를 위한 난수 생성기(숫자만 6자리)
	 * 
	 * @return
	 */
	public static String randomKey() {
		return String.valueOf(new Random().nextInt(900000) + 100000);
	}
	
	
	
	/**
	 *  비밀번호 찾기를 위한 난수 생성기(문자 + 숫자 조합 20개)
	 * 
	 * @return
	 */
	public static String randomKeyByPassword() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 20; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		return temp.toString();

	}
	
	
	/**
	 *  이메일 전송 코드 체크
	 * 
	 * @param randomNum
	 * @param emailKey
	 * @param failMsg
	 * @return
	 */
	
	public static boolean emailChk(String randomNum, String failMsg) {
		
		int keyChkIdx = 4;
		String emailKey = getStr("메일에서 확인한 인증번호를 입력하세요 : ");
		for (int i = 1; i <= keyChkIdx ; i++) {
			if (i == keyChkIdx) {
				System.out.println(failMsg);
				return false;
			}
			if (!randomNum.equals(emailKey)) {
				System.out.println(i + "회 입력 오류입니다.(" + i + "/3)");
				if (keyChkIdx > i + 1) {
					System.out.println("인증번호가 같지 않습니다. 다시 입력해주세요.");
					emailKey = CommUtil.getStr("메일에서 확인한 인증번호를 입력하세요 : ");
				}
				continue;
			} else return true;
		}
		return false;
	}
	
	public static void clear() {
		clear(0);
	}
	
	public static void clear(int delay) {
		try {
			Thread.sleep(delay);
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			
		}
	}
}