package Avatar;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Manages and draws game graphics and images.
 */
public class GraphicsManager {
	
	private BufferedImage avatarImg;			
	
	private BufferedImage leftAvatarImg; 

		
	//Constructor. Creates a new graphics manager and loads the game images.	 
	public GraphicsManager(){
		// load images
		try {
			this.avatarImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/megaMan3.png"));						
			this.leftAvatarImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/reversemegaMan3.png"));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"Unable to get Avatar image", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	
	 //Draws a MegaMan image to the specified graphics canvas.	
	public void drawAvatar (Avatar avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(avatarImg, avatar.x, avatar.y, observer);	
	}
	
	
	public void drawLeftAvatar (Avatar avatar, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(leftAvatarImg, avatar.x, avatar.y, observer);	
	}	

}
