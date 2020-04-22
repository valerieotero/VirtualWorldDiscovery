package GamePanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.Reader;

public class TakeTestPanel extends JPanel{
	
	
	private JLabel question;
	
	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton optionD;
	
	private int buildingKey;
	
	private ArrayList<String> questions = new ArrayList<String>();
		
	private HashMap<Integer, String> questionsByKey= new HashMap<Integer, String>();
	
	
	//GETTER
	public int getBuildingKey() { return buildingKey; }

	//SETTER
	public void setBuildingKey(int buildingKey) { this.buildingKey = buildingKey; }
	
	
	public TakeTestPanel(JFrame frame, int key) {			
			
		frame.getContentPane().add(this);
		this.setVisible(true);	
		this.setBuildingKey(key);
		
		getQuestion();
		
		initialize();
		
		
	}
	
	public void initialize() {				
		
		Font obj = new Font("Arial", Font.BOLD, 18);
		setLayout(null);
		
		
		//LABELS
		JLabel label = new JLabel("1)");
		label.setBounds(23, 63, 46, 14);
		add(label);
		
		JLabel label_1 = new JLabel("2)");
		label_1.setBounds(23, 107, 46, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("3)");
		label_2.setBounds(23, 154, 46, 14);
		add(label_2);
		
		JLabel label_3 = new JLabel("4)");
		label_3.setBounds(23, 198, 46, 14);
		add(label_3);
		
		
		//QUESTION
		question = new JLabel(questions.get(0));
		question.setFont(obj);
		question.setBounds(23, 16, 374, 22);
		this.add(question);
		
		
		//BUTTONS (ANSWERS)		
		optionA = new JButton("Select");
		optionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		optionA.setBounds(43, 59, 76, 23);
		this.add(optionA);
		
		optionB = new JButton("Select");
		optionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//this.remove(this);
			}
		});
		optionB.setBounds(43, 194, 76, 23);
		this.add(optionB);
		
		optionC = new JButton("Select");
		optionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		optionC.setBounds(43, 150, 76, 23);
		this.add(optionC);
		
		optionD = new JButton("Select");
		optionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		optionD.setBounds(43, 103, 76, 23);
		this.add(optionD);			
		
	}
	
	public void getQuestion() {
		
		questionsByKey = Reader.getQuestionList().get(getBuildingKey()-1);
			
		
		for(HashMap.Entry<Integer, String> z : questionsByKey.entrySet()) {
			
				questions.add(z.getValue());			
				
		}
	}
	
}