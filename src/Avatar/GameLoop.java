package Avatar;

import Input.AvatarInputHandler;

/**
 * Implements the main game loop, i.e. what actions should be taken on each frame update.
 */
public class GameLoop implements Runnable{
	
	private AvatarManagerPanel avatarManager;
	private KeysLogic keyLogic;
	private AvatarInputHandler inputHandler;

	//Constructor
	public GameLoop(AvatarManagerPanel avatarManager){
		
		this.avatarManager = avatarManager;
		this.keyLogic = avatarManager.getKeyLogic();
		this.inputHandler = avatarManager.getInputHandler();
	}

	/**
	 * Implements the run interface method. Should be called by the running thread (In Play Frame).
	 */
	public void run() {

		avatarManager.initiateAvatar();

		while(true) { 
			
			//Update the game graphics and repaint screen
			keyLogic.keyControls(inputHandler);
			avatarManager.repaint();
			
			KeysLogic.delay(1000/60);			

		}	

	}

}