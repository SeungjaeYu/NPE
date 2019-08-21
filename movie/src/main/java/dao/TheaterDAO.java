package dao;

import java.util.List;

import vo.TheaterVO;

public interface TheaterDAO {


	/**
	 * 상영관 전체 리스트
	 * @return
	 */
	List<TheaterVO> selectTheaterList();
	/**
	 * 상영관 정보 조회
	 * @param theaterNo
	 * @return
	 */
	TheaterVO selectOneTheater(String theaterName);
	/**
	 * 상영관 정보 입력
	 * @param vo
	 */
	int insertTheater(TheaterVO vo); 
	/**
	 * 상영관정보 수정
	 * @param vo
	 */
	int updateTheater(TheaterVO vo);
	/**
	 * 상영관 삭제
	 * @param no
	 * @return
	 */
	int deleteTheater(String theaterName);
	

}
