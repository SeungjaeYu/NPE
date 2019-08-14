package vo;

import java.util.Date;

public class UserVO extends GradeVO{
	private int userNo;
	private String userId;
	private String password;
	private String userEmail;
	private Date regDate;
	private int reservCnt;
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserVO(int userNo, String userId, String password, String userEmail, Date regDate, int reservCnt) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.password = password;
		this.userEmail = userEmail;
		this.regDate = regDate;
		this.reservCnt = reservCnt;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getReservCnt() {
		return reservCnt;
	}
	public void setReservCnt(int reservCnt) {
		this.reservCnt = reservCnt;
	}
	
	@Override
	public String toString() {
		return "UserVO [userNo=" + userNo + ", userId=" + userId + ", password=" + password + ", userEmail=" + userEmail
				+ ", regDate=" + regDate + ", reservCnt=" + reservCnt + "]";
	}
	
	
	
}
