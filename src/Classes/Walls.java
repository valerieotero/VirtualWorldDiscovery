package Classes;
/*
 * Created by: Yamil J. Gonzalez
 * Still going
 * 
 * This Wall class helps to create wall objects that can be store in another data structure for easy repainting
 * 
 */

public class Walls {
	int x1, y1, x2, y2, width, height;
	String picture;

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Walls(int x1, int y1, int x2, int y2, int width, int height, String picture) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.width = width;
		this.height = height;
		this.picture = picture;
	}
	
	@Override
	public String toString(){
		String walls = "(" + x1 + "," + y1 + ")" + "(" + x2 + "," + y2 + ")" + "(" + width + "x" + height + ")" + picture;
		return walls;
	}
}