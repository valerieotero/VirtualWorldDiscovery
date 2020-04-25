package Classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class fileManager {

	public static void main(String[] args) throws IOException {
		String fileName = "C:\\Users\\yamil\\git\\VirtualWorldDiscovery\\Maps\\Test";
		List<String> newLines = new ArrayList<>();
		for (String line : Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)) {
			System.out.println(line);
//		    if (line.contains("1313131")) {
//		       newLines.add(line.replace("1313131", ""+System.currentTimeMillis()));
//		    } else {
//		       newLines.add(line);
//		    }
		}
		Files.write(Paths.get(fileName), newLines, StandardCharsets.UTF_8);

	}

}
