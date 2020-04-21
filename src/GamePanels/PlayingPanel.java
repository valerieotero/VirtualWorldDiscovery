package GamePanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Avatar.Avatar;
import Avatar.GraphicsManager;

import Classes.Reader;
import Classes.Walls;
import Classes.treeLocation;
import Input.PlayingPanelInputHandler;




public class PlayingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected Avatar avatar;	
	protected Avatar fourWalls;
	protected Rectangle walls;
	protected Rectangle message;	

	private GraphicsManager graphicsManager;	
	private PlayingPanelInputHandler inputHandler;

	private Graphics2D g2d;
	protected BufferedImage backBuffer;

	public boolean isOpen = false;
	private boolean haveTouched = false;

	BufferedImage background;
		
	//BUILDING VARIABLES		
	public LinkedList<Walls> wallsDrawn = new LinkedList<>();;	
	int buildingAmount;

	
	/*Author: Valerie Otero | Date: April 11 2020
	 */
	//Constructor
	public PlayingPanel(PlayingPanelInputHandler inputHandler, GraphicsManager graphicsMan) {		
		this.setPreferredSize(new Dimension(1220, 681));			
		this.setInputHandler(inputHandler);
		this.setGraphicsManager(graphicsMan);		
		backBuffer = new BufferedImage(1220, 681, BufferedImage.TYPE_INT_RGB);
		this.setGraphics2D(backBuffer.createGraphics());		
		try {
			background = ImageIO.read(new File(Reader.getBackground()));
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}

	// Getters
	public GraphicsManager getGraphicsManager() { return graphicsManager; }	
	public PlayingPanelInputHandler getInputHandler() { return inputHandler; }
	public Graphics2D getGraphics2D() { return g2d; }
	public Avatar getAvatar() { return avatar; }	

	// Setters
	protected void setGraphicsManager(GraphicsManager graphicsManager) { this.graphicsManager = graphicsManager; }	
	protected void setInputHandler(PlayingPanelInputHandler inputHandler) { this.inputHandler = inputHandler; }
	public void setGraphics2D(Graphics2D g2d) { this.g2d = g2d; }
	

	/* Author: Valerie Otero | Date: April 11 2020
	 * Sets avatar at the bottom left corner. */
	public void initialize() {				
		newAvatar();		
	//	message();
		avatar.setDirection(1);	//start with the image looking to the right
		buildingAmountLabel();			
	}


	/* Author: Valerie Otero | Date: April 11 2020
	 * Method that constantly checks the screen for any changes and if so updates it with those changes or new objects */ 
	public void updateScreen() {	
		clearScreen();		
		drawBackground();		
		drawAvatar();		
		checkWallCollision();		
	//	checkMessageCollision();	
		drawWalls();	
	    drawTrees();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backBuffer, 0, 0, this);
	}


	public void drawBackground(){
		Graphics2D g2d = getGraphics2D();
		super.paintComponent(g2d);	    	
		g2d.drawImage(background, 0, 0, this);				
	}
	

	public void buildingAmountLabel() {
		//LABEL - BUILDING COUNT
		buildingAmount = Reader.getAmount();
		JLabel lblBuildingCount = new JLabel("Building(s) remaining: " + buildingAmount);
		lblBuildingCount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuildingCount.setBounds(1030,10,200,20);
		this.add(lblBuildingCount);	
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


	public Rectangle message() {
		this.message = new Rectangle(60, 94, 96, 54);
		return message;
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
		if(avatar.getY() - avatar.getSpeed() >= 0){
			avatar.translate(0, -avatar.getSpeed()*2);
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

	
	protected void checkMessageCollision() {
		if(message.intersects(avatar)) {
			writeMessage();
		}
	}	
	
	
	public void drawTrees() {				

		for(HashMap.Entry<Integer,LinkedList<treeLocation>> treeLocation : Reader.getTreeLocation().entrySet()) {

			for(treeLocation location : treeLocation.getValue()) {					
				getTrees(location.getX(),location.getY(), treeLocation.getKey());	
			}			
		}
	}

	
	public void getTrees(int x, int y, int key) {

		Graphics2D g2d = getGraphics2D();
		String imageType = getKeyValuesForTrees(key);
		
		switch(imageType) {

		case "TreeImage1":
			getGraphicsManager().drawTree1(x,y,g2d,this);
			break;

		case "TreeImage2":
			getGraphicsManager().drawTree2(x,y,g2d,this);
			break;

		case "TreeImage3":
			getGraphicsManager().drawTree3(x,y,g2d,this);
			break;
		}
	}
	
	
	//get key values
	public String getKeyValuesForTrees (int key) {

		for(HashMap.Entry<Integer,String> treeType : Reader.getTreeType().entrySet()) {

			if (treeType.getKey().equals(key)) {
				return treeType.getValue();
			}	

		}
		return null;
	}


	/* Author: Valerie Otero | Date: April 21 2020
	 * Checks collision of the avatar with building walls */ 
	protected void checkWallCollision() {
				
		for(HashMap.Entry<Integer,LinkedList<Walls>> buildings : Reader.getBuildings().entrySet()) {		 					
						
			for(Walls wall : buildings.getValue()) {			

				if(avatar.intersectsLine(wall.getX1(), wall.getY1(), wall.getX2(), wall.getY2())) {																
					
					addWallsToList(buildings.getKey());		
						
				}
			}			
		}	
	}

	public void addWallsToList(int key) {
				
		LinkedList<Walls> wall = getKeyValuesForBuilding(key);
			
		for (Walls w : wall) {
			
			wallsDrawn.add(w);					
		}
	}
	
	
	/* Author: Valerie Otero | Date: April 21 2020
	 * When called, it will draw on the screen the building walls that the avatar has collided with */	
	public void drawWalls() {
		
		Graphics2D g2d = getGraphics2D();
		
		if(!wallsDrawn.isEmpty()) {
			
			for (Walls w :wallsDrawn ) {
				
				g2d.drawLine(w.getX1(), w.getY1(), w.getX2(), w.getY2());			
				g2d.setStroke(new BasicStroke(3)); //Line width
				g2d.setColor(Color.black);
			}
		}
	}

	//get key value
	public LinkedList<Walls> getKeyValuesForBuilding (Integer key) {

		for(HashMap.Entry<Integer,LinkedList<Walls>> buildings : Reader.getBuildings().entrySet()) {		

			if (buildings.getKey().equals(key)) {
				return buildings.getValue();
			}
		}
		return null;
	}	
}
