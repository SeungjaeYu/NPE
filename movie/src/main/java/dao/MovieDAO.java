package dao;

import java.util.List;

import vo.MovieVO;

public interface MovieDAO {
	/**
	 * 메서드 명 : selectMovie()
	 * 기능 정의 : 등록된 영화 목록 조회
	 * @return
	 */
	List<MovieVO> selectMovie();
	/**
	 * 선택한 영화 조회
	 * @param movieNo
	 * @return
	 */
	MovieVO selectOneMovie(String originalTitle);
	/**
	 * 메서드 명 : insertMovie()
	 * 기능 정의 : 영화 등록
	 * @return
	 */
	int insertMovie(MovieVO movieVO); 
	/**
	 * 영화 수정
	 */
	int updateMovie(MovieVO movieVO);
	/**
	 * 메서드 명 : deleteMovie()
	 * 기능 정의 : 영화 삭제
	 * @return
	 */
	int deleteMovie(String movieTitle);
}
