package GamePanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
	int chosenAvatar;

	private GraphicsManager graphicsManager;	
	private PlayingPanelInputHandler inputHandler;

	private Graphics2D g2d;
	protected BufferedImage backBuffer;	

	BufferedImage background;

	//BUILDING VARIABLES		
	public LinkedList<Walls> wallsDrawn = new LinkedList<>();;
	int buildingAmount;
	public static int buildingKey;
	ArrayList<BufferedImage> buildingImages = new ArrayList<BufferedImage>();


	// Getters
	public GraphicsManager getGraphicsManager() { return graphicsManager; }	
	public PlayingPanelInputHandler getInputHandler() { return inputHandler; }
	public Graphics2D getGraphics2D() { return g2d; }
	public Avatar getAvatar() { return avatar; }	
	public static int getBuildingKey() { return buildingKey; }


	// Setters
	protected void setGraphicsManager(GraphicsManager graphicsManager) { this.graphicsManager = graphicsManager; }	
	protected void setInputHandler(PlayingPanelInputHandler inputHandler) { this.inputHandler = inputHandler; }
	public void setGraphics2D(Graphics2D g2d) { this.g2d = g2d; }
	public static void setBuildingKey(int buildingKey) { PlayingPanel.buildingKey = buildingKey;	}



	/*Author: Valerie Otero | Date: April 11 2020
	 */
	//Constructor
	public PlayingPanel(PlayingPanelInputHandler inputHandler, GraphicsManager graphicsMan, int chosenAvatar) {		
		this.setPreferredSize(new Dimension(1220, 681));			
		this.setInputHandler(inputHandler);
		this.setGraphicsManager(graphicsMan);		
		this.chosenAvatar = chosenAvatar;
		backBuffer = new BufferedImage(1220, 681, BufferedImage.TYPE_INT_RGB);
		this.setGraphics2D(backBuffer.createGraphics());		
		try {
			background = ImageIO.read(new File(Reader.getBackground()));
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}



	/* Author: Valerie Otero | Date: April 11 2020
	 * Sets avatar at the bottom left corner. */
	public void initialize() {				
		newAvatar();		
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
		drawWalls();	
		drawTrees();		
		drawBuildingPicture();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backBuffer, 0, 0, this);
	}


	public void drawBackground(){	
		super.paintComponent(getGraphics2D());	    	
		getGraphics2D().drawImage(background, 0, (681-background.getHeight()), this);				
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

		switch(chosenAvatar) {

		case 0:

			if (avatar.getDirection() > 0) {					
				getGraphicsManager().drawAvatar(avatar, g2d, this);					
			}
			else {			
				getGraphicsManager().drawLeftAvatar(avatar, g2d, this);	
			}			
			break;

		case 1: 

			if (avatar.getDirection() > 0) {					
				getGraphicsManager().drawMario(avatar, g2d, this);					
			}
			else {			
				getGraphicsManager().drawLeftMario(avatar, g2d, this);	
			}				
			break;				
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


	public boolean avatarStatic(){
		if(avatar.getSpeed() == 0) {
			return true;
		}
		return false;
	}


	/* Author: Valerie Otero | Date: April 11 2020
	 * Moves the avatar in the up direction of the screen */ 
	public void moveAvatarUp(){
		if(avatar.getY() - avatar.getSpeed() >= 80){			
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
		if(avatar.getX() + avatar.getSpeed() + avatar.width < background.getWidth()){
			avatar.translate(avatar.getSpeed(), 0);
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

		String imageType = getKeyValuesForTrees(key);

		switch(imageType) {

		case "TreeImage1":
			getGraphicsManager().drawTree1(x,y,getGraphics2D(),this);
			break;

		case "TreeImage2":
			getGraphicsManager().drawTree2(x,y,getGraphics2D(),this);
			break;

		case "TreeImage3":
			getGraphicsManager().drawTree3(x,y,getGraphics2D(),this);
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

					getGraphics2D().drawString("Press E to take test, if not, continue search ", wall.getX1(), wall.getY1());				
					getGraphics2D().setColor(Color.BLACK);	

					setBuildingKey(buildings.getKey());

					addWallsToList();										
					drawTestFrame();
					checkForCorrectAnswers();
				}
			}			
		}	
	}

	public void addWallsToList() {		

		for (Walls w : getKeyValuesForBuilding(getBuildingKey())) {

			wallsDrawn.add(w);					
		}
	}


	/* Author: Valerie Otero | Date: April 21 2020
	 * When called, it will draw on the screen the building walls that the avatar has collided with */	
	public void drawWalls() {		

		if(!wallsDrawn.isEmpty()) {

			for (Walls w :wallsDrawn ) {

				getGraphics2D().drawLine(w.getX1(), w.getY1(), w.getX2(), w.getY2());			
				getGraphics2D().setStroke(new BasicStroke(3)); //Line width
				getGraphics2D().setColor(Color.black);			
			}
		}
	}

	//get key value
	public LinkedList<Walls> getKeyValuesForBuilding (int key) {

		for(HashMap.Entry<Integer,LinkedList<Walls>> buildings : Reader.getBuildings().entrySet()) {		

			if (buildings.getKey().equals(key)) {
				return buildings.getValue();
			}
		}
		return null;
	}


	public void drawTestFrame() {	

		if(getInputHandler().isEKeyPressed()) {

			new TakeTestFrame();

			getInputHandler().seteKeyIsPressed(false);

		}    
	}

	public void checkForCorrectAnswers() {					

		for (HashMap.Entry<Integer, Integer> i : TakeTestPanel.buildingCorrectAnswers.entrySet()) {

			if(i.getKey() == getBuildingKey()) { //check for the collided building only

				if (i.getValue() >=3) {

					//System.out.println("BuildingKey : " + i.getKey() + " CORRECTANSWERS: "+ i.getValue());

					BufferedImage buildingPicture = null;
					try {
						buildingPicture = ImageIO.read(new File(Reader.getBuildingPictures().get(getBuildingKey()-1)));						
					} catch (IOException e) {						
						e.printStackTrace();
					}

					buildingImages.add(buildingPicture);

				}

			}
		}
	}

	public void drawBuildingPicture() {		

		if(!buildingImages.isEmpty()) {
			for(BufferedImage image : buildingImages) {
				getGraphics2D().drawImage(image, 0,0, this);	
			}	

		}
	}
}
