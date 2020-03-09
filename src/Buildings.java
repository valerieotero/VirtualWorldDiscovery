import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JComponent;

public class Buildings extends JComponent{

	public static class Walls{
		int x1, y1, x2, y2, width, height;
		String picture;

		public Walls(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public Walls(int width, int height, String picture) {
			this.width = width;
			this.height = height;
			this.picture = picture;
		}
	}
	//Main idea:
	/*
	 * lines stores 2D coordinates of the walls
	 * space stores 3D coordinates of the walls
	 * 
	 * index 1 to n of lines has the values for building n
	 * index 1 of space has the 3D values of that building n
	 * 
	 * 
	 */
	
	//stores the 2D coordinates
	public final Stack<Walls> lines = new Stack<Walls>();
	
	//Stores the 3D space coordinates
	public final LinkedList<Walls> space = new LinkedList<Walls>();

	//When called adds the 3D space coordiantes to the list
	public void addWalls3D(int width, int height, String picture) {
		space.add(new Walls(width, height, picture));
//		for(Walls s: space) {
//			System.out.println("Position in linkedlist = "+s);
//		}
	}
	
	//When called adds the 2D coordiantes to a list
	//later this list can be called to paint previes lines
	public void addWalls2D(int x1, int y1, int x2, int y2) {
		lines.push(new Walls(x1, y1, x2, y2));
//		while(!lines.isEmpty()) {
//			System.out.println("Position in stack = "+lines.pop());
//		}
		//repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Walls line : lines) {
			g.setColor(Color.BLACK);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}
	}
}