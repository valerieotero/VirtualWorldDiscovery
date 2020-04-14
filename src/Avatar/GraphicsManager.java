package Avatar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/* Author: Valerie Otero | Date: April 12 2020
 * Manages and draws game graphics and images. */
public class GraphicsManager {
	
	private BufferedImage avatarImg;			
	
	private BufferedImage leftAvatarImg; 
	
	private BufferedImage wallImg; 
	
	private BufferedImage messageImg;

		
	//Constructor. Creates a new graphics manager and loads the game images.	 
	public GraphicsManager(){
		// load images
		try {
			this.avatarImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/megaMan3.png"));						
			this.leftAvatarImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/reversemegaMan3.png"));
			this.wallImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/4Walls.png"));	
			this.messageImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/press e.png"));
			
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

	/* Author: Valerie Otero | Date: April 11 2020
	 * Draws a avatar image to the specified graphics canvas. */
	public void draw4Walls (Rectangle wall, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(wallImg, wall.x, wall.y, observer);	
	}	
	
	public void writeMessage (Rectangle message, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(messageImg , message.x, message.y, observer);
	}
}