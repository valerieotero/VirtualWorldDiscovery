package Classes;

import java.util.ArrayList;

public class fileNames {

	private static ArrayList<String> fileNames = new ArrayList<String>();
	
	public void save(String s) {
		fileNames.add(s);
	}
	
	public String load(int i) {
		return fileNames.get(i);
	}
	
	public static ArrayList<String> getFileName() {
		return fileNames;
	}
}
