import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * 
 * Created by: Yamil J. Gonzalez
 * Still going
 * 
 * It has two main methods, a helper and a main
 * Main: testing
 * readerController: when called it will call the other two methods
 * tokenizer: takes the input file and split it into tokens, that holds the necessary data.
 * reader: takes the token data, split it into necessary values to later store them in the necessary data structures 
 * 
 * TO-DO
 * Basic inputs:
 * String = String
 * 
 * String number = String
 * String number = (#,#)(#,#)(#,#)String.png
 * String number = (#,#)(#,#)(#,#)String.png
 * String number = (#,#)(#,#)(#,#)String.png
 * String number = (#,#)(#,#)(#,#)String.png
 * 
 */

public class Reader {
	static String[] tokens = null;
	static ArrayList<String> str = new ArrayList<String>();
	static ArrayList<Integer> walls = new ArrayList<Integer>();
	static ArrayList<String> pictures = new ArrayList<String>();
	static LinkedList<Walls> space = new LinkedList<Walls>();
	static HashMap<String, LinkedList<Walls>> buildings = new HashMap<>();
	static String name, temp;
	

	public static void main(String[] args) throws Exception {
		readerController();
		System.out.println(Collections.singletonList(buildings));
	}
	
	public static void readerController() throws FileNotFoundException {
		tokenizer();
		reader();
	}

	//Create tokens using a splitting function
	public static void tokenizer() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\src\\Configuration_test"));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}

	//Uses the data in the tokens to subtract the necessary values
	//Of each wall
	public static void reader() {
		String data = str.toString();		
		String[] arr = data.split(",|\\]");
		int i = 0, count = 0;
		while(i < arr.length) {
			if(arr[i].equals(" Building")){
				name = arr[i+3].trim();
				LinkedList<Walls> lines = new LinkedList<Walls>();
				if(i+5 < arr.length) i = i+5;
				else break;
				while(i < arr.length && arr[i].equals(" Wall")){
					if(i+4 < arr.length)i = i+4;
					else break;
					while(count != 8) {
						try {
							walls.add(Integer.parseInt(arr[i].trim()));
						}catch(NumberFormatException ex){
						}
						i++;
						count++;
					}
					pictures.add(arr[i].trim());
					count = 0;
					i++;
					for(int a = 0, b = 0; a < walls.size() && b < pictures.size(); a+=6, b++) {
						lines.add(new Walls(walls.get(a),walls.get(a+1),walls.get(a+2),walls.get(a+3),walls.get(a+4), walls.get(a+5), pictures.get(b)));
					}
					buildings.put(name, lines);
					walls.clear();
				}
				i++;
			}
			else {
				i++;
			}
		}
	}
}
