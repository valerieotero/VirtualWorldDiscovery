package Avatar;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class GraphicsManager {
	
	private BufferedImage avatarImg;		
	private BufferedImage leftAvatarImg; 
	
	private BufferedImage marioImg;		
	private BufferedImage leftMarioImg; 
	
	private BufferedImage tree1;
	private BufferedImage tree2;
	private BufferedImage tree3;

	
	/* Author: Valerie Otero | Date: April 12 2020
 	 * Creates a new graphics manager and loads the game images. */ 
	public GraphicsManager(){
		// load images
		try {
			this.avatarImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/megaMan.png"));						
			this.leftAvatarImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/megaManLeft.png"));	
			this.marioImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/mario.png"));						
			this.leftMarioImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/marioLeft.png"));
			this.tree1 = ImageIO.read(getClass().getClassLoader().getResource("Resources/TreeImage1.png"));
			this.tree2 = ImageIO.read(getClass().getClassLoader().getResource("Resources/TreeImage2.png"));
			this.tree3 = ImageIO.read(getClass().getClassLoader().getResource("Resources/TreeImage3.png"));			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"Unable to get Avatar image", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	

	/* Author: Valerie Otero | Date: April 11 2020
	 * Draws a avatar image to the specified graphics canvas. */		
	public void drawAvatar (Avatar avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarImg, avatar.x, avatar.y, observer);	
	}	
	
	/* Author: Valerie Otero | Date: April 11 2020
	 * Draws a avatar image to the specified graphics canvas. */
	public void drawLeftAvatar (Avatar avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(leftAvatarImg, avatar.x, avatar.y, observer);	
	}	

	
	public void drawMario(Avatar avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(marioImg, avatar.x, avatar.y, observer);	
	}			
	public void drawLeftMario (Avatar avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(leftMarioImg, avatar.x, avatar.y, observer);	
	}	
		
	
	public void drawTree1 (int x, int y, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(tree1 , x, y, observer);
	}
	public void drawTree2 (int x, int y, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(tree2 , x, y, observer);
	}
	public void drawTree3 (int x, int y, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(tree3 , x, y, observer);
	}
}