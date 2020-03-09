import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/*
 * 
 * Created by: Valerie Otero
 * End: March 7/2020
 * 
 * Line class basically paints the lines created in the Menu class.
 * 
 */
public class Line extends JPanel{

	private SaveCoordinates coordinates = new SaveCoordinates();
	List<Integer> coordinateList = coordinates.getCoordinates(); // Add coordinates to the list

	// Paints the components
	@Override 
	public void paintComponent(Graphics g)
	{  	    	
		super.paintComponent(g);
		g.drawLine(coordinateList.get(0), coordinateList.get(1), coordinateList.get(2), coordinateList.get(3)); // x1, y1, x2, y2 
	}
}
