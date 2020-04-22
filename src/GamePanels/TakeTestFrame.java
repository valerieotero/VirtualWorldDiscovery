package GamePanels;

import javax.swing.JFrame;


public class TakeTestFrame extends JFrame {


	TakeTestFrame(){
	}

	public void initialize(int key) {

		this.setBounds(10, 480, 575, 293);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.getContentPane().setLayout(null);
		this.setVisible(true);	

		TakeTestPanel takeTestPanel = new TakeTestPanel(this, key);		
		takeTestPanel.setBounds(10, 11, 539, 232);

	}
	
}