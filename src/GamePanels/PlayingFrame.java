package GamePanels;


import java.io.FileNotFoundException;

import javax.swing.JFrame;
import Avatar.GameLoop;
import Avatar.GraphicsManager;
import Classes.Reader;
import Input.PlayingPanelInputHandler;
import javax.swing.JLabel;



public class PlayingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;	
	public int buildingCount = 0;
	
	public PlayingFrame(String mapName) {	
		
		try {
			Reader.mapReaderController(mapName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");			
		}
		
		initialize();		
	}

	
	private void initialize() {
		
		this.setBounds(10, 10, 1220, 720); //Same as the original frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
		
		playingPanelComponents();		
	
	}
	
	public void playingPanelComponents() {
				
		PlayingPanelInputHandler inputHandler = new PlayingPanelInputHandler(); 
		GraphicsManager graphicsMan = new GraphicsManager(); 				
									
		this.addKeyListener(inputHandler);									
						
		PlayingPanel playingPanel = new PlayingPanel(inputHandler, graphicsMan);	
		playingPanel.setLayout(null);		
		
		//LABEL - BUILDING COUNT
		JLabel lblBuildingCount = new JLabel("Building(s) remaining: " + buildingCount);
		lblBuildingCount.setBounds(1050,20,200,15);
		playingPanel.add(lblBuildingCount);				
		
		this.getContentPane().add(playingPanel);		
		inputHandler.setPlayingPanel(playingPanel);					
				
		Thread gameLoop = new Thread(new GameLoop(playingPanel));			

		gameLoop.start();

	}
}  
