package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import GamePanels.PlayingPanel;

public class QuestionsAndAnswers {

	private int buildingKey;
	int randomQuestoinNum;
	
	public String randomQuestion; 
	
	private ArrayList<String> questions = new ArrayList<String>();
	public ArrayList<String> answers = new ArrayList<String>();	
	private static ArrayList<Integer> randQuestionList = new ArrayList<Integer>();
	
	//To be able to iterate and add each question regarding a building to an array list
	private HashMap<Integer, String> questionsByKey= new HashMap<Integer, String>(); 
	private HashMap<Integer,LinkedList<String>> answersByKey = new HashMap<Integer,LinkedList<String>>();

			
	//GETTER
	public int getBuildingKey() { return buildingKey; }

	//SETTER
	public void setBuildingKey(int buildingKey) { this.buildingKey = buildingKey; }

	public QuestionsAndAnswers() {
		
		this.setBuildingKey(PlayingPanel.getBuildingKey());

		//Para que random number pueda match con los keys, since empiezan en 1
		randomQuestoinNum = ThreadLocalRandom.current().nextInt(1, 7); //Inclusive, exclusive 
		System.out.println("Random number before adding to list "+randomQuestoinNum);
		if(!(randQuestionList.contains(randomQuestoinNum))) {
			System.out.println("After entry "+randomQuestoinNum);
			randQuestionList.add(randomQuestoinNum);
			System.out.println("List size "+randQuestionList);
		}else {
			System.out.println("Is inside the list "+randomQuestoinNum);
			new QuestionsAndAnswers();
		}
		if(randQuestionList.size() == 4) {
			randQuestionList.clear();
		}
		
		getBuildingQuestions();
		
		randomQuestion = questions.get(randomQuestoinNum-1);
		
		getBuildingAnswers(randomQuestoinNum);		
	}

	/* Author: Valerie Otero | Date: April 25 2020
	 * This methods gets the questions regarding the collided building. 
	 * It then adds them to a local list to be selected randomly. */
	public void getBuildingQuestions() {

		questionsByKey = Reader.getQuestionList().get(getBuildingKey()-1);			

		for(HashMap.Entry<Integer, String> z : questionsByKey.entrySet()) {

			questions.add(z.getValue());					
		}
	}

	
	/* Author: Valerie Otero | Date: April 25 2020
	 * This methods gets the answers regarding the collided building and the random question number. 
	 * It then adds them to a local list to be matched with the question. */
	public void getBuildingAnswers(int randomQuestoinNum) {	

		//answers regarding a specific building
		answersByKey = Reader.getQuestionAnswersList().get(getBuildingKey()-1);

		for(HashMap.Entry<Integer,LinkedList<String>> buildingAnswers : answersByKey.entrySet()) {

			if(buildingAnswers.getKey() == randomQuestoinNum) {	//get the answers for the random question		

				for(String answ : buildingAnswers.getValue()) {

					answers.add(answ);				
				}
			}
		}		
	}
}
