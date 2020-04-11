package Avatar;

import Input.AvatarInputHandler;

public class KeysLogic {

	private AvatarManager avatarManager;
	
	//Constructor
	public KeysLogic(){
	}

	public AvatarManager getAvatarManager() {
		return avatarManager;
	}
	
	public void setAvatarManager(AvatarManager avatarManager) {
		this.avatarManager = avatarManager;
	}
	
	public void keyControls(AvatarInputHandler ih) {							
			avatarManager.moveAvatar();
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
