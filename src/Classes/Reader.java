package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
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
	private static String name, question, background;
	private static String[] tokens = null;
	private static ArrayList<String> str = new ArrayList<String>();
	private static int amount = 0;

	//maps variables
	private static ArrayList<Integer> walls = new ArrayList<Integer>();
	private static ArrayList<String> pictures = new ArrayList<String>();
	private static ArrayList<String> buildingPictures = new ArrayList<String>();
	private static HashMap<Integer, LinkedList<Walls>> buildings = new HashMap<>();
	private static HashMap<Integer, String> buildingNames = new HashMap<>();		

	//question variables	
	private static ArrayList<HashMap<Integer, String>> questionList = new ArrayList<HashMap<Integer, String>>();
	private static ArrayList<HashMap<Integer,LinkedList<String>>> questionAnswersList = new ArrayList<HashMap<Integer,LinkedList<String>>>();
	private static HashMap<Integer,LinkedList<Integer>> buildingQuestions = new HashMap<>();

	//tree variables
	private static ArrayList<Integer> trees = new ArrayList<Integer>();
	private static HashMap<Integer,String> treeType = new HashMap<>();
	private static HashMap<Integer,LinkedList<treeLocation>> treeLocation = new HashMap<>();
	
		
	//GETTERS
	public static ArrayList<String> getBuildingPictures() {	return buildingPictures; }	
	public static HashMap<Integer, LinkedList<Walls>> getBuildings() { return buildings; }
	public static String getBackground() { return background; }
	public static int getAmount() { return amount; }
	public static HashMap<Integer, String> getTreeType() { return treeType;	}
	public static HashMap<Integer, LinkedList<treeLocation>> getTreeLocation() { return treeLocation; }
	public static HashMap<Integer, LinkedList<Integer>> getBuildingQuestions() { return buildingQuestions;	}
	public static ArrayList<HashMap<Integer, LinkedList<String>>> getQuestionAnswersList() { return questionAnswersList; }
	public static ArrayList<HashMap<Integer, String>> getQuestionList() { return questionList; }	
	
	
	//SETTERS
	public static void setBuildings(HashMap<Integer, LinkedList<Walls>> buildings) { Reader.buildings = buildings;	}	
	public static void setBuildingPictures(ArrayList<String> buildingPictures) { Reader.buildingPictures = buildingPictures; }	
	public static void setBackground(String background) { Reader.background = background; }
	public static void setAmount(int amount) { Reader.amount = amount; }
	public static void setTreeType(HashMap<Integer, String> treeType) {	Reader.treeType = treeType;	}	
	public static void setTreeLocation(HashMap<Integer, LinkedList<treeLocation>> treeLocation) { Reader.treeLocation = treeLocation; }
	public static void setBuildingQuestions(HashMap<Integer, LinkedList<Integer>> buildingQuestions) { Reader.buildingQuestions = buildingQuestions; }
	public static void setQuestionAnswersList(ArrayList<HashMap<Integer, LinkedList<String>>> questionAnswersList) { Reader.questionAnswersList = questionAnswersList; }
	public static void setQuestionList(ArrayList<HashMap<Integer, String>> questionList) { Reader.questionList = questionList; }
	
	/*Testing*/
	public static void main(String[] args) throws Exception {
				
		mapReaderController("Test");
		questionReaderController("Test");
		treeReaderController("Test");
		
//		for(String s : getBuildingPictures()) {
//			System.out.println("Building picture " + s);
//		}				
		System.out.println("Background: "+ getBackground());
//		System.out.println("Amount of building in the file = "+getAmount());
//		System.out.println("Walls of each building "+Collections.singletonList(getBuildings()));
//		System.out.println("Building Names "+Collections.singletonList(buildingNames));		
//		System.out.println("Treetype "+Collections.singletonList(treeType));
//		System.out.println("Tree location "+Collections.singletonList(treeLocation));	
		
	    
			
		
//		for(HashMap.Entry<Integer,LinkedList<Integer>> z : getBuildingQuestions().entrySet()) {
//			System.out.println("Building question numbers"+z);
//		}
//				
		
		//QUESTIONS	
		
		int randomNum = ThreadLocalRandom.current().nextInt(1, 7); //Inclusive, exclusive			
		System.out.println("Building Question: " + randomNum);
		
		
        //questionList
		for(HashMap.Entry<Integer,String> s : getQuestionList().get(0).entrySet()) {			
	
			if(s.getKey().equals(randomNum)) { //gives me a random question associated to a specific building
				  System.out.println(s.getValue());
			}
		}
			
		
		
		for(HashMap.Entry<Integer,LinkedList<String>> s : getQuestionAnswersList().get(0).entrySet()) {
			
			if(s.getKey() == randomNum) {
				
				for(String r : s.getValue()) {				

				System.out.println(r);
				}	
			}
		}
	}

	//Call this method of called form other classes
	public static void mapReaderController(String s) throws FileNotFoundException {
		mapReader(s);
	}

	public static void questionReaderController(String s) throws FileNotFoundException {
		questionReader(s);
	}
	
	public static void treeReaderController(String s) throws FileNotFoundException{
		treeReader(s);
	}

	//Uses the data in the tokens to subtract the necessary values
	//Of each wall
	private static void mapReader(String s) throws FileNotFoundException {
		mapTokenizer(s);
		String data = null;
		data = str.toString();		
		String[] arr = data.split(",|\\]");
		str.clear();
		int i = 0, count = 0, buildingNumnber = 0;
		if(i+7 < arr.length) {
			setAmount(Integer.parseInt(arr[i+6].trim()));
			setBackground(arr[i+7].trim());
		}
		while(i < arr.length) {
			if(arr[i].equals(" Building")){
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
		str.clear();
		//Testing purposes
		//		for(int i = 0; i <arr.length; i++) {
		//			System.out.println("location "+i+ " = "+arr[i]);
		//		}
		int i = 0, count = 0, questionNumber = 0, buildingNumber = 0;;
		while(i < arr.length) {
			if(arr[i].trim().equals("Building")){
				LinkedList<Integer> numQuestion = new LinkedList<Integer>();
				HashMap<Integer, String> questions = new HashMap<>();
				HashMap<Integer,LinkedList<String>> questionAnswers = new HashMap<>();
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
						getBuildingQuestions().put(buildingNumber, numQuestion);
						getQuestionList().add(questions);
						getQuestionAnswersList().add(questionAnswers);
						questionNumber = 0;
					}
				}
			}	
			else {
				i++;
			}
		}
	}

	private static void treeReader(String s) throws FileNotFoundException{
		treeTokenizer(s);
		String data = null;
		String[] arr = null;
		data = str.toString();		
		arr = data.split(",|\\]");
		str.clear();
		int treeID = 1, count = 0;
		for(int i = 5; i < arr.length; i++) {
			LinkedList<treeLocation> treeCoords = new LinkedList<treeLocation>();
			getTreeType().put(treeID, arr[i].trim());
			if(i+3 < arr.length) i=i+3;
			else break;
			while(count != 5) {
				try {
					trees.add(Integer.parseInt(arr[i].trim()));
				}catch(NumberFormatException ex){
				}
				i++;
				count++;
			}
			count = 0;
			i = i - 1;
			for(int a = 0; a < trees.size(); a+=4) {
				treeCoords.add(new treeLocation(trees.get(0), trees.get(1), trees.get(2), trees.get(3)));
			}
			getTreeLocation().put(treeID, treeCoords);
			treeID++;
			trees.clear();
		}
	}
	
	//Create tokens usinga a splitting function for trees
	private static void treeTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\Trees\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}

	//Create tokens using a splitting function for map
	private static void mapTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\Maps\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}

	//Create tokens using a splitting function for questions
	private static void questionTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\Questions\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,|=]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}
}