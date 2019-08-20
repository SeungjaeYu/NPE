package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.InningVO;

public class InningDAO {
	private static final String NS = "dao.InningDAO.";
	
	
	// myBatis를 사용하기 위해서 SqlSession 객체가 필요함
	private static SqlSession session;
	
	
	
	public InningDAO() {
		session = db.MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public List<InningVO> selectInning(int movieNo) {
		
		List<InningVO> list = session.selectList(NS + "selectInning", movieNo);
		int idx = 1;
		for (InningVO inning : list) {
			inning.setTempNo(idx++);
		}
		return list;
	}
	
	/**
	 * 메서드 명 : selectOneInning()
	 * 기능 정의 : 등록 된 회차 하나 조회
	 */
	
	public InningVO selectOneInning(int inningNo) {
		return session.selectOne(NS + "selectOneInning", inningNo);
	}
	
}