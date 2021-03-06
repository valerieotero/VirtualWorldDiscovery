package GamePanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
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

	Reader reader = new Reader();
	private boolean ePressed = false;
	private static String mapName;
	private static String path = System.getProperty("user.dir");
	
	//BUILDING VARIABLES		
	public LinkedList<Walls> wallsDrawn = new LinkedList<>();;
	int buildingAmount;
	public static int buildingKey;
	HashMap<Integer, ArrayList<Integer>> buildingPictures = new HashMap<>();
	
	ArrayList<Integer> discoveredBuildings = new ArrayList<Integer>();

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
		createArrayLists();

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
		checkForCorrectAnswers();
		drawBuildingPicture();
		open3DModel(getMapName());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backBuffer, 0, 0, this);
	}

	/* Author: Valerie Otero | Date: April 18 2020
	 * Method draws the background image in the panel. */
	public void drawBackground(){	
		super.paintComponent(getGraphics2D());	    	
		getGraphics2D().drawImage(background, 0, (681-background.getHeight()), this);				
	}
	

	/* Author: Valerie Otero | Date: April 20 2020
	 * Method that sets the label with the building amounts that are in each map */
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


	/* Author: Valerie Otero | Date: April 11 2020
	 * Moves the avatar in the up direction of the screen */ 
	public void moveAvatarUp(){
		if(avatar.getY() - avatar.getSpeed() >= (getHeight() - background.getHeight())){				
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


	/* Author: Valerie Otero | Date: April 21 2020
	 * Method reads from the tree file and gets their coordinates. 
	 * Has helper method that determines which type, from the 3 trees, to draw in the panel. */
	public void drawTrees() {				

		for(HashMap.Entry<Integer,LinkedList<treeLocation>> treeLocation : Reader.getTreeLocation().entrySet()) {

			for(treeLocation location : treeLocation.getValue()) {					
				getTrees(location.getX(),location.getY(), treeLocation.getKey());	
			}			
		}
	}

	/* Author: Valerie Otero | Date: April 21 2020
	 * Helper method that determines which type of tree to draw on the specific coordinates. */
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


	/* Author: Valerie Otero | Date: April 21 2020
	 * Helper method that gets the value(the type of tree), according to the key */	
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
					//checkForCorrectAnswers();

				}				
			}			
		}

	}
	
	/* Author: Valerie Otero | Date: April 21 2020
	 * Adds walls that have been collided to a list that draws everything in it. */
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

	
	/* Author: Valerie Otero | Date: April 21 2020
	 * Helper method that gets the value(the walls), according to the key of the collided building*/
	public LinkedList<Walls> getKeyValuesForBuilding (int key) {

		for(HashMap.Entry<Integer,LinkedList<Walls>> buildings : Reader.getBuildings().entrySet()) {		

			if (buildings.getKey().equals(key)) {
				return buildings.getValue();
			}
		}
		return null;
	}

	
	/* Author: Valerie Otero | Date: April 23 2020
	 * Method initializes the frame where the question and answers appear if the user chooses this option*/
	public void drawTestFrame() {	

		if(getInputHandler().isEKeyPressed()) {

			new TakeTestFrame();

			getInputHandler().seteKeyIsPressed(false);

		}    
	}

	
	/* Author: Valerie Otero | Date: April 30 2020
	 * Methods constantly checks for at least 3 correct answers from each building in the panel. */
	public void checkForCorrectAnswers() {			


		for (HashMap.Entry<Integer, Integer> i : TakeTestPanel.buildingCorrectAnswers.entrySet()) {

			if(i.getKey() == getBuildingKey() ) {//check for the collided building only

				if (i.getValue() >=3) {

					ArrayList<Integer> coords = buildingPictures.get(getBuildingKey());
					coords.add(avatar.x);
					coords.add(avatar.y);
				}
			}
		}
	}

	public void drawBuildingPicture() {		

		BufferedImage buildingPicture = null;
		
		if(getBuildingKey()!=0) {
			
			try {
				buildingPicture = ImageIO.read(new File(Reader.getBuildingPictures().get(getBuildingKey()-1)));						
			} catch (IOException e) {						
				e.printStackTrace();
			}

			
			for (HashMap.Entry<Integer, ArrayList<Integer>> l : buildingPictures.entrySet()) {

				if(!l.getValue().isEmpty() && l.getKey() == getBuildingKey()) {
					System.out.println(coordinateXFinder(getBuildingKey()));
					System.out.println(coordinateYFinder(getBuildingKey()));
					getGraphics2D().drawImage(buildingPicture, coordinateXFinder(getBuildingKey()), coordinateYFinder(getBuildingKey()), this);
				}
			}
		}
	}
	
	/* Author: Juan Davila | Date: April 25 2020
	 * Opens VRML model of map while playing the game */ 
	public void open3DModel(String fileName){
		if(ePressed == false) {
			if(getInputHandler().isEKeyPressed()) {
				File file = new File(path + "\\vrmlmap\\" + fileName + ".wrl");
									//"C:\\Users\\juang\\git\\VirtualWorldDiscovery\\a.wrl"
				Desktop desktop = Desktop.getDesktop();
				System.out.println(file.toString());
				ePressed = true;
				if(file.exists()) {
					try {
						desktop.open(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/* Author Valerie Otero | Date: April 30 2020
	 * Helper method that helps initialize the array lists. */	 
	public void createArrayLists() {

		for(int i=1; i<=buildingAmount; i++) {

			ArrayList<Integer> list = new ArrayList<>();
			buildingPictures.put(i,list);

		}		
	}
	
	//Author: Yamil Gonzalez
	//Last Edited: 5/1/2020
	//Looks for the smallest of the X positions inside the building walls
	public int coordinateXFinder(int i) {
		int x = 0;
		LinkedList<Walls> temp = new LinkedList<Walls>();
		temp = reader.getBuildings().get(i);
		x = temp.element().getX1();
		for(Walls w: temp) {
			if(x > w.getX1()) x = w.getX1();
			else if (x > w.getX2()) x = w.getX2();
		}
		return x;
	}
	
	//Author: Yamil Gonzalez
	//Last Edited: 5/1/2020
	//Looks for the smallest of the Y positions inside the building walls
	public int coordinateYFinder(int i) {
		int y = 0;
		LinkedList<Walls> temp = new LinkedList<Walls>();
		temp = reader.getBuildings().get(i);
		y = temp.element().getY1();
		for(Walls w: temp) {
			if(y > w.getY1()) y = w.getY1();
			else if (y > w.getY2()) y = w.getY2();
		}
		return y;
	}
	
	public static void setMapName(String name) {
		mapName = name;
	}
	
	public static String getMapName() {
		return mapName;
	}
	
}
