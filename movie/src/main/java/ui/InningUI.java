package ui;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.InningDAO;
import dao.ReservationDAO;
import util.CommUtil;
import vo.InningVO;

public class InningUI {
	SqlSession session;
	private ReservationDAO reservationDAO;
	private InningDAO inningDAO;

	private ReservSeatUI reservSeatUI = new ReservSeatUI();
	
	public InningUI() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
		reservationDAO = session.getMapper(ReservationDAO.class);
		inningDAO = session.getMapper(InningDAO.class);
	}
	
	 
	
	public void selectInningList(int movieNo, int userNo) {
		inningOuter : 
			while(true) {
				CommUtil.clear();
				List<InningVO> list = inningDAO.selectInning(movieNo);
				int no = 1;
				System.out.println("------------------------------");
				System.out.println("  상영시간\t잔여좌석수\t상영관");
				System.out.println("------------------------------");
				if (list.isEmpty()) {
					System.out.println("상영 정보가 없습니다.");
					CommUtil.clear(2);
					return;
				}
				for (InningVO inning : list) {
					int totSeatSize = inning.getSeatRow() * inning.getSeatCol();
					int reservSize = reservationDAO.countReserv(inning.getInningNo());
					System.out.printf(
							"%d. %-10s%10s%10s",
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
				
				int inningNo = CommUtil.getInt("상세 조회할 회차를 선택하세요 : ");
				
				if (inningNo == 0) break inningOuter;
				while (inningNo > list.size() || inningNo < 0) {
					System.out.println("잘못 된 메뉴번호 입니다. 다시입력하세요. ");
					inningNo = CommUtil.getInt("상세 조회할 회차를 선택하세요 : ");
				}
				InningVO vo = list.get(inningNo - 1);
				reservSeatUI.reservSeatList(vo.getInningNo(), userNo);
				
			}
	}
}
