import java.applet.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class LionsLambs {
	
	public static Agent[] board = new Agent[25];
	public static int WIN_STATE = 0;
	public static int TURN = 0;
	public static int LAMB_COUNT = 0;

	public static void init() {
		board[0] = new Lion("Leo");
		board[4] = new Lion("Leu");
		board[20] = new Lion("Love");
		board[24] = new Lion("Lejon");
	}

	public static void main(String[] args) {
	    init();
	    playGame();
	}

	// call: java LionsLambs <pos1> (<pos2>)	

    /* This method describes the game loop */
	public static void playGame(){
		String[] input;
		
		// setUpGame(); -- the basic board should be drawn here

		while (WIN_STATE == 0) {
		    // updateBoard(); -- the board should be updated according to the positions filled
			Board.drawBoard(board);
			System.out.print("Lambs player's turn: "); // M: what if move is invalid; give player another chance?
			input = readInput();
			setMove(input);
			Board.drawBoard(board);
			System.out.print("Lions player's turn: ");
			input = readInput();
			setMove(input);
		}
		
		if (WIN_STATE == 1) {
			System.out.println("Lambs win!");
			System.exit(1);
		}
		
		else if (WIN_STATE == -1) {
			System.out.println("Lions win!");
			System.exit(1);
		}
	}

    /* Reads the users input - moves */
	public static String[] readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		try {
			input = br.readLine().split("\\s");
			return input;
		} 
		catch (IOException ioe) {
			System.out.println("When does this error show!?");
			return null;
      	}
	}

    /* Determines kind of move and moves if legitimate */
	public static void setMove(String[] input) {
		int pos1 = Integer.parseInt(input[0]);
		int pos2 = 0; // M: Why do you need this?
		if (input.length == 2) {
			pos2 = Integer.parseInt(input[1]);
		}

		// hiermee kan je de class opvragen zie ook Board.java
		// Class agent = board[pos1].getClass();
		
		/* Place lamb in game */
		if (input.length == 1) {
			board[pos1] =  new Lamb("name");
		}
		
	    /* Move lions and lambs */
	    else if (board[pos1] != null && board[pos2] == null) {
	    	if(board[pos1].move(pos1, pos2) || board[pos1].specialMove(pos1, pos2)) {
	    		board[pos2] = board[pos1];
	    		board[pos1] = null;
	    	} else {
	    		System.out.println("Invalid move");
	    	}
	    } else {
    		System.out.println("No piece found at " + pos1 + " or no room at " + pos2);
    	}
    }
	    	

	    
	    
}
