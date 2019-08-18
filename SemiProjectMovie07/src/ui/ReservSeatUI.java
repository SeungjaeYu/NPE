package ui;

import java.util.List;

import dao.InningDAO2;
import dao.ReservSeatDAO;
import util.CommUtil;
import vo.InningVO;
import vo.ReservationVO;

public class ReservSeatUI {
	InningUI2 inningUI = new InningUI2();
	ReservSeatDAO reservSeatDAO = new ReservSeatDAO();
	InningDAO2 inningDAO = new InningDAO2();
	
	public void reservSeatList() {
		
		int inningNo = inningUI.showInningList();
		
		if (inningNo == 0) return;
		
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
