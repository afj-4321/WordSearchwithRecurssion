package edu.wmich.edu.CS1120.LA2.NMacklinCamel;

import java.util.Random;

//This is the WordSearch class
public class WordSearch {
	// Private Fields: word (String), seed (Integer), board (Character[][]), check (Boolean[][]) 
		// Public field: found (Boolean) equal to false
	private String word;
	private int seed;
	private char[][] board;
	private boolean check[][];
	public boolean found = false;
	
	// Constructor: Initialize fields word, seed, check, and board
	public WordSearch(String word, int seed,boolean[][] check) {
		this.word = word;
		this.seed = seed;
		this.check = check;
		setBoard();
	}// end of WordSearch constructor

	//getters and setters
	public boolean[][] getCheck() {
		return check;
	}

	public void setCheck() {
		boolean[][] checker = new boolean[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j< 4; j++) {
				check[i][j] = false;
			}
		}
		this.check = checker;
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getSeed() {
		return seed;
	}
	public void setSeed(int seed) {
		this.seed = seed;
	}
	
	public char[][] getBoard() {
		return board;
	}
	public void setBoard() {
		char b[][] = new char[4][4];
		Random r = new Random(seed);
		for (int i = 0; i < 4; i++) {
			 for (int j = 0; j < 4; j++) {
				 b[i][j] = (char) (r.nextInt(26) + 65);
			 }
		}
		this.board = b;
	}
	
	// end of getters and setters
	

	// Method: ----------------------------------------------------------------------------------------------------	
	// Method recursionSearch: Take in two integers and the user’s word as parameters, 
	// and then recursively search the word on the board, by using a for loop within a for 
	// loop, and then sending those two for loop’s integers and a substring of the user’s 
	// word, with the first letter taken off 
	public boolean[][] recursionSearch(int x,int y,String theWord) {
		// This method will be called by the WordSearchTest class and it will give a 0 for 
		// 	the first integer, another 0 for the second integer, and the user’s word
		if (theWord.length() <= 0 || theWord.length() == 1) {
			if (theWord.length() == 1) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (theWord.charAt(0) == board[i][j]) {
							this.check[i][j] = true;
							int find = 0; 
							for (int a = 0; a < 4; a++) {
								for (int b = 0; b < 4; b++) {
									if (this.check[a][b] == true) {
										find++;
									} 
								}
							}
							// If the letter is not found at any time, 
							// 	the public field that is Boolean 
							// 	will stay as false and be 
							//	returned to the 
							//	WordSearchTest class as well

							if (find < word.length() - 1) {
								found = false;
							} else {
								found = true;
							}
							found = true;
							return this.check;
						}
					}
				}
			}
			int find = 0; 
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if (this.check[a][b] == true) {
						find++;
					} 
				}
			}
			if (find < word.length() - 1) {
				found = false;
			} else {
				found = true;
			}
			found = true;
			return this.check;
		} else {
			// This will check for the first letter in the user’s word if it is on the board, if
			//	it is, the Boolean two-dimensional array’s exact spot will be turned to 
			//	true and it will check all 8 possibilities if the next letter is up, diagonally 
			// 	right up, right, diagonally right down, down, diagonally left down, left, // 	or diagonally left up
	
		for (int i = x; i < 4 ; i++) {
			// If it is any of these it will use recursion to send back the exact position using a 
			//	for loop imbedded within another for loop and send back the substring 
			// 	taking off the first letter

			for (int j = y; j < 4; j++) {
				if (theWord.charAt(0) == board[i][j]) {
					if (i != 3 && theWord.charAt(1) == board[i++][j]) {// down
						this.check[i][j] = true;
						return recursionSearch(i++, j, theWord.substring(1));
					}
					if (i != 0 && theWord.charAt(1) == board[i--][j]) {// up
						this.check[i][j] = true;
						return recursionSearch(i--, j, theWord.substring(1));
					}
					if (j != 3 && theWord.charAt(1) == board[i][j++]) {// right
						this.check[i][j] = true;
						return recursionSearch(i, j++, theWord.substring(1));
					}
					if (j != 0&& theWord.charAt(1) == board[i][j--]) {// left
						this.check[i][j] = true;
						return recursionSearch(i, j--, theWord.substring(1));
					}
					if (i != 0 && j != 3 && theWord.charAt(1) == board[i--][j++]) {// diag up right
						this.check[i][j] = true;
						return recursionSearch(i--, j++, theWord.substring(1));
					}
					if (i != 0 && j != 0 && theWord.charAt(1) == board[i--][j--]) {// diag up left
						this.check[i][j] = true;
						return recursionSearch(i--, j--, theWord.substring(1));
					}
					if (i != 3 && j != 0 && theWord.charAt(1) == board[i++][j--]) {// diag down left
						this.check[i][j] = true;
						return recursionSearch(i++, j--, theWord.substring(1));
					}
					if (i != 3 && j != 3 && theWord.charAt(1) == board[i++][j++]) {// diag down right 
						this.check[i][j] = true;
						return recursionSearch(i++, j++, theWord.substring(1));
					}
					this.check[i][j] = true;
				}
			}
		}
		}
		// This class also returns the Boolean two-dimensional array that pinpoints the 
		// 	exact spots where the user’s word was found
		return this.check;
		
	} // end of recursionSearch method

} // end of WordSearch class
