package ui;

import java.util.List;

import dao.InningDAO;
import dao.ReservationDAO;
import util.CommUtil;
import vo.InningVO;
import vo.MovieVO;
import vo.ReservationVO;

public class InningUI {

	 ReservationDAO reservationDAO = new ReservationDAO();
	 
	 InningDAO inningDAO = new InningDAO();
	 ReservSeatUI reservSeatUI = new ReservSeatUI();
	 
	 
	public void selectInningList(int movieNo, int userNo) {
		inningOuter : 
			while(true) {
				
				List<InningVO> list = inningDAO.selectInning(movieNo);
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
				
				int inningNo = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
				
				if (inningNo == 0) break inningOuter;
				while (inningNo > list.size() || inningNo < 0) {
					System.out.println("잘못 된 메뉴번호 입니다. 다시입력하세요. ");
					inningNo = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
				}
				InningVO vo = null;
				for (InningVO inningVO : list) {
					if (inningNo != inningVO.getTempNo()) continue;
					vo = inningVO;
				}
				if (vo != null) {
					reservSeatUI.reservSeatList(vo.getInningNo(), userNo);
					
				} else {
					System.out.println("없는 회차번호 입니다.");
				}
					
				
			}
	}
}
