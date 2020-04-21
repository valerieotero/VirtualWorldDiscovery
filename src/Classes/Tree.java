package Classes;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/* Author: Valerie Otero | Date: March 22 2020
 * This class draws the trees in the CreatedMapPanel.
 * The constructor receives the necessary parameters to draw the trees as JLabels with icons.*/

public class Tree extends JLabel{	
				
	public Tree(int imageNumber, int x, int y, int width ,int height) {	
		
		this.setIcon(new ImageIcon(Tree.class.getResource("/Resources/TreeImage"+imageNumber+".png")));	
		this.setBounds(x, y, width, height);
	}
}
