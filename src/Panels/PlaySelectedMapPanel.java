package Panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlaySelectedMapPanel {

	private JPanel playingPanel;

	public PlaySelectedMapPanel (JFrame frame, String chosenMapName) {

		//PLAY SELECTED MAP PANEL
		playingPanel = new JPanel();
		playingPanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(playingPanel);
		playingPanel.setLayout(null);	
		frame.setTitle(chosenMapName);

	}

}
