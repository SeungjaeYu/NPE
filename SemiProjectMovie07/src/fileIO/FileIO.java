package fileIO;

import java.util.List;

public interface FileIO {
	List<String> fileRead(String path);
	void fileWrite(String path, String write, boolean chk);
	void mkdirPath(String path);
}
