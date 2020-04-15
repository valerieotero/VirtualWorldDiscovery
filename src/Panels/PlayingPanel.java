package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Avatar.Avatar;
import Avatar.GraphicsManager;
import Avatar.KeysLogic;
import Input.PlayingPanelInputHandler;


public class PlayingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected Avatar avatar;	
	protected Avatar fourWalls;
	protected Rectangle walls;
	protected Rectangle message;

	private GraphicsManager graphicsManager;
	private KeysLogic keyLogic;
	private PlayingPanelInputHandler inputHandler;
	
	private Graphics2D g2d;
	protected BufferedImage backBuffer;
	
	public boolean isOpen = false;
	private boolean haveTouched = false;
	
	
	/*Author: Valerie Otero | Date: April 11 2020
	 * 
	 */
	//Constructor
	public PlayingPanel(KeysLogic keyLogic, PlayingPanelInputHandler inputHandler, GraphicsManager graphicsMan) {		
		this.setPreferredSize(new Dimension(1220, 681));	
		this.setKeyLogic(keyLogic);
		this.setInputHandler(inputHandler);
		this.setGraphicsManager(graphicsMan);		
		backBuffer = new BufferedImage(1220, 681, BufferedImage.TYPE_INT_RGB);
		this.setGraphics2D(backBuffer.createGraphics());
	}

	// Getters
	public GraphicsManager getGraphicsManager() { return graphicsManager; }
	public KeysLogic getKeyLogic() { return keyLogic; }
	public PlayingPanelInputHandler getInputHandler() { return inputHandler; }
	public Graphics2D getGraphics2D() { return g2d; }
	public Avatar getAvatar() 	{return avatar;}

	// Setters
	protected void setGraphicsManager(GraphicsManager graphicsManager) { this.graphicsManager = graphicsManager; }
	protected void setKeyLogic(KeysLogic gameLogic) { this.keyLogic = gameLogic; }
	protected void setInputHandler(PlayingPanelInputHandler inputHandler) { this.inputHandler = inputHandler; }
	public void setGraphics2D(Graphics2D g2d) { this.g2d = g2d; }

	
	/* Author: Valerie Otero | Date: April 11 2020
	 * Sets avatar at the bottom left corner. */
	public void initiateAvatar() {
		newAvatar();
		new4Walls();
		message();
		avatar.setDirection(1);	//start with the image looking to the right
	}

	
	/* Author: Valerie Otero | Date: April 11 2020
	 * Method that constantly checks the screen for any changes and if so updates it with those changes or new objects */ 
	public void updateScreen() {
		clearScreen();
		drawAvatar();
		checkAvatarWallsCollisions();
		checkMessageCollision();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backBuffer, 0, 0, this);
	}

	
	/* Author: Valerie Otero | Date: April 11 2020
	 * This method draws one of two possible avatar poses according to direction.  */
	protected void drawAvatar() {
			
		Graphics2D g2d = getGraphics2D();
		
		if (avatar.getDirection() > 0) {		
			
			getGraphicsManager().drawAvatar(avatar, g2d, this);			
		}
		else {			
			getGraphicsManager().drawLeftAvatar(avatar, g2d, this);
			
		}
	}
	

     /* Author: Valerie Otero | Date: April 11 2020
      * Makes sure there is only one image of the avatar on the screen. 
	  * If not used, you can see the trace of where the image has moved. */
	protected void clearScreen() {		
		Graphics2D g2d = getGraphics2D();	
		g2d.fillRect(0, 0, getSize().width, getSize().height);
	}
	

	/* Author: Valerie Otero | Date: April 11 2020
	 * Draws avatar in the bottom left corner  */	
	public Avatar newAvatar(){
		this.avatar = new Avatar(20,633,42,45); //x, y, width, height		
		return avatar;
	}
	
	
	/* Author: Valerie Otero | Date: April 11 2020
	 * Initiate position of the wall */ 	
	public Avatar new4Walls() {
		this.fourWalls = new Avatar(60, 94, 100, 58);//Use avatar class because it extends from Rectangle class.
		return fourWalls;
	}
	
	public Rectangle message() {
		this.message = new Rectangle(60, 94, 96, 54);
		return message;
	}
	
	
	/* Author: Valerie Otero | Date: April 11 2020
	 * When called, it will draw on the screen the four wall image*/	
	public void draw4Walls() {
		Graphics2D g2d = getGraphics2D();			
		getGraphicsManager().draw4Walls(fourWalls, g2d, this);			
	}
	
	public void writeMessage() {
		Graphics2D g2d = getGraphics2D();
		getGraphicsManager().writeMessage(message, g2d, this);
	}
	
	public boolean avatarStatic(){
		if(avatar.getSpeed() == 0) {
			return true;
		}
		return false;
	}
	
	/* Author: Valerie Otero | Date: April 11 2020
	 * Moves the avatar in the up direction of the screen */ 
	public void moveAvatarUp(){
		if(avatar.getCollision().intersects(fourWalls.getCollision())) {
			avatar.translate(0, 0);
		}
		else {
			if(avatar.getY() - avatar.getSpeed() >= 0){
				avatar.translate(0, -avatar.getSpeed()*2);
			}
		 }
	}

	/* Author: Valerie Otero | Date: April 11 2020
	 * Moves the avatar in the down direction of the screen */ 
	public void moveAvatarDown(){
		for(int i=0; i<9; i++){
			if(avatar.getY() + avatar.getSpeed() + avatar.height < getHeight()) { 
				avatar.translate(0, 2);
			}
		}
	}
	/* Author: Valerie Otero | Date: April 11 2020
	 * Moves the avatar in the left direction of the screen */ 
	public void moveAvatarLeft(){		
		if(avatar.getX() - avatar.getSpeed() >= 0){
			avatar.translate(-avatar.getSpeed(), 0);
			avatar.setDirection(-1);	
		}
	}

	/* Author: Valerie Otero | Date: April 11 2020
	 * Moves the avatar in the right direction of the screen */ 
	public void moveAvatarRight(){
		avatar.setDirection(1);
		if(avatar.getX() + avatar.getSpeed() + avatar.width < getWidth()){
			avatar.translate(avatar.getSpeed(), 0);
		}
	}
	
	public void interactAvatar(PlayingPanelInputHandler ih) {
		if(ih.isEKeyPressed() && isOpen == false) {
			TakeTestFrame testFrame = new TakeTestFrame();
			isOpen = true;
		}
	}
	
	/* Author: Valerie Otero | Date: April 11 2020
	 * Checks collision of the avatar with the four wall image */ 
	protected void checkAvatarWallsCollisions() {	
		if(fourWalls.intersects(avatar) && haveTouched == false){				
			draw4Walls();	
			haveTouched = true;
		}	
		if(haveTouched == true) {
			draw4Walls();
		}
	}
	
	protected void checkMessageCollision() {
		if(message.intersects(avatar)) {
			writeMessage();
		}
	}
	
}
