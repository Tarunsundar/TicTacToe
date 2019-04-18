import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private OorX[][] board;
    private Scanner scanner = new Scanner(System.in);
    private String winner;
    private int freeSpaces;
    private int xWins, oWins;


    public static final int NUM_BOARD_SPACES = 9;

    public Board() {
        board = new OorX[3][3];
        freeSpaces = 9;
        winner = "none";
    }

    public OorX get(int i) {
        OorX oorX;
        switch (i) {
            case 1:
                oorX = getValueAt(0, 0);
                break;
            case 2:
                oorX = getValueAt(0, 1);
                break;
            case 3:
                oorX = getValueAt(0, 2);
                break;
            case 4:
                oorX = getValueAt(1, 0);
                break;
            case 5:
                oorX = getValueAt(1, 1);
                break;
            case 6:
                oorX = getValueAt(1, 2);
                break;
            case 7:
                oorX = getValueAt(2, 0);
                break;
            case 8:
                oorX = getValueAt(2, 1);
                break;
            case 9:
                oorX = getValueAt(2, 2);
                break;
            default:
                oorX = null;
                break;
        }
        return oorX;
    }

    public OorX getValueAt(int row, int column) {
        // STEP 2: WRITE CODE HERE: WHAT ERROR CONDITION
        // MUST BE CHECKED?
        if (board[row][column] == null) {
            return null;
        } else
            return board[row][column];

    }




    public boolean setValueAt(int row, int column, String value) {
        // STEP 3: WRITE CODE HERE: WHAT ERROR
        // CONDITIONS MUST BE CHECKED BEFORE YOU ASSIGN?
        // FOR THIS EXAMPLE JUST DISPLAY AN ERROR MESSAGE IF
        // ILLEGAL INPUT ENTERED
        boolean success;
        if (getValueAt(row, column) == null) {
            if (value.equals("X") || value.equals("x")) {
                board[row][column] = OorX.X;

            } else if (value.equals("O") || value.equals("o")) {
                board[row][column] = OorX.O;
            }
            success = true;
            freeSpaces--;
        } else {
            System.out.println("Value already set at this position, please try some other position");
            success = false;
        }
        return success;
    }


    private String check(OorX o){
        if(o != null){
            if(o.equals(OorX.O)){
                return  "O";
            }
            else
                return  "X";
        }

        else return  " ";
    }

    public void drawBoard(){
        System.out.println(check(get(1)) +" "+ " | " + check(get(2)) +" "+ " | " + check(get(3)));
        System.out.println("¯¯¯|¯¯¯¯|¯¯¯");
        System.out.println(check(get(4)) +" "+ " | " + check(get(5)) +" "+ " | " + check(get(6)));
        System.out.println("¯¯¯|¯¯¯¯|¯¯¯");
        System.out.println(check(get(7)) +" "+ " | " + check(get(8)) +" "+ " | " + check(get(9)));
        System.out.println();
     }

    private boolean checkDiagonal() {
        boolean result;
        if (get(3) == get(5) && get(5) == get(7)&& get(3) != null) {
            DecideGame(get(3));
            result = true;
        } else if (get(1) == get(5) && get(5) == get(9)&& get(1) != null) {
            DecideGame(get(1));
            result = true;
        } else result = false;
        return result;
    }

    private boolean checkHorizontal() {
        if ((get(1) == get(2) && get(2) == get(3) && get(3) != null)) {
            DecideGame(get(1));
            return true;
        } else if (get(4) == get(5) && get(5) == get(6)&& get(6) != null) {
            DecideGame(get(4));
            return true;
        } else if ((get(7) == get(8) && get(8) == get(9))&& get(9) != null) {
            DecideGame(get(7));
            return true;
        } else return false;
    }

    private boolean checkVertical() {
        if ((get(1) == get(4) && get(4) == get(7))&& get(1) != null) {
            DecideGame(get(1));
            return true;
        } else if ((get(2) == get(5) && get(5) == get(8))&& get(2) != null) {
            DecideGame(get(2));
            return true;
        } else if ((get(3) == get(6) && get(6) == get(9))&& get(3) != null) {
            DecideGame(get(3));
            return true;
        } else return false;
    }

    private boolean checkBoard() {
        if (checkDiagonal() || checkHorizontal() || checkVertical()) {
            return true;
        } else return false;
    }


    public void DecideGame(OorX o) {
        if (o == OorX.O) {
            GameResult("O");
        } else if (o == OorX.X) {
            GameResult("X");
        }
    }

    public String GameResult(String s){
        if(s.equals("o")||s.equals("O")){
            System.out.println("O wins!!");
            winner = "o";
            oWins++;
        }
        else if(s.equals("x")||s.equals("X")){
            System.out.println("X wins!!");
            winner = "x";
            xWins++;
        }
        else {
            System.out.println("Match Tie!");
            winner = "None";
        }
        return winner;
    }

    public String getWinner() {
        return winner;
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public boolean gameOver() {
        // STEP 4: DISCUSS THE KINDS OF THINGS THIS METHOD
        // WOULD NEED TO DO TO FIND IF GAME OVER.
        // KUDOS TO ANYONE WHO COMPLETES THIS
        boolean result;
        if (freeSpaces < 5) {
            if (freeSpaces == 0) {
                if(!checkBoard()) {
                    GameResult("Tie");
                    result = true;
                }
                else result=true;
            } else {
                result = checkBoard();
            }
        } else result = false;
        return result;
    }
}
