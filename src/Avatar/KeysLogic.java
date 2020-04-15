package Avatar;

import Input.PlayingPanelInputHandler;
import Panels.PlayingPanel;


/* Author: Valerie Otero | Date: April 11 2020
 * Class is responsible for detecting what key is being pressed and what action to take afterwards. */
public class KeysLogic {

	private PlayingPanel playingPanel;

	//Constructor
	public KeysLogic(){
	}

	public PlayingPanel getPlayingPanel() {
		return playingPanel;
	}

	public void setPlayingPanel(PlayingPanel playingPanel) {
		this.playingPanel = playingPanel;
	}

	public void keyControls(PlayingPanelInputHandler ih) {							
		playingPanel.updateScreen();
		handleKeysDuringPlay(ih);		

	}


	public void handleKeysDuringPlay(PlayingPanelInputHandler ih) {				

		if(ih.isUpPressed()){		
			getPlayingPanel().moveAvatarUp();			
		}

		if(ih.isDownPressed()){
			getPlayingPanel().moveAvatarDown();			
		}

		if(ih.isLeftPressed()){
			getPlayingPanel().moveAvatarLeft();
		}

		if(ih.isRightPressed()){
			getPlayingPanel().moveAvatarRight();
		}
		if(ih.isEKeyPressed()) {
			getPlayingPanel().interactAvatar(ih);
		}

	}

	public static void delay(long millis) {
		try{
			Thread.sleep(millis);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
