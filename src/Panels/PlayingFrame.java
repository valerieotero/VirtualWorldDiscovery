package Panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Avatar.GameLoop;
import Avatar.GraphicsManager;
import Avatar.KeysLogic;
import Input.PlayingPanelInputHandler;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class PlayingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public int buildingCount = 0;

	
	public PlayingFrame() {			
		initialize();		
	}

	
	private void initialize() {
		
		this.setBounds(10, 10, 1220, 720); //Same as the original frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
		
		KeysLogic keyLogic = new KeysLogic(); 
		PlayingPanelInputHandler inputHandler = new PlayingPanelInputHandler(); 
		GraphicsManager graphicsMan = new GraphicsManager(); 				
									
		this.addKeyListener(inputHandler);									
						
		PlayingPanel playingPanel = new PlayingPanel(keyLogic, inputHandler, graphicsMan);	
		playingPanel.setLayout(null);		
		
		//LABEL - BUILDING COUNT
		JLabel lblBuildingCount = new JLabel("Building(s) remaining: " + buildingCount);
		lblBuildingCount.setBounds(1050,20,200,15);
		playingPanel.add(lblBuildingCount);				
		
		this.getContentPane().add(playingPanel);				
		keyLogic.setPlayingPanel(playingPanel);
		inputHandler.setPlayingPanel(playingPanel);					
				
		Thread gameLoop = new Thread(new GameLoop(playingPanel));			

		gameLoop.start();	
	}
}  
