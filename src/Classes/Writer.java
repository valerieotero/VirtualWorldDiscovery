package Classes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/*
 * Created by: Yamil J. Gonzalez
 * End:March 5/2020
 * 
 * Writer class has 4 methods:
 * open: tries to open the file that is sent to the method
 * close: will close the opened file
 * write: Will try to write the String that is being pass
 * newLine: it moves the cursor to a new line.
 * buildingInfo: it add the building name, number and picture path to the file.
 */

public class Writer {
	private static FileWriter configFile;
	
	//Will try to open a file given a path
	public static void open(String fileName) {
		try {
			configFile = new FileWriter(fileName, true);
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
	
	public static void writeSpace(String sentence) {
		try {
			configFile.write(sentence);
			configFile.write(System.getProperty("line.separator"));
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//Will try to move the cursor to the next line of the file
	public static void newLine() {
		try {
			configFile.write(System.getProperty("line.separator"));
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
	
	//Will try to write the name, number and picture path of the building.
	public static void buildingInfo(String name, int number, File file) {
		try {
			configFile.append("Building "+number+" = "+name);
			configFile.append(System.getProperty("line.separator"));
			configFile.append(file.getPath());
			configFile.append(System.getProperty("line.separator"));
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}