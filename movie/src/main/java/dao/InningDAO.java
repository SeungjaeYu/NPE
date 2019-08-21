package dao;

import java.util.List;

import vo.InningVO;

public interface InningDAO {
	/**
	 * 메서드 명 : selectInning()
	 * 기능 정의 : 등록 된 회차 목록 조회
	 */
	
	List<InningVO> selectInning(int movieNo);
	
	/**
	 * 메서드 명 : selectOneInning()
	 * 기능 정의 : 등록 된 회차 하나 조회
	 */
	InningVO selectOneInning(int inningNo);
	
	int insertInning(InningVO inningVO);
	
	int deleteInnning(int movieNo);
	
}