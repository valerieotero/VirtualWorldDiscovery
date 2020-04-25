package GamePanels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.QuestionsAndAnswers;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class TakeTestPanel {

	public JPanel panel; 
	
	private JLabel question;
	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton optionD;

	static int questionCount = 0;	
	
	QuestionsAndAnswers qAndA;
	
	
	public TakeTestPanel() {
	
		panel = new JPanel();
		panel.setBounds(10, 11, 539, 232);
		TakeTestFrame.frame.setContentPane(panel);			
		panel.setVisible(true);		
		panel.setLayout(null);
		
		qAndA = new QuestionsAndAnswers();

		initialize();
	}	
	

	public void initialize() {				
			
		//QUESTION
		question = new JLabel(qAndA.randomQuestion);	
		question.setBounds(10, 21, 485, 22);
		panel.add(question);


		//LABELS (ANSWERS)	
		JLabel label_1 = new JLabel(qAndA.answers.get(0));
		label_1.setBounds(23, 63, 103, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel(qAndA.answers.get(1));
		label_2.setBounds(23, 107, 103, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel(qAndA.answers.get(2));
		label_3.setBounds(23, 154, 103, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel(qAndA.answers.get(3));
		label_4.setBounds(23, 198, 103, 14);
		panel.add(label_4);

		
		//OPTION 1
		optionA = new JButton("Select");		
		optionA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				panel.setVisible(false);
								
				if(questionCount < 4) {																
					new TakeTestPanel();
					questionCount++;	
					System.out.println("QuestionCount: " + questionCount);
				}
				else {
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));
					questionCount = 0;
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
				
				if(questionCount < 4) {																
					new TakeTestPanel();
					questionCount++;	
					System.out.println("QuestionCount: " + questionCount);
				}
				else {
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));
					questionCount = 0;
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
				
				if(questionCount < 4) {							
					new TakeTestPanel();
					questionCount++;	
					System.out.println("QuestionCount: " + questionCount);
				}
				else {
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));
					questionCount = 0;
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
				
				if(questionCount < 4) {																	
					new TakeTestPanel();
					questionCount++;
					System.out.println("QuestionCount: " + questionCount);	
				}
				else {
					TakeTestFrame.frame.dispatchEvent(new WindowEvent(TakeTestFrame.frame, WindowEvent.WINDOW_CLOSING));
					questionCount = 0;
				}				
			}
		});		
		optionD.setBounds(152, 194, 80, 23);
		panel.add(optionD);

	}
}