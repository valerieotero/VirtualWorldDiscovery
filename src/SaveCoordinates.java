import java.util.ArrayList;

public class SaveCoordinates extends ArrayList<Integer> {

	ArrayList<Integer> coordinateList = new ArrayList<>();	
		
	public SaveCoordinates() {
		coordinateList.add(0);   //x1
		coordinateList.add(0);   //y1
		coordinateList.add(0);   //x2 
		coordinateList.add(0);   //y2
	}
	
	public ArrayList<Integer> getCoordinates() {
		return coordinateList;
	}

	public void setCoordinates(ArrayList<Integer> coordinates) {
		 this.coordinateList = coordinates;
	}	

}