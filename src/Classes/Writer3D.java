package Classes;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import Classes.Reader;

public class Writer3D {
	
	
	private static int x1;
	private static int x2;
	private static int y1;
	private static int y2;
	private static double heightCorrection;
	private static double distance;
	private static double angle; 
	private static double medPointX;
	private static double medPointY;
	private static String base;
	private static String end;
	
	///testvalues
	public static int bgWidth = 800;
	public static int bgHeight = 600; 
	public static String bgImage = "texture.png";
	
	public static int xOne = 40; 
	public static int yOne = 80;
	public static int xTwo = 150;
	public static int yTwo = 360;
	public static int depth = 1;
	public static int height = 20;
	public static String imagePath = "texture2.png";
	
	public static int xLocation = 250;
	public static int yLocation = 250;
	public static int treeWidth = 40;
	public static int treeHeight = 60;
	public static int treeImg = 0;

	
	
//	public static void main(String[] args) {
//		open("VRMLtest.wrl");
//		setHeightCorrection(bgHeight);
//		startVRMLCode();
//		prepBackground(bgWidth, bgHeight, bgImage);
//		buildWallVRML(xOne, yOne, xTwo, yTwo, depth, height, imagePath);
//		buildTreesVRML(xLocation, yLocation, treeWidth, treeHeight, treeImg);
//		endVRMLCode();
//		close();
//	}
	
	public static FileWriter vrmlFile;

	
	public static void open(String fileName) {
		try {
			vrmlFile = new FileWriter(fileName, true);
		}catch(IOException e) {
			System.out.println("An error ocurred.");
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			vrmlFile.close();
		} catch(IOException e) {
			System.out.println("An error ocurred.");
			e.printStackTrace();
		}
	}
	
