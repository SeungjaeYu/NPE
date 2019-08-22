package ui;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.ReservSeatDAO;
import dao.ReservationDAO;
import dao.UserDAO;
import util.CommUtil;
import vo.ReservationVO;

public class ReservationUI {
	
	SqlSession session;
	
	
	private ReservationDAO reservationDAO;
	private ReservSeatDAO reservSeatDAO;
	private UserDAO userDAO;
	
	
	public ReservationUI() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
		reservationDAO = session.getMapper(ReservationDAO.class);
		reservSeatDAO = session.getMapper(ReservSeatDAO.class);
		userDAO =  session.getMapper(UserDAO.class);
		
	}
	
	
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
		
			List<ReservationVO> reservList = reservationDAO.reservList(userNo);
			System.out.println("예매 정보");
			System.out.println("----------------------------------------");
			System.out.printf("%2s%18s%13s%6s%5s\n"
					,"번호", "영화제목", "상영시간", "상영관", "좌석");
			System.out.println("----------------------------------------");
			int reservCnt = reservList.size();
			
			if (reservList.isEmpty()) {
				System.out.println("예매 내역이 없습니다.");
			} else {
				for (ReservationVO reservVO : reservList) {
					System.out.printf("%2s%20s%16s%8s%8s\n", reservCnt--, reservVO.getMovieTitle(),
							reservVO.getMovieTime(), reservVO.getTheaterName(),
							CommUtil.getReservRow(reservVO.getReservRow()) + "" + CommUtil.getReservCol(reservVO.getReservCol()));
					
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
		List<ReservationVO> reservList = reservationDAO.reservList(userNo);
		if (reservList.isEmpty()) {
			System.out.println("예매 내역이 없습니다.");
			return;
		}
		
		int reservRemove = CommUtil.getInt("취소할 예매 번호를 입력하세요 : ");
		
		while (reservRemove > reservList.size() || reservRemove < 0) {
			System.out.println("잘못된 값을 입력하셨습니다.");
			reservRemove = CommUtil.getInt("취소할 예매 번호를 입력하세요 : ");
		}
		if (reservRemove == 0) return;
		ReservationVO vo = reservList.get(reservList.size() - reservRemove );
		
		
		
		String chkDel = CommUtil.getStr("정말로 예매를 취소하시겠습니까?(Y/N) : ");
		if (chkDel.equalsIgnoreCase("Y")) {
			int reservDel = reservSeatDAO.deleteReservSeat(vo.getReservNo());
			reservDel += reservationDAO.deleteReserv(vo.getReservNo());
			reservDel += userDAO.updateUserReservRemove(userNo);
			if (reservDel == 3) {
				session.commit();
				System.out.printf("선택하신 \'%s시 %s분 %s 좌석:%s\'이 취소되었습니다.\n"
						, vo.getMovieTime().substring(0, vo.getMovieTime().indexOf(":"))
						, vo.getMovieTime().substring(vo.getMovieTime().indexOf(":") + 1, vo.getMovieTime().length())
						, vo.getMovieTitle()
						, CommUtil.getReservRow(vo.getReservRow()) + "" + CommUtil.getReservCol(vo.getReservCol()));
				System.out.println("============================");
				 return;
			}
		}
		session.rollback();
		System.out.println("예매가 취소되지 않았습니다.");
		System.out.println("============================");
		
	}

}
