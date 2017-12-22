/**
 * 
 */
package nl.surance.user.models;

/**
 * @author praveen
 *
 */
public interface Constants {
	
	   // Name-constants to represent the seeds and cell contents
	   public static final int EMPTY = 0;
	   public static final int PLAYER = 1;
	   public static final int PC = 10;
	   public static final int MINIMUM_BALANCE = 0;
	 
	   // Name-constants to represent the various states of the game
	   public static final int PLAYING = 0;
	   public static final int DRAW = 1;
	   public static final int PLAYER_WON = 3;
	   public static final int PC_WON = 30;
	 
	   // The game board and the game status
	   public static final int ROWS = 3, COLS = 3; // number of rows and columns
	   public static int[][] board = new int[ROWS][COLS]; // game board in 2D array
	                                                      //  containing (EMPTY, CROSS, NOUGHT)
	   public static int currentState=0;  // the current state of the game
	                                    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
	   public static int currentPlayer=0; // the current player (CROSS or NOUGHT)
	   public static int currntRow=0, currentCol=0; // current seed's row and column
	 

}
