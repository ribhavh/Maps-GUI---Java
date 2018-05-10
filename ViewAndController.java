package hw8;

import java.util.Scanner;

/**
 * ViewAndController gives a menu to the user and offers various functions like
 * listing all buildings and finding paths between two buildings.
 * 
 * @author RibhavHora
 */

public class ViewAndController extends Functions {

	// This class does not represent an ADT.

	/**
	 * Provides a menu with various functions to execute with a given command.
	 * 
	 * 
	 * @effects prints a menu to the user with various functionalities, the user
	 *          can enter the said commands to execute them. q quits the
	 *          program.
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		menu();
		boolean programEnd = false; // keeps track when the program should end
		boolean hashIgnore = false; // helps ignore sentences starting with #
									// and empty sentences
		while (!programEnd) {
			if (!hashIgnore) {
				System.out.print("Enter an option ('m' to see the menu): ");
			}
			String input = console.nextLine();
			if (input.startsWith("#") || input.isEmpty()) {
				System.out.println(input);
				hashIgnore = true;
				continue;
			} else if (input.equals("m")) {
				menu();
				hashIgnore = false;
			} else if (input.equals("r")) {
				findPath(console);
				hashIgnore = false;
			} else if (input.equals("b")) {
				listBuildings();
				hashIgnore = false;
			} else if (input.equals("q")) {
				programEnd = true;
			} else {
				System.out.println("Unknown option");
				hashIgnore = false;
				System.out.println();
			}
		}
		console.close();
	}
}
