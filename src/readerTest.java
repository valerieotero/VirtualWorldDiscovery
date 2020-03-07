import java.io.File;
import java.util.Scanner;

public class readerTest {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\src\\Configuration_test");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }
}