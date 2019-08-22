package ui;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.InningDAO;
import dao.ReservSeatDAO;
import dao.ReservationDAO;
import dao.UserDAO;
import util.CommUtil;
import util.SendEmail;
import vo.InningVO;
import vo.ReservSeatVO;
import vo.ReservationVO;
import vo.UserVO;

public class ReservSeatUI {

	SqlSession session;

	private ReservSeatDAO reservSeatDAO;
	private InningDAO inningDAO;
	private ReservationDAO reservDAO;
	private UserDAO userDAO;

	public ReservSeatUI() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
		reservSeatDAO = session.getMapper(ReservSeatDAO.class);
		inningDAO = session.getMapper(InningDAO.class);
		reservDAO = session.getMapper(ReservationDAO.class);
		userDAO = session.getMapper(UserDAO.class);
	}

	/**
	 * 회차별 상영관 예매 좌석 보기 및 예매 등록
	 * 
	 * @param userNo
	 */

	public void reservSeatList(int inningNo, int userNo) {

		reservOuter: while (true) {

			// 전체 자리수 가져오기
			InningVO seatTotSize = inningDAO.selectOneInning(inningNo);
			if (seatTotSize == null) {
				System.out.println("잘못된 값을 입력하셨습니다.");
				return;
			}

			int[][] seatMovie = new int[seatTotSize.getSeatRow()][seatTotSize.getSeatCol()];

			// 회차 당 영화 예매된 자리 조회
			List<ReservationVO> list = reservSeatDAO.reservSeatList(inningNo);
			if (list.isEmpty()) {
				System.out.println("자리 조회가 불가능한 상태입니다.");
				return;
			}
			for (ReservationVO reservVO : list) {
				seatMovie[reservVO.getReservRow()][reservVO.getReservCol()] = 1;
			}

			// 열의 크기만큼 idx++
			System.out.println("--------------------------------");
			System.out.print("좌석" + "\t");
			for (int i = 1; i <= seatTotSize.getSeatCol(); i++) {
				System.out.printf("%4d", i);
			}
			System.out.println();
			System.out.println("--------------------------------");

			// 각 회차별 상영관 예매된 좌석이 있다면 X, 없다면 O
			for (int i = 0; i < seatMovie.length; i++) {
				System.out.print(CommUtil.getReservRow(i) + "    |\t");
				for (int j = 0; j < seatMovie[i].length; j++) {
					System.out.printf("%4s", seatMovie[i][j] == 1 ? "X" : "O");
				}
				System.out.println();
			}

			System.out.println("-------------------------------");
			System.out.println("1. 예매하기");
			System.out.println("0. 이전메뉴");
			System.out.println("-------------------------------");
			int chkReserv = CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
			if (chkReserv == 0)
				break reservOuter;
			if (userNo == 0) {
				System.out.println("비회원은 조회만 가능합니다.");
				continue reservOuter;
			} else if (userNo == 25) {
				System.out.println("관리자는 예매를 하실 수 없습니다.");
				continue reservOuter;
			} else if (seatTotSize.getSeatCol() * seatTotSize.getSeatRow() == list.size()) {
				System.out.println("매진된 상영관입니다. ");
				break reservOuter;
			}

			String ioReservRow = CommUtil.getStr("예매할 좌석의 행을 입력하세요 : ");
			int iocharReservRow = 0;

			// 행을 입력할 때 A~Z 이외의 값이 나오면 예외 발생
			try {
				iocharReservRow = CommUtil.parseReservRow(ioReservRow);
			} catch (ArithmeticException e) {
				System.out.println(e.getMessage());
				return;
			}

			int ioReservCol = CommUtil.parseReservCol(CommUtil.getInt(("예매할 좌석의 열을 입력하세요 : ")));

			// 상영관 사이즈보다 크거나 작은 값 입력한 경우
			if (iocharReservRow + 1 > seatTotSize.getSeatRow() || ioReservCol + 1 > seatTotSize.getSeatCol()
					|| iocharReservRow < 0 || ioReservCol < 0) {
				System.out.println("상영관에 없는 좌석 번호를 입력하셨습니다.");
				continue reservOuter;
			}

			// 이미 예매된 좌석 일 경우
			if (seatMovie[iocharReservRow][ioReservCol] == 1) {
				System.out.printf("선택하신 %s%d좌석은 예매된 좌석입니다.\n ", CommUtil.getReservRow(iocharReservRow),
						CommUtil.getReservCol(ioReservCol));
				continue reservOuter;
			}

			ReservationVO reservVO = new ReservationVO();
			reservVO.setInningNo(inningNo);
			reservVO.setUserNo(userNo);

			int result = reservDAO.insertReserv(reservVO);

			ReservSeatVO reservSeatVO = new ReservSeatVO();

			reservSeatVO.setReservNo(reservVO.getReservNo());
			reservSeatVO.setReservRow(iocharReservRow);

			reservSeatVO.setReservCol(ioReservCol);
			result += reservSeatDAO.insertReservSeat(reservSeatVO);
			result += userDAO.updateUserReservAdd(userNo);

			UserVO userVO = userDAO.selectOneUserByUserNo(userNo);

			ReservationVO reVO = reservDAO.reservSelectOne(reservVO.getReservNo());

			if (result == 3) {
				session.commit();
				System.out.printf("선택하신  %s%d좌석이 예매 되었습니다.\n ", CommUtil.getReservRow(iocharReservRow),
						CommUtil.getReservCol(ioReservCol));
				System.out.println("예매 내역 이메일로 전송중......");
				new SendEmail(userVO.getUserEmail(), "안녕하세요 고객님. 영화 예매내역 입니다.",
						String.format("예매 좌석 정보 : 영화 제목 : %s, 영화 시간 : %s, 영화관 : %s, 좌석 : %s", reVO.getMovieTitle(),
								reVO.getMovieTime(), reVO.getTheaterName(),
								CommUtil.getReservRow(iocharReservRow) + "" + CommUtil.getReservCol(ioReservCol)));
				System.out.println("예매 내역 이메일 전송이 완료되었습니다.");
				return;
			}
			session.rollback();
			System.out.printf("선택하신  %s%d좌석 예매를 실패 하였습니다.\n ", CommUtil.getReservRow(iocharReservRow),
					CommUtil.getReservCol(ioReservCol));

//					System.out.printf("선택하신  %s%d좌석이 예매 %s\n "
//							, CommUtil.getReservRow(iocharReservRow),
//							CommUtil.getReservCol(ioReservCol),
//							result == 3 ? "되었습니다." : "실패하였습니다.");
		}

	}

}
