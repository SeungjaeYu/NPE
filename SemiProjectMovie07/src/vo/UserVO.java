package vo;

public class UserVO {
	private int no;
	private String id;
	private String passwd;
	private String passhint;
	private String name;
	private String gender;
	private int phone;
	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserVO(int no, String id, String passwd, String passhint, String name, String gender, int phone) {
		super();
		this.no = no;
		this.id = id;
		this.passwd = passwd;
		this.passhint = passhint;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasshint() {
		return passhint;
	}
	public void setPasshint(String passhint) {
		this.passhint = passhint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

	
	
}
