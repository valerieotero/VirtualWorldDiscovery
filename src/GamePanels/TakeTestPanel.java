package GamePanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Reader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;


public class TakeTestPanel extends JPanel{


	private JLabel question;

	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton optionD;

	private int buildingKey;

	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();	
	
	//To be able to iterate and add each question regarding a building to an array list
	private HashMap<Integer, String> questionsByKey= new HashMap<Integer, String>(); 
	
	private HashMap<Integer,LinkedList<String>> answersByKey = new HashMap<Integer,LinkedList<String>>();

	
	//GETTER
	public int getBuildingKey() { return buildingKey; }

	//SETTER
	public void setBuildingKey(int buildingKey) { this.buildingKey = buildingKey; }
	
	int randomQuestoinNum;


	public TakeTestPanel(JFrame frame, int key) {			

		frame.getContentPane().add(this);
		this.setVisible(true);	
		this.setBuildingKey(key);
		this.setLayout(null);
		
		//Para que random number pueda match con los keys, since empiezan en 1
		randomQuestoinNum = ThreadLocalRandom.current().nextInt(1, 7); //Inclusive, exclusive 
		
		getQuestion();
		getAnswers(randomQuestoinNum);

		initialize(randomQuestoinNum);
	}

	public void initialize(int randomQuestoinNum) {				
		
		Font obj = new Font("Arial", Font.BOLD, 16);		
			
		//QUESTION
		question = new JLabel(questions.get(randomQuestoinNum-1));
		question.setFont(obj);
		question.setBounds(0, 21, 495, 22);
		this.add(question);


		//LABELS (ANSWERS)	
		JLabel label_1 = new JLabel(answers.get(0));
		label_1.setBounds(23, 63, 103, 14);
		add(label_1);

		JLabel label_2 = new JLabel(answers.get(1));
		label_2.setBounds(23, 107, 103, 14);
		add(label_2);

		JLabel label_3 = new JLabel(answers.get(2));
		label_3.setBounds(23, 154, 103, 14);
		add(label_3);

		JLabel label_4 = new JLabel(answers.get(3));
		label_4.setBounds(23, 198, 103, 14);
		add(label_4);

		
		//OPTION 1
		optionA = new JButton("Select");		
		optionA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		optionA.setBounds(152, 59, 80, 23);
		this.add(optionA);


		//OPTION 2
		optionB = new JButton("Select");
		optionB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		optionB.setBounds(152, 103, 80, 23);
		this.add(optionB);


		//OPTION 3
		optionC = new JButton("Select");
		optionC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		optionC.setBounds(152, 150, 80, 23);
		this.add(optionC);


		//OPTION 4
		optionD = new JButton("Select");
		optionD.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});		
		optionD.setBounds(152, 194, 80, 23);
		this.add(optionD);


	}

	public void getQuestion() {

		questionsByKey = Reader.getQuestionList().get(getBuildingKey()-1);			

		for(HashMap.Entry<Integer, String> z : questionsByKey.entrySet()) {

			questions.add(z.getValue());					
		}
	}

	public void getAnswers(int randomQuestoinNum) {	

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