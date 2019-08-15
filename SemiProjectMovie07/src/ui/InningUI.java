package ui;

import java.util.List;

import dao.InningDAO;
import util.CommUtil;
import vo.InningVO;

public class InningUI {
	 MovieUI m = new MovieUI();
	
	public int showInningList() {
		List<InningVO> list = InningDAO.selectInning(m.showMovieList());
		
		int no = list.size();
		System.out.println("------------------------------");
		System.out.println("상영시간\t잔여  좌석수\t상영관");
		System.out.println("------------------------------");
		for (InningVO inning : list) {
			System.out.printf(
					"%-3d. %5s%-10s%-10s",
					no--,
					inning.getMovieTime(),
					"",
					inning.getTheaterName()
				);
			System.out.println();
		}
		System.out.println("------------------------------");
		return CommUtil.getInt("메뉴 중 처리할 항목을 선택하세요 : ");
	}
}
