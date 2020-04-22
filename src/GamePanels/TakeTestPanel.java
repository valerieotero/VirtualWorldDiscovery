package GamePanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TakeTestPanel extends JPanel{
	
	
	private JLabel question;
	private JLabel answerA;
	private JLabel answerB;
	private JLabel answerC;
	private JLabel answerD;
	
	private JButton optionA;
	private JButton optionB;
	private JButton optionC;
	private JButton optionD;
	
	private ArrayList<String>  qAndA = new ArrayList<String>();
		
	
	public TakeTestPanel(JFrame frame) {			
			
		frame.getContentPane().add(this);
		this.setVisible(true);	
		
		initialize();
	}
	
	public void initialize() {				
		
		Font obj = new Font("Arial", Font.BOLD, 18);
		setLayout(null);
		
		question = new JLabel(/*QandA.get(0)*/ "How Many Floors Does this Building have?");
		question.setFont(obj);
		question.setBounds(23, 16, 374, 22);
		this.add(question);
		
		answerA = new JLabel ("6");
		answerA.setFont(obj);
		answerA.setBounds(28, 49, 10, 22);
		this.add(answerA);
		
		answerB = new JLabel ("4");
		answerB.setFont(obj);
		answerB.setBounds(28, 82, 10, 22);
		this.add(answerB);
		
		answerC = new JLabel ("5");
		answerC.setFont(obj);
		answerC.setBounds(28, 106, 10, 22);
		this.add(answerC);
		
		answerD = new JLabel ("7");
		answerD.setFont(obj);
		answerD.setBounds(28, 139, 10, 22);
		this.add(answerD);
		
		optionA = new JButton("Select");
		optionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		optionA.setBounds(441, 51, 76, 23);
		this.add(optionA);
		
		optionB = new JButton("Select");
		optionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//this.remove(this);
			}
		});
		optionB.setBounds(441, 141, 76, 23);
		this.add(optionB);
		
		optionC = new JButton("Select");
		optionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		optionC.setBounds(441, 108, 76, 23);
		this.add(optionC);
		
		optionD = new JButton("Select");
		optionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		optionD.setBounds(441, 82, 76, 23);
		this.add(optionD);
		
		
	}
}