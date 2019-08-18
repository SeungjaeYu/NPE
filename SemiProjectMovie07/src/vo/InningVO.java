package vo;

public class InningVO extends TheaterVO {
	private int tempNo;
	private int inningNo;
	private int movieNo;
	private int movieInning;
	private String movieTime;
	private int theaterNo;
	public InningVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InningVO(int theaterNo, String theaterName, int seatRow, int seatCol) {
		super(theaterNo, theaterName, seatRow, seatCol);
		// TODO Auto-generated constructor stub
	}
	public InningVO(int tempNo, int inningNo, int movieNo, int movieInning, String movieTime, int theaterNo) {
		super();
		this.tempNo = tempNo;
		this.inningNo = inningNo;
		this.movieNo = movieNo;
		this.movieInning = movieInning;
		this.movieTime = movieTime;
		this.theaterNo = theaterNo;
	}
	public int getTempNo() {
		return tempNo;
	}
	public void setTempNo(int tempNo) {
		this.tempNo = tempNo;
	}
	public int getInningNo() {
		return inningNo;
	}
	public void setInningNo(int inningNo) {
		this.inningNo = inningNo;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public int getMovieInning() {
		return movieInning;
	}
	public void setMovieInning(int movieInning) {
		this.movieInning = movieInning;
	}
	public String getMovieTime() {
		return movieTime;
	}
	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}
	public int getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}
	@Override
	public String toString() {
		return "InningVO [tempNo=" + tempNo + ", inningNo=" + inningNo + ", movieNo=" + movieNo + ", movieInning="
				+ movieInning + ", movieTime=" + movieTime + ", theaterNo=" + theaterNo + "]";
	}
	
	
	
}
