package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GamePanels.PlayingPanel;

/**
 * Handles user input events.
 */
public class PlayingPanelInputHandler implements KeyListener{
	
	private boolean leftIsPressed;
	private boolean rightIsPressed;
	private boolean downIsPressed;
	private boolean upIsPressed;
	private boolean eKeyIsPressed;

	private PlayingPanel playingPanel;	

	public PlayingPanel getPlayingPanel() { return playingPanel; }
	public void setPlayingPanel(PlayingPanel playingPanel) { this.playingPanel = playingPanel; }
	

	//Creates a new input handler	
	public PlayingPanelInputHandler(){
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
	
	public boolean isEKeyPressed() {
		return eKeyIsPressed;
	}



	//Handle a key input event	
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
		case KeyEvent.VK_W:			
			this.upIsPressed = true;
			break;
		case KeyEvent.VK_S:			
			this.downIsPressed = true;
			break;
		case KeyEvent.VK_A:
			this.leftIsPressed = true;
			break;
		case KeyEvent.VK_D:
			this.rightIsPressed = true;
			break;	
		case KeyEvent.VK_E:
			this.eKeyIsPressed = true;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;
	
		}
		e.consume();
	}

	
	//Handle a key release event	
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
		case KeyEvent.VK_W:			
			this.upIsPressed = false;
			break;
		case KeyEvent.VK_S:			
			this.downIsPressed = false;
			break;
		case KeyEvent.VK_A:
			this.leftIsPressed = false;
			break;
		case KeyEvent.VK_D:
			this.rightIsPressed = false;
			break;	
		case KeyEvent.VK_E:
			this.rightIsPressed = false;
			break;
		}
		e.consume();
	}
	

	/* Author: Valerie Otero | Date: April 11 2020
	 * Method is responsible for detecting what key is being pressed and what action to take afterwards. */
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
	
	
	public void keyTyped(KeyEvent e) {
		// not used; comes with the interface
	}
	

}
