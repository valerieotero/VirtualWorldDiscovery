package Avatar;

import Input.AvatarInputHandler;

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
