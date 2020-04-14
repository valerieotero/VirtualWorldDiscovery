package Avatar;

import Input.AvatarInputHandler;


/* Author: Valerie Otero | Date: April 11 2020
 * Class is responsable for detecting what key is being pressed and what action to take afterwards. */
public class KeysLogic {

	private AvatarManagerPanel avatarManager;
	
	//Constructor
	public KeysLogic(){
	}

	public AvatarManagerPanel getAvatarManager() {
		return avatarManager;
	}
	
	public void setAvatarManager(AvatarManagerPanel avatarManager) {
		this.avatarManager = avatarManager;
	}
	
	public void keyControls(AvatarInputHandler ih) {							
			avatarManager.updateScreen();
			handleKeysDuringPlay(ih);		
			
	}

	
	public void handleKeysDuringPlay(AvatarInputHandler ih) {				

		if(ih.isUpPressed()){		
			getAvatarManager().moveAvatarUp();			
		}

		if(ih.isDownPressed()){
			getAvatarManager().moveAvatarDown();			
		}

		if(ih.isLeftPressed()){
			getAvatarManager().moveAvatarLeft();
		}

		if(ih.isRightPressed()){
			getAvatarManager().moveAvatarRight();
		}
		if(ih.isEKeyPressed()) {
			getAvatarManager().interactAvatar(ih);
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
