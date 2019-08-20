package impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import fileIO.FileIO;

public class FileIOImpl implements FileIO {
	
	
	public List<String> fileRead(String path) {
		
		mkdirPath(path);
		
		List<String> list = null;
		try	(FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);)
		{
			list = new ArrayList<>();
			while (true) {
				String readLine = br.readLine();
				if (readLine == null) break;
				list.add(readLine);
			}
			
		} catch(Exception e) {
			
		}
		return list;
	}
	
	public void fileWrite(String path, String write, boolean chk) {
		mkdirPath(path);
		
		try	(FileWriter fw = new FileWriter(path, chk);
			BufferedWriter bw = new BufferedWriter(fw);)
			{
					if (write.equals("")) {
						bw.write("");
						return;
					}
					bw.write(write);
					if (chk) bw.write("\n");
			} catch(Exception e) {
				
			}
	}
	
	
	public void mkdirPath(String path) {
		File f = new File(path);
		
		if (!f.exists()) {
			String dir = f.getParent();
			f = new File(dir + "/");
			f.mkdirs();
		}
	}
	
}
