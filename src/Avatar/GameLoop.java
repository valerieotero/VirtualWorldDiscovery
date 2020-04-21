package Avatar;

import GamePanels.PlayingPanel;
import Input.PlayingPanelInputHandler;


  /* Author: Valerie Otero | Date: April 11 2020 
  * Implements the main game loop, i.e. what actions should be taken on each frame update. */
public class GameLoop implements Runnable{
	
	private PlayingPanel playingPanel;	
	private PlayingPanelInputHandler inputHandler;

	//Constructor
	public GameLoop(PlayingPanel playingPanel){
		
		this.playingPanel = playingPanel;		
		this.inputHandler = playingPanel.getInputHandler();
	}

	
	//Implements the run interface method. Should be called by the running thread (In Play Frame).	
	public void run() {

		playingPanel.initialize();

		while(true) { 
			
			//Update the game graphics and repaint screen
			playingPanel.updateScreen();
			inputHandler.handleKeysDuringPlay(inputHandler);
			playingPanel.repaint();
			
			PlayingPanelInputHandler.delay(1000/60);			

		}	

	}

}