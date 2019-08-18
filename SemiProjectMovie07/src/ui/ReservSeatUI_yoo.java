package ui;

import java.util.List;

import dao.InningDAO_yoo;
import dao.ReservSeatDAO_yoo;
import util.CommUtil;
import vo.InningVO;
import vo.ReservationVO;

public class ReservSeatUI_yoo {
	InningUI_yoo inningUI = new InningUI_yoo();
	ReservSeatDAO_yoo reservSeatDAO = new ReservSeatDAO_yoo();
	InningDAO_yoo inningDAO = new InningDAO_yoo();
	
	public void reservSeatList() {
		int inningNo = 0;
		outer : 
		while(true) {
			inningNo = inningUI.showInningList();
			if (inningNo == 0) continue outer;
			break;
		}
		
		// 전체 자리수 가져오기
		InningVO seatTotSize = inningDAO.selectOneInning(inningNo);
		int[][] seatMovie = new int[seatTotSize.getSeatRow()][seatTotSize.getSeatCol()];
		List<ReservationVO> list = reservSeatDAO.reservSeatList(inningNo);	// 회차 당 영화 예매된 자리 조회
		for (ReservationVO reservVO : list) {
			seatMovie[reservVO.getReservRow()][reservVO.getReservCol()] = 1;
		}
		System.out.println("--------------------------------");
		System.out.print("좌석" + "\t");
		for (int i = 1; i <= seatTotSize.getSeatCol(); i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		System.out.println("--------------------------------");
		for (int i = 0; i < seatMovie.length; i++) {
			System.out.print(CommUtil.getReservRow(i) + "    |\t");
			for (int j = 0; j < seatMovie[i].length; j++) {
				System.out.printf("%4s",seatMovie[i][j] == 1 ? "X" : "O");
			}
			System.out.println();
		}
	}
}