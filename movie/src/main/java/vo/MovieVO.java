package vo;

public class MovieVO {
	
	private int tempNo;
	private int movieNo;
	private String movieTitle;
	private String movieDirector;
	private String movieActor;
	private String viewingGrade;
	public MovieVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieVO(int tempNo, int movieNo, String movieTitle, String movieDirector, String movieActor,
			String viewingGrade) {
		super();
		this.tempNo = tempNo;
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.movieDirector = movieDirector;
		this.movieActor = movieActor;
		this.viewingGrade = viewingGrade;
	}
	public int getTempNo() {
		return tempNo;
	}
	public void setTempNo(int tempNo) {
		this.tempNo = tempNo;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public String getMovieActor() {
		return movieActor;
	}
	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}
	public String getViewingGrade() {
		return viewingGrade;
	}
	public void setViewingGrade(String viewingGrade) {
		this.viewingGrade = viewingGrade;
	}
	@Override
	public String toString() {
		return "MovieVO [tempNo=" + tempNo + ", movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", movieDirector="
				+ movieDirector + ", movieActor=" + movieActor + ", viewingGrade=" + viewingGrade + "]";
	}
	
	
	
}
