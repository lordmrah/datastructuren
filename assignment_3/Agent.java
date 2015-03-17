import java.util.*;

public abstract class Agent {

    public String name;
    public int position;
    public static ArrayList<Agent[]> boardHistory = new ArrayList<Agent[]>();
    
    public Agent() {
        //nothing
    }
    
    /*public Agent(String name, int position) {
        this.name = name;
        this.position = position;
    } */

    public abstract boolean specialMove(int pos1, int pos2, Agent[] board);
    // public abstract boolean validateAI(int pos1, int pos2); 

    /*public int setPosition() {
        
    } */
  
    /*  */
    public static boolean validate(Agent[] board, int pos1, int pos2, int turn) {
        //boardHistory.add(board);
    
        if (((pos2 < 0 || pos2 > 24) && pos2 < 9000) ||
             (pos1 < 0 || pos1 > 24 )) {
            return false;
        }       
        
                
        /* Simulate move and check whether this board position has occured before */
        /* Agent[] copyBoard = new Agent[25];
        for (int i=0; i<board.length; i++) {
            copyBoard[i] = board[i];
        }    
        
        if (pos2 > 9000) {
			copyBoard[pos1] =  new Lamb("name", pos1);
    	} else {
	    	copyBoard[pos2] = copyBoard[pos1];
	    	copyBoard[pos1] = null;
    	} 
        
        for (int i=0; i<boardHistory.size(); i++) {
            if (!compareBoards(copyBoard, boardHistory.get(i))) {                
                return false;
            }
        } */
        
        /* End check */
        
        // if pos2 > 9000 place lamb
        if (pos2 > 9000 && LionsLambs.LAMB_COUNT != 0 && turn == 1) {
            if (board[pos1] == null) {
                return true;
            } else{
                return false;
            }
        } 
        else if (board[pos1] != null && board[pos2] == null) {
            if((turn == 1 && board[pos1].getClass().equals(Lamb.class) && LionsLambs.LAMB_COUNT == 0) ||
                turn == -1 && board[pos1].getClass().equals(Lion.class)) {
                if (move(pos1, pos2) || board[pos1].specialMove(pos1, pos2, board)) {
                    return true;
                }
            }
        }
        if (LionsLambs.LAMB_COUNT == 0) {
            //System.out.println("No lambs left...");
            return false;
        }
        // System.out.println("Invalid move...");
        return false;
        

        // else move.
    }
    
    /* Check whether asked moves are valid */
    private static boolean move(int pos1, int pos2) {
        // System.out.println("check move");
        boolean status = false;
        int posDif = pos2 - pos1;
               
        /* Move Horizontal */
        if (posDif == 1 || posDif == -1) {
            // System.out.println(posDif);
            status = checkMoveHor(posDif, pos1); 
            return status;   
        }
        
        /* Move Vertical */
        if (posDif == 5 || posDif == -5) {
            status = checkMoveVer(pos2);
            return status;
        }
        
        /* Move Diagonal */
        if (posDif == -6 ||  posDif == 4 || posDif == 6 || posDif == -4) {
            status = checkMoveDiag(posDif, pos1);
            return status;
        } 
        return false;  
    }
    
    /* Checks whether move to the left is valid */
    private static boolean checkMoveHor(int posDif, int pos1) {
        if ((posDif == 1 && (pos1 + 1) % 5 == 0)||
            (posDif == -1 && pos1 % 5 == 0)) {
            // System.out.println("false!");
            return false;
        }
        else { 
            return true; 
        }
        
    }

    /* Checks whether a move down or upwards is valid */
    private static boolean checkMoveVer(int pos2) {
        if (pos2 > 24 || pos2 < 0) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /* Checks whether a move upwards and to the left via the diagonal is valid */
    private static boolean checkMoveDiag(int posDif, int pos1) {
        if (pos1 % 2 == 0 && posDif > 0 &&
            checkMoveHor(posDif - 5, pos1) && checkMoveVer(posDif + pos1) ) {
            return true;
        }
        if (pos1 % 2 == 0 && posDif < 0 &&
            checkMoveHor(posDif + 5, pos1) && checkMoveVer(posDif + pos1) ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /* Compares whether two boards are the same (array comparison) */
    /* private static boolean compareBoards(Agent[] copyBoard, Agent[] usedBoard) {
        int samePositions = 0;
        
       // System.out.println("yes");
        
        for (int i=0; i<copyBoard.length; i++) {
            for (int j=0; j<usedBoard.length; j++) {
                if (!((copyBoard[i] == null && usedBoard[j] == null) ||
                    (copyBoard[i] != null && usedBoard[j] != null && copyBoard[i].getClass().equals(usedBoard[j].getClass())))) {
                    return true;
                }
                else {
                    samePositions++;
                }
            }
        }
        
        
        
        if (samePositions == 25) {
            return false;
            
        }
        return true;
    } */
}
