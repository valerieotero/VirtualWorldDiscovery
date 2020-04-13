package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import Panels.NewBuildingFrame;

/*
 * Created by: Yamil J. Gonzalez
 * Still going
 * 
 * It has two main methods, a helper and a main
 * Main: testing
 * readerController: when called it will call the other two methods
 * tokenizer: takes the input file and split it into tokens, that holds the necessary data.
 * reader: takes the token data, split it into necessary values to later store them in the necessary data structures 
 *
 * Basic inputs:
 * String = String
 * 
 * String integer = String
 * Path\\String.png
 * String integer = (#,#)(#,#)(#,#)String.png
 * String integer = (#,#)(#,#)(#,#)String.png
 * String integer = (#,#)(#,#)(#,#)String.png
 * String integer = (#,#)(#,#)(#,#)String.png
 * ....
 */

public class Reader {

	//General variables
	private static String name, temp, question;
	private static String[] tokens = null;
	private static ArrayList<String> str = new ArrayList<String>();
	private static int amount = 0;

	//maps variables
	private static ArrayList<Integer> walls = new ArrayList<Integer>();
	private static ArrayList<String> pictures = new ArrayList<String>();
	private static ArrayList<String> buildingPictures = new ArrayList<String>();
	private static LinkedList<Walls> space = new LinkedList<Walls>();
	private static HashMap<Integer, LinkedList<Walls>> buildings = new HashMap<>();
	private static HashMap<Integer, String> buildingNames = new HashMap<>();


	//question variables
	static NewBuildingFrame NBF;
	private static HashMap<Integer, String> questions = new HashMap<>();
	private static HashMap<Integer,LinkedList<Integer>> buildingQuestions = new HashMap<>();
	private static HashMap<Integer,LinkedList<String>> questionAnswers = new HashMap<>();

	/*Testing*/
	public static void main(String[] args) throws Exception {
		mapReaderController("UPR");
		questionReaderController("UPR");
		for(String s : buildingPictures) {
			//System.out.println("Building picture "+s);
		}
		System.out.println("Amount of building in the file = "+amount);
		System.out.println("Walls of each building "+Collections.singletonList(buildings));
		System.out.println("Building Names "+Collections.singletonList(buildingNames));
		System.out.println("Building question numbers "+Collections.singletonList(buildingQuestions));
		System.out.println("Building questions "+Collections.singletonList(questions));
		System.out.println("Question answers "+Collections.singletonList(questionAnswers));
	}

	//Call this method of called form other classes
	public static void mapReaderController(String s) throws FileNotFoundException {
		mapReader(s);

	}

	public static void questionReaderController(String s) throws FileNotFoundException {
		questionReader(s);
	}

	//Uses the data in the tokens to subtract the necessary values
	//Of each wall
	private static void mapReader(String s) throws FileNotFoundException {
		mapTokenizer(s);
		String data = null;
		data = str.toString();		
		String[] arr = data.split(",|\\]");
		//Testing
//		for(int i = 0; i <arr.length; i++) {
//			System.out.println("location "+i+ " = "+arr[i]);
//		}
		int i = 0, count = 0, buildingNumnber = 0;
		while(i < arr.length) {
			if(arr[i].equals(" Building")){
				amount = Integer.parseInt(arr[i-1].trim());
				name = arr[i+3].trim();
				buildingPictures.add(arr[i+4].trim());
				buildingNumnber++;
				LinkedList<Walls> lines = new LinkedList<Walls>();
				if(i+6 < arr.length) i = i+6;
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
					buildings.put(buildingNumnber, lines);
					buildingNames.put(buildingNumnber, name);
					walls.clear();
				}
				i++;
			}
			else {
				i++;
			}
		}
	}

	private static void questionReader(String s) throws FileNotFoundException {
		questionTokenizer(s);
		String data = null;
		String[] arr = null;
		data = str.toString();		
		arr = data.split(",|\\]");
		//Testing purposes
		//		for(int i = 0; i <arr.length; i++) {
		//			System.out.println("location "+i+ " = "+arr[i]);
		//		}
		int i = 0, count = 0, questionNumber = 0, buildingNumber = 0;;
		while(i < arr.length) {
			if(arr[i].trim().equals("Building")){
				LinkedList<Integer> numQuestion = new LinkedList<Integer>();
				buildingNumber++;
				name = arr[i+2].trim();
				if(i+2 < arr.length) i = i+2;
				else break;
				while(i < arr.length && arr[i].trim().equals("Question")){
					LinkedList<String> answers = new LinkedList<String>();
					questionNumber++;
					question = arr[i+1].trim();
					if(i+2 < arr.length) i = i+2;
					else break;
					while(count < 4) {
						answers.add(arr[i].trim());
						i++;
						count++;
					}
					numQuestion.add(questionNumber);
					questionAnswers.put(questionNumber, answers);
					questions.put(questionNumber, question);
					count = 0;
					if(questionNumber >= 6) {
						buildingQuestions.put(buildingNumber, numQuestion);
						questionNumber = 0;

					}
				}
			}	
			else {
				i++;
			}
		}
	}

	private static void treeTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\tree\\"+file));
	}

	//Create tokens using a splitting function for map
	private static void mapTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\maps\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}

	//Create tokens using a splitting function for questions
	private static void questionTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\questions\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,|=]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}
}