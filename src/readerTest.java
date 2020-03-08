import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class readerTest {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\src\\Configuration_test"));
		String[] tokens = null;
		ArrayList<String> str = new ArrayList<String>();
		int x1, y1, x2, y2, w, h;
		String picture;
		
		
	    while(scanner.hasNext()){
	        tokens = scanner.nextLine().split("[(|)|,| ]");
		    for(int i = 0; i < tokens.length; i++) {
		    	str.add(tokens[i]);
		    }
	    }

	    String data = str.toString();
	    System.out.println(data);
	    String[] arr = data.split(",");
//	    for(int i = 0; i < arr.length; i++) {
//	    	System.out.println(arr[i]);;
//	    }
	    int i = 0;
	    while(i != arr.length) {
	    	//System.out.println("hey");
	    	if(arr[i].equals(" name")) {
	    		System.out.println(arr[i]);
	    		i++;
	    	}
	    	i++;
	    }
	}  
}

