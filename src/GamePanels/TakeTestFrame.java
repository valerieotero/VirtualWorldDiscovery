package GamePanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TakeTestFrame {
	
	private JFrame takeTestFrame;
	
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
	
	public int counter = 1;
	
	
	public TakeTestFrame() {
		if(counter == 1) {
			initialize();
		}
	}
	
	private void initialize() {
		counter = 0;
		takeTestFrame = new JFrame();
		takeTestFrame.setBounds(10, 480, 550, 250);
		takeTestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		takeTestFrame.getContentPane().setLayout(null);
		
		Font obj = new Font("Arial", Font.BOLD, 18);
		
		question = new JLabel(/*QandA.get(0)*/ "How Many Floors Does this Building have?");
		question.setFont(obj);
		question.setBounds(5, 0, 800, 30);
		takeTestFrame.getContentPane().add(question);
		
		answerA = new JLabel ("6");
		answerA.setFont(obj);
		answerA.setBounds(10, 40, 400, 30);
		takeTestFrame.getContentPane().add(answerA);
		
		answerB = new JLabel ("4");
		answerB.setFont(obj);
		answerB.setBounds(10, 80, 400, 30);
		takeTestFrame.getContentPane().add(answerB);
		
		answerC = new JLabel ("5");
		answerC.setFont(obj);
		answerC.setBounds(10, 120, 400, 30);
		takeTestFrame.getContentPane().add(answerC);
		
		answerD = new JLabel ("7");
		answerD.setFont(obj);
		answerD.setBounds(10, 160, 400, 30);
		takeTestFrame.getContentPane().add(answerD);
		
		optionA = new JButton("Select");
		optionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		optionA.setBounds(400, 40, 100, 30);
		takeTestFrame.getContentPane().add(optionA);
		
		optionB = new JButton("Select");
		optionB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeTestFrame.remove(takeTestFrame);
			}
		});
		optionB.setBounds(400, 80, 100, 30);
		takeTestFrame.getContentPane().add(optionB);
		
		optionC = new JButton("Select");
		optionC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		optionC.setBounds(400, 120, 100, 30);
		takeTestFrame.getContentPane().add(optionC);
		
		optionD = new JButton("Select");
		optionD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		optionD.setBounds(400, 160, 100, 30);
		takeTestFrame.getContentPane().add(optionD);
		
		takeTestFrame.setVisible(true);

	}
}