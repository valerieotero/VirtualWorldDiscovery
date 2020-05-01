package Classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/*
 * Created by: Yamil J. Gonzalez
 *
 * basic file format......
 * 
 * map
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
 * 
 * question
 * Basic inputs
 * Building = String
 * Questions = String
 * Answer
 * .....
 * 
 * tree
 * basic input
 * Treetype = coordinates
 * ....
 * 
 * All methods worked by: Yamil Gonzalez
 * Last worked: 4/30/2020
 */

public class Reader {

	//General variables
	private static String name, question, background, backW, backH;
	private static String[] tokens = null;
	private static ArrayList<String> str = new ArrayList<String>();
	private static int amount = 0;

	//maps variables
	private static ArrayList<Integer> walls = new ArrayList<Integer>();
	private static ArrayList<String> pictures = new ArrayList<String>();
	private static ArrayList<String> buildingPictures = new ArrayList<String>();
	private static HashMap<Integer, LinkedList<Walls>> buildings = new HashMap<>();
	private static HashMap<Integer, String> buildingNames = new HashMap<>();		
	private static ArrayList<String> locations = new ArrayList<String>();
	private static ArrayList<String> imageSize = new ArrayList<String>();
	
	//question variables	
	private static ArrayList<HashMap<Integer, String>> questionList = new ArrayList<HashMap<Integer, String>>();
	private static ArrayList<HashMap<Integer,LinkedList<String>>> questionAnswersList = new ArrayList<HashMap<Integer,LinkedList<String>>>();
	private static HashMap<Integer,LinkedList<Integer>> buildingQuestions = new HashMap<>();

	//tree variables
	private static ArrayList<Integer> trees = new ArrayList<Integer>();
	private static HashMap<Integer,String> treeType = new HashMap<>();
	private static HashMap<Integer,LinkedList<treeLocation>> treeLocation = new HashMap<>();
	private static ArrayList<Integer> treeInfo = new ArrayList<Integer>();
	
	//GETTERS
	public static String getBackW() { return backW;	}
	public static String getBackH() { return backH; }
	public static ArrayList<String> getBuildingPictures() {	return buildingPictures; }	
	public static HashMap<Integer, LinkedList<Walls>> getBuildings() { return buildings; }
	public static String getBackground() { return background; }
	public static int getAmount() { return amount; }
	public static HashMap<Integer, String> getTreeType() { return treeType;	}
	public static HashMap<Integer, LinkedList<treeLocation>> getTreeLocation() { return treeLocation; }
	public static HashMap<Integer, LinkedList<Integer>> getBuildingQuestions() { return buildingQuestions;	}
	public static ArrayList<HashMap<Integer, LinkedList<String>>> getQuestionAnswersList() { return questionAnswersList; }
	public static ArrayList<HashMap<Integer, String>> getQuestionList() { return questionList; }	
	public static ArrayList<String> getLocations() {return locations;}
	public static ArrayList<Integer> getTreeInfo() {return treeInfo;}
	public static ArrayList<String> getImageSize() {return imageSize;}
	
	//SETTERS
	public static void setBackW(String backW) { Reader.backW = backW; }
	public static void setBackH(String backH) {	Reader.backH = backH; }
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
		showMeInfo();
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
	//Of each wall inside the map file
	private static void mapReader(String s) throws FileNotFoundException {
		mapTokenizer(s);
		String data = null;
		data = str.toString();		
		String[] arr = data.split(",|\\]");
		str.clear();
		int i = 0, count = 0, buildingNumnber = 0;
		if(i+9 < arr.length) {
			setAmount(Integer.parseInt(arr[i+6].trim()));
			setBackground(arr[i+7].trim());
			setBackW(arr[i+8].trim());
			setBackH(arr[i+9].trim());
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
							locations.add(arr[i].trim());
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
						locations.add(pictures.get(b));
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

	//Uses the data in the tokens to subtract the necessary values
	//Of each question, each question has 4 answers
	//those answers also are store in a map
	private static void questionReader(String s) throws FileNotFoundException {
		questionTokenizer(s);		
		String data = null;
		String[] arr = null;
		data = str.toString();		
		arr = data.split(",|\\]");
		str.clear();
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

	//Uses the data in the tokens to subtract the necessary values
	//Of each tree getting the tree type and coordinates.
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
			treeInfo.add(treeID);
			if(i+3 < arr.length) i=i+3;
			else break;
			while(count != 5) {
				try {
					trees.add(Integer.parseInt(arr[i].trim()));
					treeInfo.add(Integer.parseInt(arr[i].trim()));
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
		Scanner scanner = new Scanner(new File("C:\\Users\\juang\\git\\VirtualWorldDiscovery\\Trees\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}

	//Create tokens using a splitting function for map
	private static void mapTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\juang\\git\\VirtualWorldDiscovery\\Maps\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}

	//Create tokens using a splitting function for questions
	private static void questionTokenizer(String file) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("C:\\Users\\juang\\git\\VirtualWorldDiscovery\\Questions\\"+file));
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,|=]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}
	}
	
	public static void showMeInfo() {
		String [] list = new String[getLocations().size()];
		Integer[] imglist = new Integer[getTreeInfo().size()];
		String[] treelist = new String[getTreeLocation().size()];
		//String[] imageSize = new String[getImageSize().size()];
		
		getImageSize().add(getBackW());
		getImageSize().add(getBackH());
		
		for(int i = 0; i < list.length ; i ++) {
			list[i] = getLocations().get(i);
				System.out.println(list[i]);
		}
//		for(int i = 0; i < imglist.length ; i ++) {
//			imglist[i] = getTreeInfo().get(i);
//				System.out.println(imglist[i]);
//		}
		
//		for(int i = 0; i < imageSize.size() ; i ++) {
//				System.out.println(getImageSize().get(i));
//		}
		
//		for(int i = 0; i < treelist.length ; i ++) {
//			//imglist[i] = getTreeLocation().get(i).toString();
//			treelist[i] = getTreeLocation().toString();
//				System.out.println(treelist[i]);
//		}

		
	}
}