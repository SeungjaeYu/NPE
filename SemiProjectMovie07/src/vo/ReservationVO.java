package vo;

public class ReservationVO {
	private int reservNo;
	private int inningNo;
	private int userNo;
	public ReservationVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservationVO(int reservNo, int inningNo, int userNo) {
		super();
		this.reservNo = reservNo;
		this.inningNo = inningNo;
		this.userNo = userNo;
	}
	public int getReservNo() {
		return reservNo;
	}
	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}
	public int getInningNo() {
		return inningNo;
	}
	public void setInningNo(int inningNo) {
		this.inningNo = inningNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "Reservation [reservNo=" + reservNo + ", inningNo=" + inningNo + ", userNo=" + userNo + "]";
	}
	
}
