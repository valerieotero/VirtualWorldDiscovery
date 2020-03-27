package Classes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Created by: Yamil J. Gonzalez
 * End:March 5/2020
 * 
 * Writer class has 4 methods:
 * open: tries to open the file that is sent to the method
 * close: will close the opened file
 * write: Will try to write the String that is being pass
 * newLine: it moves the cursor to a new line.
 * 
 */

public class Writer {
	private static BufferedWriter configFile;
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
	//Will try to move the cursor to the next line of the file
	public static void newLine() {
		try {
			configFile.newLine();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//Will try to save trees into a file
	public static void trees() {
		try {
			configFile.close();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//Write the building name and number into the file
	//Saves reference picture
	public void writingBuildingName(String s,String p, int w) {
		try {
			configFile.write("Building "+w+" = "+s);
			configFile.newLine();
			configFile.write(p);
			configFile.newLine();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	//Writes the wall, wall number and wall coordinates
	public void writingCoordinates(int n,int x, int y, int x1, int y1, int h, int w, String p) {
		try {
			configFile.write("Wall "+n+" = ("+x+","+y+")("+x1+","+y1+")("+h+","+w+")"+p);
			configFile.newLine();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}