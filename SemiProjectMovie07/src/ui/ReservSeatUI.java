package ui;

import java.util.List;

import dao.InningDAO2;
import dao.ReservSeatDAO;
import dao.ReservationDAO;
import util.CommUtil;
import vo.InningVO;
import vo.ReservationVO;

public class ReservSeatUI {
	InningUI2 inningUI = new InningUI2();
	ReservSeatDAO reservSeatDAO = new ReservSeatDAO();
	InningDAO2 inningDAO = new InningDAO2();
	
	ReservationDAO reservDAO = new ReservationDAO();
	
	public void reservSeatList(int userNo) {
		
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
		System.out.println("-------------------------------");
		System.out.println("1. 예매하기");
		System.out.println("0. 이전메뉴");
		System.out.println("-------------------------------");
		int chkReserv = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
		
		if (chkReserv == 1) {
			
			String ioReservRow = CommUtil.getStr("에매할 좌석의 행을 입력하세요 : ");
			int iocharReservRow = CommUtil.parseReservRow(ioReservRow.charAt(0));
			int ioReservCol = CommUtil.parseReservCol(CommUtil.getInt(("에매할 좌석의 열을 입력하세요 : ")));
			
			if (iocharReservRow + 1 > seatTotSize.getSeatRow() || ioReservCol + 1 > seatTotSize.getSeatCol()) {
				System.out.println("상영관에 없는 좌석 번호를 입력하셨습니다.");
				return;
			}
			
			
			
			if (seatMovie[iocharReservRow][ioReservCol] == 1) {
				System.out.printf("선택하신 %s%d좌석은 예매된 좌석입니다.\n ", CommUtil.getReservRow(iocharReservRow), CommUtil.getReservCol(ioReservCol));
				return;
			}
			
			
			
			int result = reservDAO.insertReserv(inningNo, userNo, iocharReservRow, ioReservCol);
				System.out.printf("선택하신  %s%d좌석이 예매 %s\n "
						, CommUtil.getReservRow(iocharReservRow),
						CommUtil.getReservCol(ioReservCol),
						result == 3 ? "되었습니다." : "실패하였습니다.");
			
			
		} else return;
		
	}
	
	
}
