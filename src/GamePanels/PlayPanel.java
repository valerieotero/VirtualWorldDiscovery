package GamePanels;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Avatar.GameLoop;
import Avatar.GraphicsManager;
import Input.PlayingPanelInputHandler;


public class PlayPanel {
	
	String filesPath;
	JComboBox<String> comboBoxLoadMap;	
	private JPanel playPanel;
	String mapName;
	
	
	public PlayPanel(JFrame frame) {
		
		//PLAY PANEL
		playPanel = new JPanel();
		playPanel.setBounds(0, 0, 1220, 681);
		frame.getContentPane().add(playPanel);
		playPanel.setLayout(null);	
				
		//LABEL - LOAD MAP
		JLabel lblLoadMap = new JLabel("Choose map to play:");
		lblLoadMap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoadMap.setBounds(530, 164, 138, 23);
		playPanel.add(lblLoadMap);
		
		//LOAD MAP DROPDOWN
		comboBoxLoadMap = new JComboBox<String>();	
		filesPath = System.getProperty("user.dir");		
		File directory = new File(filesPath+"\\Maps");
		String[] fileList = directory.list();

		for(String name:fileList){
			comboBoxLoadMap.addItem(name);		
		} 		
		comboBoxLoadMap.setBounds(540, 198, 107, 20);
		playPanel.add(comboBoxLoadMap);
		
		//LOAD BUTTON
		JButton btnLoad = new JButton("Load");	
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mapName = (String) comboBoxLoadMap.getSelectedItem();
								
				PlayingFrame gameFrame = new PlayingFrame(); 
				gameFrame.setTitle(mapName);			

			}
		});
		btnLoad.setBounds(677, 197, 89, 23);
		playPanel.add(btnLoad);

	}

}
