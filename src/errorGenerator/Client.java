package errorGenerator;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		ErrorGenerator gn = new ErrorGenerator();
		System.out.println("****************** TYPING ERROR GENERATOR *******************");
		System.out.println("*************************************************************");
		System.out.println("******************* Type \"stop\" to stop *********************");
		System.out.println("*************************************************************\n\n");
		String str;
		System.out.print("Enter a sentence: ");
		while (scn.hasNextLine()) {
			str = scn.nextLine();
			if (str.equals("stop")) {
				System.out.println();
				System.out.println();
				System.out.println("************************** EXITING **************************");
				break;
			}
			String ans = gn.generateTypingErrorInLine(str);
			System.out.println("The line with error is : " + ans);
			System.out.println();
			System.out.print("Enter a line: ");
		}

		scn.close();
	}

}
