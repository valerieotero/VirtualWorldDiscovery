package DesignPanels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Classes.Writer;
import Classes.fileNames;


public class TestCreatorFrame {

	public static int qCount;
	private JFrame testFrame;

	private JButton btnEnter;

	private JTextField textFieldQuestion1;
	private JTextField textFieldQuestion2;
	private JTextField textFieldQuestion3;
	private JTextField textFieldQuestion4;
	private JTextField textFieldQuestion5;
	private JTextField textFieldQuestion6;

	private JTextField textFieldAnswer1;
	private JTextField textFieldAnswer2;
	private JTextField textFieldAnswer3;
	private JTextField textFieldAnswer4;	
	private JTextField textFieldAnswer5;
	private JTextField textFieldAnswer6;

	private String comboQandA;
	public int buildingNumber;
	fileNames locations = new fileNames();
	
	public HashMap<Integer, String> mapQuestions = new HashMap<Integer, String>();

	Writer writer;

	public TestCreatorFrame() {
		newTestCreator();
	}

	public int getQCount() {
		return qCount;
	}

	public void setQCount(int newQCount) {
		qCount = newQCount;
	}

	public String getComboQAndA() {
		return comboQandA;
	}

	public void setComboQAndA (String s) {
		comboQandA = s;
	}

	private void newTestCreator() {

		//FRAME CREATOR
		testFrame = new JFrame();
		testFrame.setBounds(100,100, 450, 570);
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		testFrame.getContentPane().setLayout(null);

		//LABEL - QUESTION #1
		JLabel lblQuestion1 = new JLabel("Question #1 :");
		lblQuestion1.setBounds(20, 20, 100, 15);
		testFrame.getContentPane().add(lblQuestion1);

		//QUESTION #1 INPUT FIELD
		textFieldQuestion1 = new JTextField();
		textFieldQuestion1.setBounds(120, 20, 300, 18);
		testFrame.getContentPane().add(textFieldQuestion1);

		//LABEL - ANSWERs #1
		JLabel lblAnswer1 = new JLabel("Answers separated by a comma( , ) :");
		lblAnswer1.setBounds(20, 45, 250, 15);
		testFrame.getContentPane().add(lblAnswer1);

		//ANSWERS #1 INPUT FIELD
		textFieldAnswer1 = new JTextField();
		textFieldAnswer1.setBounds(20, 65, 400, 18);
		testFrame.getContentPane().add(textFieldAnswer1);


		//LABEL - QUESTION #2
		JLabel lblQuestion2 = new JLabel("Question #2 :");
		lblQuestion2.setBounds(20, 100, 100, 15);
		testFrame.getContentPane().add(lblQuestion2);

		//QUESTION #2 INPUT FIELD
		textFieldQuestion2 = new JTextField();
		textFieldQuestion2.setBounds(120, 100, 300, 18);
		testFrame.getContentPane().add(textFieldQuestion2);

		//LABEL - ANSWERs #2
		JLabel lblAnswer2 = new JLabel("Answers separated by a comma( , ) :");
		lblAnswer2.setBounds(20, 125, 250, 15);
		testFrame.getContentPane().add(lblAnswer2);

		//ANSWERS #2 INPUT FIELD
		textFieldAnswer2 = new JTextField();
		textFieldAnswer2.setBounds(20, 145, 400, 18);
		testFrame.getContentPane().add(textFieldAnswer2);


		//LABEL - QUESTION #3
		JLabel lblQuestion3 = new JLabel("Question #3 :");
		lblQuestion3.setBounds(20, 180, 100, 15);
		testFrame.getContentPane().add(lblQuestion3);

		//QUESTION #3 INPUT FIELD
		textFieldQuestion3 = new JTextField();
		textFieldQuestion3.setBounds(120, 180, 300, 18);
		testFrame.getContentPane().add(textFieldQuestion3);

		//LABEL - ANSWERs #3
		JLabel lblAnswer3 = new JLabel("Answers separated by a comma( , ) :");
		lblAnswer3.setBounds(20, 205, 250, 15);
		testFrame.getContentPane().add(lblAnswer3);

		//ANSWERS #3 INPUT FIELD
		textFieldAnswer3 = new JTextField();
		textFieldAnswer3.setBounds(20, 225, 400, 18);
		testFrame.getContentPane().add(textFieldAnswer3);


		//LABEL - QUESTION #4
		JLabel lblQuestion4 = new JLabel("Question #4 :");
		lblQuestion4.setBounds(20, 260, 100, 15);
		testFrame.getContentPane().add(lblQuestion4);

		//QUESTION #4 INPUT FIELD
		textFieldQuestion4 = new JTextField();
		textFieldQuestion4.setBounds(120, 260, 300, 18);
		testFrame.getContentPane().add(textFieldQuestion4);

		//LABEL - ANSWERs #4
		JLabel lblAnswer4 = new JLabel("Answers separated by a comma( , ) :");
		lblAnswer4.setBounds(20, 285, 250, 15);
		testFrame.getContentPane().add(lblAnswer4);

		//ANSWERS #4 INPUT FIELD
		textFieldAnswer4 = new JTextField();
		textFieldAnswer4.setBounds(20, 305, 400, 18);
		testFrame.getContentPane().add(textFieldAnswer4);


		//LABEL - QUESTION #5
		JLabel lblQuestion5 = new JLabel("Question #5 :");
		lblQuestion5.setBounds(20, 340, 100, 15);
		testFrame.getContentPane().add(lblQuestion5);

		//QUESTION #5 INPUT FIELD
		textFieldQuestion5 = new JTextField();
		textFieldQuestion5.setBounds(120, 340, 300, 18);
		testFrame.getContentPane().add(textFieldQuestion5);

		//LABEL - ANSWERs #5
		JLabel lblAnswer5 = new JLabel("Answers separated by a comma( , ) :");
		lblAnswer5.setBounds(20, 365, 250, 15);
		testFrame.getContentPane().add(lblAnswer5);

		//ANSWERS #5 INPUT FIELD
		textFieldAnswer5 = new JTextField();
		textFieldAnswer5.setBounds(20, 385, 400, 18);
		testFrame.getContentPane().add(textFieldAnswer5);


		//LABEL - QUESTION #6
		JLabel lblQuestion6 = new JLabel("Question #6 :");
		lblQuestion6.setBounds(20, 420, 100, 15);
		testFrame.getContentPane().add(lblQuestion6);

		//QUESTION #6 INPUT FIELD
		textFieldQuestion6 = new JTextField();
		textFieldQuestion6.setBounds(120, 420, 300, 18);
		testFrame.getContentPane().add(textFieldQuestion6);

		//LABEL - ANSWERs #6
		JLabel lblAnswer6 = new JLabel("Answers separated by a comma( , ) :");
		lblAnswer6.setBounds(20, 445, 250, 15);
		testFrame.getContentPane().add(lblAnswer6);

		//ANSWERS #6 INPUT FIELD
		textFieldAnswer6 = new JTextField();
		textFieldAnswer6.setBounds(20, 465, 400, 18);
		testFrame.getContentPane().add(textFieldAnswer6);


		//ENTER BUTTON
		btnEnter = new JButton("Enter");
		btnEnter.setBounds(320, 505, 100, 18);
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				questionWriter();
				System.out.println("Added questions to file "+locations.load(1));
			}
		});
		this.setComboQAndA(comboQandA);
		testFrame.getContentPane().add(btnEnter);

		testFrame.setVisible(true);
	}
	
	//Author: Yamil Gonzalez
	//Last edited: 4/15/2020
	//Takes the questions and answers and write them to the correct file
	public void questionWriter() {
		Writer.open(locations.load(1));
		Writer.writeSpace("Building = "+NewBuildingFrame.getBuildingName());
		Writer.writeSpace("Question = "+textFieldQuestion1.getText());
		answerSpliter(textFieldAnswer1.getText());
		Writer.writeSpace("Question = "+textFieldQuestion2.getText());
		answerSpliter(textFieldAnswer2.getText());
		Writer.writeSpace("Question = "+textFieldQuestion3.getText());
		answerSpliter(textFieldAnswer3.getText());
		Writer.writeSpace("Question = "+textFieldQuestion4.getText());
		answerSpliter(textFieldAnswer4.getText());
		Writer.writeSpace("Question = "+textFieldQuestion5.getText());
		answerSpliter(textFieldAnswer5.getText());
		Writer.writeSpace("Question = "+textFieldQuestion6.getText());
		answerSpliter(textFieldAnswer6.getText());
		Writer.close();
	}
	
	//Author: Yamil Gonzalez
	//Last edited: 4/15/2020
	//Helper method to split the main array by the , to write the answers in the correct
	//spaces
	public void answerSpliter(String s) {
		String[] arr = s.split(",");
		for(int i = 0; i < arr.length; i++) {
			Writer.writeSpace(arr[i]);
		}
		for(int i = 0; i < arr.length; i++) {
			arr[i] = null;
		}
	}
}
