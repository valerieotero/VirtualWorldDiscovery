import java.util.ArrayList;
import java.util.Scanner;

public class TestCreator {
	public static String question;
	public static String answer;
	public static ArrayList<String> answers = new ArrayList<String>();
	public static String[] order = {"first" , "second", "third", "fourth", "fifth"};
	public static ArrayList<String> questions = new ArrayList<String>();

	
	
	public static void main(String[] args) {
		TestCreator(questions, order, answers, question, answer);
		
		for(int i = 0 ; i < answers.size(); i++) {
			System.out.println( answers.get(i));
		}
		
	}
	

	public static void TestCreator(ArrayList<String> questions , String[] order, ArrayList<String> answers, String question, String answer){

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("How many questions will you create?");
		int numQ = scanner.nextInt();
		while(numQ > 5) {
			System.out.println("That value is too high.");
			numQ = scanner.nextInt();
			
		}
		
		for(int i = 0 ; i < numQ ; i++) {
			if(i == 0) {
				//System.out.println("Write the first question.");
				question = scanner.nextLine();
			}
			
			System.out.println("Write the "  + order[i] + " question." );
			question = scanner.nextLine();
			questions.add(i, question);
			
				System.out.println("How many answers will you create?");
				int numA = scanner.nextInt();
				while(numA > 5) {
					System.out.println("That value is too high.");
					numA = scanner.nextInt();
				}
					
				for(int j = 0; j < numA; j++) {
					if(j==0) {
						answer = scanner.nextLine();
					}
					System.out.println("Write the " + order[j] + " answer, but first :");
					System.out.println("set a 0 or 1 before it separated only by a comma like so");
					System.out.println("0,This building has 32 classrooms.");
			
					answers.add(j, scanner.nextLine());
				}	

		}
		System.out.println("You have finished creating the questions for this building.");
	}
	
	
}
