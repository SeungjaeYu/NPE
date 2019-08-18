package ui;

import java.util.List;

import dao.InningDAO_yoo;
import dao.ReservationDAO_yoo;
import util.CommUtil;
import vo.InningVO;

public class InningUI_yoo {
	 MovieUI_yoo m = new MovieUI_yoo();
	 ReservationDAO_yoo reservationDAO = new ReservationDAO_yoo();
		
	public int showInningList() {
		outer : 
		while(true) {
			List<InningVO> list = InningDAO_yoo.selectInning(m.showMovieList());
			
			int no = 1;
			System.out.println("------------------------------");
			System.out.println("상영시간\t잔여  좌석수\t상영관");
			System.out.println("------------------------------");
			for (InningVO inning : list) {
				int totSeatSize = inning.getSeatRow() * inning.getSeatCol();
				int reservSize = reservationDAO.countRserv(inning.getInningNo());
				System.out.printf(
						"%-2d. %-10s%5s%5s",
						no++,
						inning.getMovieTime(),
						(totSeatSize - reservSize) + "석",
						inning.getTheaterName()
					);
				System.out.println();
			}
			System.out.println("------------------------------");
			System.out.println("0 . 이전");
			System.out.println("------------------------------");
			outer2 : 
			while(true) {
				int userNum = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
				if (userNum == 0) break outer;
				if (userNum > list.size() || userNum < 0) {
					System.out.println("잘못 된 메뉴번호 입니다. 다시입력하세요. ");
					continue outer2;
				}
				return userNum;
			}
		}
	return 0;
	}
}
