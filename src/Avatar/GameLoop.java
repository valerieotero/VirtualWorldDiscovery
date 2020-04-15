package Avatar;

import Input.PlayingPanelInputHandler;
import Panels.PlayingPanel;


  /* Author: Valerie Otero | Date: April 11 2020 
  * Implements the main game loop, i.e. what actions should be taken on each frame update. */
public class GameLoop implements Runnable{
	
	private PlayingPanel playingPanel;
	private KeysLogic keyLogic;
	private PlayingPanelInputHandler inputHandler;

	//Constructor
	public GameLoop(PlayingPanel playingPanel){
		
		this.playingPanel = playingPanel;
		this.keyLogic = playingPanel.getKeyLogic();
		this.inputHandler = playingPanel.getInputHandler();
	}

	
	//Implements the run interface method. Should be called by the running thread (In Play Frame).	
	public void run() {

		playingPanel.initiateAvatar();

		while(true) { 
			
			//Update the game graphics and repaint screen
			keyLogic.keyControls(inputHandler);
			playingPanel.repaint();
			
			KeysLogic.delay(1000/60);			

		}	

	}

}