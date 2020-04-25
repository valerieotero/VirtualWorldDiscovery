package GamePanels;
import javax.swing.JFrame;

/*
 * Class creates the frame where the questions appears. 
 * Then calls the TakeTestPanel class, where the labels and buttons are initiated.
 */
public class TakeTestFrame {

	public static JFrame frame; 

	
	TakeTestFrame() {
		
		initialize();
	}


	public void initialize() {

		frame = new JFrame(); 

		frame.setBounds(10, 480, 575, 293);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frame.getContentPane().setLayout(null);

		new TakeTestPanel();

		frame.setVisible(true);	

	}
}