package dao;

import java.util.ArrayList;
import java.util.List;

import impl.FileIOImpl;
import vo.UserVO;

public class UserDAO extends FileIOImpl {
private int pos = 0;
	
	private String path = "data/ch24/user.txt";
	
	
	public UserDAO() {
		List<UserVO> list = new ArrayList<>();
		List<String> list2 = fileRead(path);
		if (list2 != null) {
			for (String str : list2) {
				
				String[] arr = str.split("@@");
				
				list.add(new UserVO(++pos,
						arr[1], arr[2], arr[3], arr[4], arr[5], Integer.parseInt(arr[6])));
			}
		}
	}
	
	
	public int insertUser(UserVO vo) {
		
		List<UserVO> list = selectUser();
		for (UserVO user : list) {
			if (user.getId().equals(vo.getId())) {
				return 0;
			}
		}
		
		String user = ++pos + "@@" + vo.getId() + "@@" + vo.getPasswd() + "@@" + vo.getPasshint() + "@@"
				      + vo.getName() + "@@" + vo.getGender() + "@@" + vo.getPhone();
		fileWrite(path, user, true);
		return 1;
	}
	
	public List<UserVO> selectUser() {
		List<UserVO> list = new ArrayList<>();
		List<String> list2 = fileRead(path);
	
		if (list2 != null) {
			for (String str : list2) {
				String[] arr = str.split("@@");
				list.add(new UserVO(Integer.parseInt(arr[0]),
						arr[1], arr[2], arr[3], arr[4], arr[5], Integer.parseInt(arr[6])));
			}
		}
		
		return list;
	}
	

}