	public static void startVRMLCode(int bgHeight) {
		try {
			base = "#VRML V2.0 utf8\r\n" + 
					"\r\n" + 
					"NavigationInfo {\r\n" + 
					"	set_bind\r\n" +
					"	avatarSize        [0.25, 1.6, 0.75]\r\n" +
					"	headlight         TRUE\r\n" +
					"	speed             1.0\r\n" +
					"	type              \"WALK\"\r\n" +
					"	visibilityLimit   0.0\r\n" +
					"	isBound\r\n" +
					"	\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"Group {\r\n" + 
					"	children[\r\n" + 
					"		DEF headcam Viewpoint {\r\n" + 
					"         position 0 0 " + bgHeight + "\r\n" +
					"         fieldOfView 0.50\r\n" + 
					"		 orientation 0 1 0 -.5\r\n" + 
					"         description \"HeadCam\"\r\n" + 
					"      }\r\n" + 
					"	]\r\n" + 
					"}\r\n" +
					"Transform{\r\n" + 
					"	translation 0 -1 0\r\n" + 
					"	children [\r\n" + 
					"			";
			vrmlFile.write(base);
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void endVRMLCode() {
		try {
			end = "	]\r\n" + 
					"}";
			vrmlFile.write(end);
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void prepBackground(int bgWidth, int bgHeight, String bgImage) {
		try {  
			String bGround = "		Transform {\r\n" + 
					"			translation " + (bgWidth/2) + " 0 " + getHeightCorrection() + "\r\n" +
					"			children [\r\n" + 
					"				Shape {\r\n" + 
					"					appearance Appearance {\r\n" + 
					"						texture ImageTexture {\r\n" + 
					"							url " + "\"" + bgImage + "\"" + "\r\n" +
					"						}\r\n" + 
					"					}\r\n" + 
					"					geometry Box {\r\n" + 
					"						size " + bgWidth + " 0.1 " + bgHeight + "\r\n" +
					"					}\r\n" + 
					"				}\r\n" + 
					"			]\r\n" + 
					"		}\r\n";
			vrmlFile.write(bGround);
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	public static void buildWallVRML(int x1, int y1, int x2, int y2, int depth, int height, String imagePath) {
		try {
			String walls = "		Transform {\r\n" + 
				"			translation " + mediumPointX(x1 , x2) + " " + (height /2) +
				" " + mediumPointY(y1, y2) + "\r\n" +
				"			rotation 0 1 0 " + angle(x1, y1, x2, y2)
				+ "\r\n" +
				"			children[\r\n" + 
				"				Shape {\r\n" + 
				"					appearance Appearance {\r\n" + 
				"						texture ImageTexture {\r\n" + 
				"							url " + "\"" + imagePath + "\"" + "\r\n" +
				"						}\r\n" + 
				"						textureTransform TextureTransform {\r\n" + 
				"							scale 10 2\r\n" + 
				"						}\r\n" + 
				"					}\r\n" + 
				"					geometry Box {\r\n" + 
				"						size " + distance(x1, y1, x2, y2) + " " + height + " " + depth + "\r\n" +
				"					}\r\n" + 
				"				}\r\n" + 
				"			]\r\n" + 
				"		}\r\n";
//			System.out.println(Integer.toString(x1) +" " + Integer.toString(y1) +" "
//								+ Integer.toString(x2) +" " + Integer.toString(y2) +" " + Integer.toString(height) +" "
//								+ Integer.toString(depth));
//			System.out.println(mediumPointX(x1,x2));
//			System.out.println(mediumPointY(y1, y2));
			//System.out.println(angle(x1, y1, x2, y2));
//			System.out.println(distance(x1, y1, x2, y2));
//			System.out.println(imagePath);
//			System.out.println(height);
//			System.out.println(depth);
			
			vrmlFile.write(walls);
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	public static void buildTreesVRML(int xLocation, int yLocation, int treeWidth, int treeHeight, int treeImg) {
		try {
		String tree = "		Transform{\r\n" + 
				"			translation " + xLocation + " " + treeHeight/2 + " " + yLocation + "\r\n" +
				"			children[\r\n" + 
				"				Billboard {\r\n" + 
				"					children[\r\n" + 
				"						Shape {\r\n" + 
				"							appearance Appearance {\r\n" + 
				"								texture ImageTexture{\r\n" + 
				"									url " + "\"TreeImage" + treeImg + ".png\"" + "\r\n" +
				"								}\r\n" + 
				"							}\r\n" + 
				"							geometry Box {\r\n" + 
				"								size " + treeWidth + " " + treeHeight + " 0.0001\r\n" +
				"							}\r\n" + 
				"						}\r\n" + 
				"					]\r\n" + 
				"				}\r\n" + 
				"			]\r\n" + 
				"		}\r\n";
		vrmlFile.write(tree);
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	
	public static double distance(int x1, int y1, int x2, int y2) {
//		x1 = xOne;
//		y1 = yOne;
//		x2 = xTwo;
//		y2 = yTwo;
		
		distance = Math.sqrt((Math.pow(x1 - x2, 2)) + (Math.pow(y1 - y2, 2)));
		
		return distance;	
	}
	
	public static double angle(int x1, int y1, int x2, int y2) {
		angle = (y2 - y1);
		angle = angle/(x2-x1);
		angle = Math.atan(angle);
		
		return -angle;
	}
	
	public static double mediumPointX(int x1,int x2/* ArrayList<Integer> list */) {
//		this.x1 = x1; 
//		this.x2 = x2;
		
		medPointX = (x1 + x2)/2;
		
		return medPointX;
	}
	
	public static double mediumPointY(int y1,int y2/* ArrayList<Integer> list */) {
//		this.y1 = y1;
//		this.y2 = y2;
		
		medPointY = (y1 + y2)/2;
		
		return medPointY;
	}
	
	//se usa donde se implemente este write, se encuentra la altura de la imagen de background
	public static void setHeightCorrection(int bgHeight) {
		heightCorrection = 681 - bgHeight + (bgHeight/2);
	}
	
	public static double getHeightCorrection() {
		return heightCorrection;
	}
	
	public static void mapVRML(String mapName) {
		open(System.getProperty("user.dir") + "\\vrmlmap\\" + mapName + ".wrl");
		setHeightCorrection(Integer.parseInt(Reader.getBackH()));
		startVRMLCode(Integer.parseInt(Reader.getBackH()));
		prepBackground(Integer.parseInt(Reader.getBackW()), Integer.parseInt(Reader.getBackH()), Reader.getBackground());
		
		for(int i = 0 ; i < Reader.getLocations().size() ; i = i + 7) {
			buildWallVRML(Integer.parseInt(Reader.getLocations().get(i)), Integer.parseInt(Reader.getLocations().get(i+1)),
					Integer.parseInt(Reader.getLocations().get(i+2)), Integer.parseInt(Reader.getLocations().get(i+3)),
					Integer.parseInt(Reader.getLocations().get(i+4)), Integer.parseInt(Reader.getLocations().get(i+5)), Reader.getLocations().get(i+6));
		}
		
		for(int i = 0; i < Reader.getTreeInfo().size(); i = i + 5) {
			buildTreesVRML(Reader.getTreeInfo().get(i+1), Reader.getTreeInfo().get(i+2), Reader.getTreeInfo().get(i+3), 
					Reader.getTreeInfo().get(i+4), Reader.getTreeInfo().get(i));
			
//			System.out.println(Reader.getTreeInfo().get(i + 1) + " " + Reader.getTreeInfo().get(i+2) + " " +
//					Reader.getTreeInfo().get(i+3) + " " + Reader.getTreeInfo().get(i + 4) + " "
//					+ Reader.getTreeInfo().get(i));
		}
		endVRMLCode();
		close();
	}
	
}
