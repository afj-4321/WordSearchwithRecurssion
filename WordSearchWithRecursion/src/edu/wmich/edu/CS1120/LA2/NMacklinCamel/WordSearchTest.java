/* 
 * Neil Macklin-Camel
 * Lab 2
 * CS 1120
 * Lillien
 */
package edu.wmich.edu.CS1120.LA2.NMacklinCamel;
import java.util.*;

//The WordSearchTest class for testing the WordSearch class.
public class WordSearchTest {
	// Method main: Call the method theOutput
	public static void main(String[] args)  {
		theOutput();
	} // end of main method
	
// Other methods: ------------------------------------------------------------------------------------------------------
	// Method userSeed: Ask the user for a seed (an integer from 1 to 9999)
	public static int userSeed() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a seed from:  ");
		int seed;
		// Make sure what the user enters is an integer and nothing else using a while loop
		while (!sc.hasNextInt()) {
			System.out.print("Enter a seed from:  ");
			sc.next();
		}
		seed = sc.nextInt();
		// Then make sure what the user enters is an integer between 1 and 9999 using a while 
		// 	loop
		if (seed < 1 || seed > 9999) {
			while (seed < 1 || seed > 9999) {
				System.out.print("Enter a seed from:  ");
				seed = sc.nextInt();
			}
		}
		// Return the integer once everything is checked
		return seed;
	} // end of seed method
	
	// Method userWord: Ask the user for a word (only letters)
	public static String userWord()  {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a word to search for on the word search board:  \n");
		// Make sure what the user enters is a word with only letters using a while loop
		while (!sc.hasNext("[A-Za-z]+")) {
			System.out.println("Invalid response.\n");
			System.out.println("Enter a word to search for on the word search board:  \n");
		    sc.next();
		}
		String user = sc.nextLine();
		System.out.println();
		// Return the user’s word in all upper-case letters
		return user.toUpperCase();
	} // end of userWord method
	
	// Method keepAsking: Ask the user to enter either a ‘y’ to continue and enter another word to 
	// search or a ‘n’ to exit the program
	public static boolean keepAsking() {//maybe loop it all in this method?
		Scanner sc = new Scanner(System.in);
		boolean r = true;
		System.out.println("Do you want to search for another word? (Y / N)\n");
		String yOrN = (sc.nextLine());
		// Make sure what the user enters is only a ‘y’ or a ‘n’
		while (true) {
			if ("y".equalsIgnoreCase(yOrN) || "n".equalsIgnoreCase(yOrN)) {
				break;
			}
			System.out.println("Invalid response.\n");
			System.out.println("Do you want to search for another word? (Y / N)\n");
			yOrN = (sc.nextLine());
		}
		// If the user enters a ‘y’ it makes a boolean equal to true
		if ("y".equalsIgnoreCase(yOrN)) {
			r = true;
		} 
		// If the user enters a ‘n’ it makes a boolean equal to false
		else if ("n".equalsIgnoreCase(yOrN)) {
			r = false;
		}
		// Return that boolean
		return r;
	} // end of keepAsking method
	
	// Method displayTheBox: Displays the board that is generated using the seed given by the user
	public static void displayTheBox(int seed) {
		char b[][] = new char[4][4];
		// Fill a 4 by 4 character array with the seed that the user enters
		Random r = new Random(seed);
		for (int i = 0; i < 4; i++) {
			 for (int j = 0; j < 4; j++) {
				 b[i][j] = (char) (r.nextInt(26) + 65);
			 }
		} // end of filling the 4 by 4 array
		char someChar;
		System.out.println();
		// Print out that 4 by 4 character array
		for (int i = 0; i < 4; i++) {
			System.out.println("+---+ +---+ +---+ +---+"); 
			for (int j = 0; j < 4; j++) {
				someChar = b[i][j];
				System.out.print("| " + someChar + " | ");
			}
			System.out.println("\n+---+ +---+ +---+ +---+"); 
		} // end of printing the 4 by 4 character array
	} // end of displayTheBox method
	
	// Method falses: Creates a 4 by 4 Boolean two-dimensional array filled with a false in each spot 
	public static boolean[][] falses() {
		// Fill a 4 by 4 boolean array with a false in every spot
		boolean[][] check = new boolean[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j< 4; j++) {
				check[i][j] = false;
			}
		}
		// Return that boolean array
		return check;
	} // end of falses method
	
	// Method theOutput: Repeatedly calls methods inside a while loop until the user is wants to 
	// exit the program.
	public static void theOutput() {
		// Set an integer variable equal to theSeed method
		int seed = userSeed();
		String user;
		// Set a boolean two-dimensional array equal to the method falses
		boolean[][] check = falses();
		WordSearch word;
		char arrayTest[][];
		boolean arrayTester[][];
		// Set up a regular boolean variable and make it true, this will determine 
		//	whether or not the user wants to continue and this will be set equal to 
		//	the keepAsking method

		boolean ask = true;
		while (ask == true) {
			displayTheBox(seed);
			// Set a String variable equal to the userWord method
			user = userWord();
			// Declare an object for WordSearch class
			word = new WordSearch(user,seed,check);
			// Set a character two-dimensional array to getBoard getter from the 
			//	WordSearch class
			arrayTest = word.getBoard();
			word.setCheck();
			// Set a boolean two-dimensional array to the recursionSearch method from 
			//	the WordSearch class
			arrayTester = word.recursionSearch(0, 0, user);
			// Create an If statement with a parameter found from the WordSearch class to 
			// 	see if the word was found or not, if it was found then the box will be 
			//	printed with ‘<the letter>’ the letter being the current letter being  
			//	printed out, if the word was not found then call the displayTheBox 
			//	method
			if (word.found == true) {
			System.out.println("'" + user +"' " + "was found on the board!");
				for (int i = 0; i < 4; i++) {
					System.out.println("+---+ +---+ +---+ +---+"); 
					for (int j = 0; j < 4; j++) {
						if (arrayTester[i][j] == true) {
							System.out.print("|<" + arrayTest[i][j] + ">| ");
						} else {
							System.out.print("| " + arrayTest[i][j] + " | ");
						}
					}
					System.out.println("\n+---+ +---+ +---+ +---+");
			}
			System.out.println("-------------------------------------------------------------\n\n");
			} 
			// Then find out whether the user answered ‘y’ or ‘n’ in the keepAsking 
			//	method, if ‘y’ then loop back to the top of the method, if ‘n’ then exit 
			//	the program

			ask = keepAsking(); 
			if (ask == false) {
				System.out.println("Terminating ...");
			}
		}
	} // end of theOutput method
} // end of WordSearchTest class
