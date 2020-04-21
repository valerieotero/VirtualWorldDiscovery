package Avatar;

import java.awt.Rectangle;


/* Author: Valerie Otero | Date: April 11 2020
 * This class creates a rectangle object as the avatar for the game. */
public class Avatar extends Rectangle {
	
	private Rectangle avatar = new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 2;
	public static final int Y_OFFSET = 5; // initial y distance of the avatar from the bottom of the screen 
	
	public static final int WIDTH = 42;
	public static final int HEIGHT = 45;
	
	private int speed;
	private int direction;
	

	//CONSTRUCTOR
	public Avatar(int xPos, int yPos, int width, int height){
		
		super(xPos, yPos,width, height);		
		this.setSpeed(DEFAULT_SPEED);
	}
	
	//Getters
	public int getSpeed() { return speed; }	
	public int getDirection() {	return direction; }
	public int getInitialYOffset() { return Y_OFFSET; }		
	public int getDefaultSpeed(){ return DEFAULT_SPEED;	}	
	public int getPixelsWide() { return (int) getWidth(); }	
	public int getPixelsTall() { return (int) getHeight(); }	
	public Rectangle getCollision() {return avatar;}
	
	
	//Setters
	public void setSpeed(int speed) { this.speed = speed; }	
	public void setDirection(int direction) { this.direction = direction; }
	
}
