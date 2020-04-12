package Avatar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Input.AvatarInputHandler;
import Panels.TakeTestFrame;
import Panels.TestCreatorFrame;


public class AvatarManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected Avatar avatar;	
	protected Avatar fourWalls;
	protected Rectangle walls;

	private GraphicsManager graphicsManager;
	private KeysLogic keyLogic;
	private AvatarInputHandler inputHandler;
	
	private Graphics2D g2d;
	protected BufferedImage backBuffer;
	
	public boolean isOpen = false;
	private boolean haveTouched = false;
	
	
	//Constructor
	public AvatarManagerPanel(KeysLogic keyLogic, AvatarInputHandler inputHandler, GraphicsManager graphicsMan) {		
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
	public AvatarInputHandler getInputHandler() { return inputHandler; }
	public Graphics2D getGraphics2D() { return g2d; }
	public Avatar getAvatar() 	{return avatar;}

	// Setters
	protected void setGraphicsManager(GraphicsManager graphicsManager) { this.graphicsManager = graphicsManager; }
	protected void setKeyLogic(KeysLogic gameLogic) { this.keyLogic = gameLogic; }
	protected void setInputHandler(AvatarInputHandler inputHandler) { this.inputHandler = inputHandler; }
	public void setGraphics2D(Graphics2D g2d) { this.g2d = g2d; }


    //Sets avatar at the bottom left corner
	public void initiateAvatar() {
		newAvatar();
		new4Walls();
		avatar.setDirection(1);	//start with the image looking to the right
	}

	public void updateScreen() {
		clearScreen();
		drawAvatar();
		
		checkAvatarWallsCollisions();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backBuffer, 0, 0, this);
	}


	//draw one of two possible MegaMan poses according to direction
	protected void drawAvatar() {
			
		Graphics2D g2d = getGraphics2D();
		
		if (avatar.getDirection() > 0) {		
			
			getGraphicsManager().drawAvatar(avatar, g2d, this);			
		}
		else {			
			getGraphicsManager().drawLeftAvatar(avatar, g2d, this);
			
		}
	}

     /* Makes sure there is only one image of the avatar on the screen. 
	  * If not used, you can see the trace of where the image has moved. */
	protected void clearScreen() {		
		Graphics2D g2d = getGraphics2D();	
		g2d.fillRect(0, 0, getSize().width, getSize().height);
	}


	//Draws avatar in the bottom left corner 
	public Avatar newAvatar(){
		this.avatar = new Avatar(20,633,42,45); //x, y, width, height		
		return avatar;
	}
	
	//Initiate posotion of the wall
	public Avatar new4Walls() {
		this.fourWalls = new Avatar(60, 94, 100, 58);//Use avatar class because it extends from Rectangle class.
		return fourWalls;
	}
	
	public void draw4Walls() {
		Graphics2D g2d = getGraphics2D();			
		getGraphicsManager().draw4Walls(fourWalls, g2d, this);			
	}
	
	
	public boolean avatarStatic(){
		if(avatar.getSpeed() == 0) {
			return true;
		}
		return false;
	}
	
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

	public void moveAvatarDown(){
		for(int i=0; i<9; i++){
			if(avatar.getY() + avatar.getSpeed() + avatar.height < getHeight()) { 
				avatar.translate(0, 2);
			}
		}
	}

	public void moveAvatarLeft(){		
		if(avatar.getX() - avatar.getSpeed() >= 0){
			avatar.translate(-avatar.getSpeed(), 0);
			avatar.setDirection(-1);	
		}
	}

	public void moveAvatarRight(){
		avatar.setDirection(1);
		if(avatar.getX() + avatar.getSpeed() + avatar.width < getWidth()){
			avatar.translate(avatar.getSpeed(), 0);
		}
	}
	
	public void interactAvatar(AvatarInputHandler ih) {
		if(ih.isEKeyPressed() && isOpen == false) {
			TakeTestFrame testFrame = new TakeTestFrame();
			isOpen = true;
		}
	}
	
	protected void checkAvatarWallsCollisions() {	
		if(fourWalls.intersects(avatar) && haveTouched == false){				
			draw4Walls();	
			haveTouched = true;
		}	
		if(haveTouched == true) {
			draw4Walls();
		}
	}
	
}
