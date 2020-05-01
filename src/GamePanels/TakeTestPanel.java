package GamePanels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.QuestionsAndAnswers;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.HashMap;

public class TakeTestPanel {

	public JPanel panel; 

	private JLabel question;
	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton optionD;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	static int questionCount = 0;	
	private int buildingKey;
	
	QuestionsAndAnswers qAndA;
	
	int a = 0;
	int b = 0;	
	int c = 0;
	int d = 0;
	
	boolean firstRun = true;  //because this class calls him self a control variable is needed, for values not to be replaced.
	private String correct;	
	public static HashMap<Integer,Integer> buildingCorrectAnswers = new HashMap<>();

	
	
	public TakeTestPanel() {

		panel = new JPanel();
		panel.setBounds(10, 11, 539, 232);
		TakeTestFrame.frame.setContentPane(panel);			
		panel.setVisible(true);		
		panel.setLayout(null);

		this.buildingKey = PlayingPanel.getBuildingKey();
		
		qAndA = new QuestionsAndAnswers();
		if(firstRun) {
			correct = qAndA.answers.get(0);			
			firstRun = false;
		}
		initialize();
	}	


	public void initialize() {			

		//QUESTION
		question = new JLabel(qAndA.randomQuestion);	
		question.setBounds(10, 21, 485, 22);
		panel.add(question);

		Collections.shuffle(qAndA.answers);
		//LABELS (ANSWERS)	
		label_1 = new JLabel(qAndA.answers.get(0));
		label_1.setBounds(23, 63, 103, 14);
		panel.add(label_1);

		label_2 = new JLabel(qAndA.answers.get(1));
		label_2.setBounds(23, 107, 103, 14);
		panel.add(label_2);

		label_3 = new JLabel(qAndA.answers.get(2));
		label_3.setBounds(23, 154, 103, 14);
		panel.add(label_3);

		label_4 = new JLabel(qAndA.answers.get(3));
		label_4.setBounds(23, 198, 103, 14);
		panel.add(label_4);


		//OPTION 1
		optionA = new JButton("Select");		
		optionA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				panel.setVisible(false);

				if(questionCount < 3) {
					questionCount++;
					checkAnswer(1);
					new TakeTestPanel();				
				}
				else {
					questionCount = 0;
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));					
				}
			}
		});
		optionA.setBounds(152, 59, 80, 23);
		panel.add(optionA);


		//OPTION 2
		optionB = new JButton("Select");
		optionB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				panel.setVisible(false);

				if(questionCount < 3) {
					questionCount++;
					checkAnswer(2);
					new TakeTestPanel();				
				}
				else {
					questionCount = 0;
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));					
				}
			}
		});
		optionB.setBounds(152, 103, 80, 23);
		panel.add(optionB);


		//OPTION 3
		optionC = new JButton("Select");
		optionC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				panel.setVisible(false);

				if(questionCount < 3) {	
					questionCount++;
					checkAnswer(3);
					new TakeTestPanel();					
				}
				else {
					questionCount = 0;
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));					
				}
			}
		});
		optionC.setBounds(152, 150, 80, 23);
		panel.add(optionC);


		//OPTION 4
		optionD = new JButton("Select");
		optionD.addMouseListener(new MouseAdapter() {
			@Override			
			public void mouseClicked(MouseEvent arg0) {

				panel.setVisible(false);

				if(questionCount < 3) {
					questionCount++;
					checkAnswer(4);					
					new TakeTestPanel();					
				}
				else {
					questionCount = 0;
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));
				}				
			}
		});		
		optionD.setBounds(152, 194, 80, 23);
		panel.add(optionD);

	}

	//Created by Yamil Gonzalez
	//April 4/28/2020
	//check if the answer is correct
	//the method takes an ID, this ID is the number of the selected answer (1 to 4)
	//then verified with the correct answer, if correct add to the count if not just 
	//break
	void checkAnswer(int ID) {
		switch(ID) {
		
		case 1:
			
			if(label_1.getText().equals(correct)) {			

				if(buildingCorrectAnswers.containsKey(buildingKey)) {

					a = buildingCorrectAnswers.get(buildingKey);				
				}

				buildingCorrectAnswers.put(buildingKey, (a+1));
			}			
			break;
			
		case 2:			
			
			if(label_2.getText().equals(correct)) {						
				
				if(buildingCorrectAnswers.containsKey(buildingKey)) {

					b = buildingCorrectAnswers.get(buildingKey);
				}

				buildingCorrectAnswers.put(buildingKey, (b+1));
			}			
			break;
			
		case 3:
			
			if(label_3.getText().equals(correct)) {				
				
				if(buildingCorrectAnswers.containsKey(buildingKey)) {

					c = buildingCorrectAnswers.get(buildingKey);

				}
				buildingCorrectAnswers.put(buildingKey, (c+1));
			}			
			break;
						
		case 4:			
			
			if(label_4.getText().equals(correct)) {				
				
				if(buildingCorrectAnswers.containsKey(buildingKey)) {

					d = buildingCorrectAnswers.get(buildingKey);
				}
				
				buildingCorrectAnswers.put(buildingKey, (d+1));
			}			
			break;
		}
	}
}