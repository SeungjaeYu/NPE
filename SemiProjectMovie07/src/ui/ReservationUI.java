package ui;

import java.util.List;

import dao.ReservationDAO;
import util.CommUtil;
import vo.ReservationVO;

public class ReservationUI {
	
	ReservationDAO dao = new ReservationDAO();
	
	
	
	public void service(int userNo) {
		
		reservOuter :
		while(true) {
			selectReservList(userNo);
			switch (menu()) {
			case 1:
				deleteReserv(userNo);  break;
			case 0:
				break reservOuter;
			default:
				System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
			
		}
		
	}
	
	public int menu() {
		System.out.println("1. 예매취소");
		System.out.println("0. 이전메뉴");
		return CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
	}
	
	
	
	
	
	/**
	 * 
	 * 예매 조회
	 * @param userNo
	 */
	public void selectReservList(int userNo) {
		
			List<ReservationVO> reservList = dao.reservList(userNo);
			System.out.println("예매 정보");
			System.out.println("----------------------------------------");
			System.out.println("번호\t\t영화제목\t상영시간\t상영관\t\t좌석");
			System.out.println("----------------------------------------");
			int reservCnt = reservList.size();
			
			if (reservList.isEmpty()) {
				System.out.println("예매 내역이 없습니다.");
				
			} else {
				for (ReservationVO reservVO : reservList) {
					System.out.print(reservCnt-- + "\t\t");
					System.out.print(reservVO.getMovieTitle() + "\t\t");
					System.out.print(reservVO.getMovieTime() + "\t\t");
					System.out.print(reservVO.getTheaterName( )+ "\t\t");
					System.out.println(CommUtil.getReservCol(reservVO.getReservCol()) + "" + CommUtil.getReservRow(reservVO.getReservRow()));
					
				}
				
			}
			
			System.out.println("----------------------------------------");
	
	}
	
	
	/**
	 * 
	 * 예매 삭제
	 * @param userNo
	 */
	public void deleteReserv(int userNo) {
		
		int reservRemove = CommUtil.getInt("취소할 예매 번호를 입력하세요 : ");
		List<ReservationVO> reservList = dao.reservList(userNo);
		int realReservNo = 0;
		for (ReservationVO reservVO : reservList) {
			if (reservRemove != reservVO.getTempNo()) continue;
			realReservNo = reservVO.getReservNo();
		}
		
		if (realReservNo == 0) {
			System.out.println("잘못된 값을 입력하셨습니다.");
			return;
		}
		
		String chkDel = CommUtil.getStr("정말로 예매를 취소하시겠습니까?(Y/N) : ");
		if (chkDel.equalsIgnoreCase("Y")) {
			int reservDel = dao.deleteReserv(realReservNo, userNo);
			if (reservDel == 3) {
				System.out.println("성공적으로 예매가 취소되었습니다.");
				System.out.println("============================");
				 return;
			}
		}
		System.out.println("예매가 취소되지 않았습니다.");
		System.out.println("============================");
		
	}

}
