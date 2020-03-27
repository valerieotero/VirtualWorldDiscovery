package Classes;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tree extends JLabel{	
				
	public Tree(int imageNumber, int x, int y, int width ,int height) {	
		
		this.setIcon(new ImageIcon(Tree.class.getResource("/Resources/Tree"+imageNumber+".png")));	
		this.setBounds(x, y, width, height);
	}
}
