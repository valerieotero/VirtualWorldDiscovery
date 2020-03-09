import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class readerTest {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\src\\Configuration_test"));
		String[] tokens = null;
		ArrayList<String> str = new ArrayList<String>();
		ArrayList<Integer> walls = new ArrayList<Integer>();
		ArrayList<String> pictures = new ArrayList<String>();
		Buildings b = new Buildings();

		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("[(|)|,| ]");
			for(int i = 0; i < tokens.length; i++) {
				str.add(tokens[i]);
			}
		}

		String data = str.toString();
		
		
		String[] arr = data.split(",|\\]");

//		for(int i = 0; i < arr.length; i++) {
//			System.out.println("Position "+i+ " = "+arr[i]);;
//		}

		int i = 0, count = 0;
		while(i < arr.length) {
			if(arr[i].equals(" Building")){
				if(i+5 < arr.length) i = i+5;
				else break;
				while(i < arr.length && arr[i].equals(" Wall")){
					if(i+4 < arr.length)i = i+4;
					else break;
					while(count != 8) {
						try {
							walls.add(Integer.parseInt(arr[i].trim()));
						}catch(NumberFormatException ex){
						}
						i++;
						count++;
					}
					pictures.add(arr[i].trim());
					count = 0;
					i++;
				}

				i++;
			}
			else {
				i++;
			}
		}
//		for(Integer l : walls) {
//			System.out.println(l);
//		}
//		for(String s : pictures) {
//			System.out.println(s);
//		}
		for(int j = 0, k = 0; j < walls.size() && k < pictures.size(); j+=6, k++) {
			System.out.println(walls.get(j));
			System.out.println(walls.get(j+1));
			System.out.println(walls.get(j+2));
			System.out.println(walls.get(j+3));
			System.out.println(walls.get(j+4));
			System.out.println(walls.get(j+5));
			System.out.println(pictures.get(k));
			b.addWalls2D(walls.get(j), walls.get(j+1),walls.get(j+2),walls.get(j+3));
			b.addWalls3D(walls.get(j+4), walls.get(j+5), pictures.get(k));
		}
	}  
}



