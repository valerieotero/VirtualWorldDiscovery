package Panels;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Avatar.AvatarManagerPanel;
import Avatar.GameLoop;
import Avatar.GraphicsManager;
import Avatar.KeysLogic;
import Input.AvatarInputHandler;
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
		
		KeysLogic keyLogic = new KeysLogic(); 
		AvatarInputHandler inputHandler = new AvatarInputHandler(); 
		GraphicsManager graphicsMan = new GraphicsManager(); 				
									
		this.addKeyListener(inputHandler);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
						
		AvatarManagerPanel avatarManagerPanel = new AvatarManagerPanel(keyLogic, inputHandler, graphicsMan);	
		avatarManagerPanel.setLayout(null);		
		
		JLabel lblBuildingCount = new JLabel("Building(s) remaining: " + buildingCount);
		lblBuildingCount.setBounds(1050,20,200,15);
		avatarManagerPanel.add(lblBuildingCount);				
		
		this.getContentPane().add(avatarManagerPanel);				
		keyLogic.setAvatarManager(avatarManagerPanel);
		inputHandler.setAvatarManager(avatarManagerPanel);	
						
		this.setVisible(true); 
		
		Thread gameLoop = new Thread(new GameLoop(avatarManagerPanel));
		avatarManagerPanel.setLayout(null);		

		gameLoop.start();	
	}
}  
