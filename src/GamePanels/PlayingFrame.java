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
	int chosenAvatar;
	
	public PlayingFrame(String mapName, int chosenAvatar) {	
		
		try {
			Reader.mapReaderController(mapName);
			Reader.treeReaderController(mapName);
			Reader.questionReaderController(mapName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");			
		}
		
		this.chosenAvatar = chosenAvatar;
		System.out.print(this.chosenAvatar);
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
						
		PlayingPanel playingPanel = new PlayingPanel(inputHandler, graphicsMan, chosenAvatar);	
		playingPanel.setLayout(null);		
	
		this.getContentPane().add(playingPanel);		
		inputHandler.setPlayingPanel(playingPanel);					
				
		Thread gameLoop = new Thread(new GameLoop(playingPanel));			

		gameLoop.start();

	}
}  
