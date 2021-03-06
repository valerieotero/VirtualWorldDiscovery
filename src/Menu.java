import javax.swing.JFrame;

import DesignPanels.GameModePanel;

public class Menu {

	
	//Panel variables
	private JFrame frame;		
	GameModePanel gameModePanel;

	
	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initializeFrame();
	}

	public void initializeFrame() {

		frame = new JFrame();
		frame.setBounds(10, 10, 1220, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		gameModePanel = new GameModePanel(frame);
	}
}