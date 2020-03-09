/*
 * Created by: Yamil J. Gonzalez
 * Still going
 * 
 * This Wall class helps to create wall objects that can be stored in another data structure for easy repainting
 * 
 */

public class Walls {
	int x1, y1, x2, y2, width, height;
	String picture;

	public Walls(int x1, int y1, int x2, int y2, int width, int height) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.width = width;
		this.height = height;
	}
	public Walls(String picture) {
		this.picture = picture;
	}
}