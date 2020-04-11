package Panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Avatar.AvatarManager;
import Avatar.GameLoop;
import Avatar.GraphicsManager;
import Avatar.KeysLogic;
import Input.AvatarInputHandler;


public class PlayingFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	
	public PlayingFrame() {			
		initialize();		
	}

	
	private void initialize() {
		
		this.setBounds(10, 10, 1220, 720); //Same as the original frame
		
		KeysLogic keyLogic = new KeysLogic(); 
		AvatarInputHandler inputHandler = new AvatarInputHandler(); 
		GraphicsManager graphicsMan = new GraphicsManager(); 				
									
		this.addKeyListener(inputHandler);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
						
		AvatarManager avatarManager = new AvatarManager(keyLogic, inputHandler, graphicsMan);	
						
		this.getContentPane().add(avatarManager);				
		keyLogic.setAvatarManager(avatarManager);
		inputHandler.setAvatarManager(avatarManager);	
						
		this.setVisible(true); 
		
		Thread gameLoop = new Thread(new GameLoop(avatarManager));
		gameLoop.start();	
	}

}  
