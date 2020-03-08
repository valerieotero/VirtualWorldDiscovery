import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;


public class Line extends JPanel{

	private SaveCoordinates coordinates = new SaveCoordinates();
	List<Integer> coordinateList = coordinates.getCoordinates();

	//Paints the components
	@Override 
	public void paintComponent(Graphics g)
	{  	    	
		super.paintComponent(g);
		g.drawLine(coordinateList.get(0), coordinateList.get(1), coordinateList.get(2), coordinateList.get(3)); // x1, y1, x2, y2 
	}
}
