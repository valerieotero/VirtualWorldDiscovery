import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	private static BufferedWriter configFile;
	
	//main tester
	//delete after use
//	public static void main(String[] args) {
//		String f = "configuration";
//		String w = "I can open the file";
//		open(f);
//		write(w);
//		write("im yamil");
//		close();
//	}

	//Will try to open the file wanted
	public static void open(String fileName) {
		try {
			configFile = new BufferedWriter(new FileWriter(fileName, true));
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//Will try to close the file that is already open
	public static void close() {
		try {
			configFile.close();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//Will try to write a sentence into the file
	public static void write(String sentence) {
		try {
			configFile.write(sentence);
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void newLine() {
		try {
			configFile.newLine();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
