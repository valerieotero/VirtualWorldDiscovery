package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Avatar.AvatarManagerPanel;

/**
 * Handles user input events.
 */
public class AvatarInputHandler implements KeyListener{
	
	private boolean leftIsPressed;
	private boolean rightIsPressed;
	private boolean downIsPressed;
	private boolean upIsPressed;

	private AvatarManagerPanel avatarManager;	

	public AvatarManagerPanel getAvatarManager() { return avatarManager; }
	public void setAvatarManager(AvatarManagerPanel avatarManager) { this.avatarManager = avatarManager; }

	/**
	 * Create a new input handler
	**/
	public AvatarInputHandler(){
		reset();
	}

	public void reset() {
		leftIsPressed = false;
		rightIsPressed = false;
		downIsPressed = false;
		upIsPressed = false;		
	}

	public boolean isLeftPressed() {
		return leftIsPressed;
	}

	public boolean isRightPressed() {
		return rightIsPressed;
	}

	public boolean isDownPressed() {
		return downIsPressed;
	}

	public boolean isUpPressed() {
		return upIsPressed;
	}


	/**
	 * Handle a key input event.
	 */
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:			
			this.upIsPressed = true;
			break;
		case KeyEvent.VK_DOWN:			
			this.downIsPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			this.leftIsPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			this.rightIsPressed = true;
			break;	
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;
	
		}
		e.consume();
	}

	/**
	 * Handle a key release event.
	 */
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:			
			this.upIsPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			this.downIsPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			this.leftIsPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			this.rightIsPressed = false;
			break;
		}
		e.consume();
	}

	public void keyTyped(KeyEvent e) {
		// not used; comes with the interface
	}

}
