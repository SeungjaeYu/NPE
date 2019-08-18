package ui;

import java.util.List;

import dao.InningDAO;
import dao.InningDAO2;
import dao.ReservationDAO;
import util.CommUtil;
import vo.InningVO;
import vo.ReservationVO;

public class InningUI2 {
	 MovieUI2 m = new MovieUI2();
	 ReservationDAO reservationDAO = new ReservationDAO();
	 
	public int showInningList() {
		List<InningVO> list = InningDAO2.selectInning(m.showMovieList());
		
		int no = 1;
		System.out.println("------------------------------");
		System.out.println("상영시간\t잔여  좌석수\t상영관");
		System.out.println("------------------------------");
		for (InningVO inning : list) {
			int totSeatSize = inning.getSeatRow() * inning.getSeatCol();
			int reservSize = reservationDAO.countRserv(inning.getInningNo());
			System.out.printf(
					"%-3d. %5s%10s%10s",
					no++,
					inning.getMovieTime(),
					(totSeatSize - reservSize) + "석",
					inning.getTheaterName()
				);
			System.out.println();
		}
		System.out.println("------------------------------");
		int inningNo =  CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
		for (InningVO inning : list) {
			if (inning.getTempNo() != inningNo) continue;
			return inning.getInningNo();
		}
		return 0;
	}
}
