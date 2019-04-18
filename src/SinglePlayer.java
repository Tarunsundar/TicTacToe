import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SinglePlayer {

    private Board board;
    private String player;
    private String bot;
    private Scanner scanner = new Scanner(System.in);
    private int[] freeSpaces = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int[] opponentMove, botMove;
    ;

    public SinglePlayer(String p, String b) {
        this.player = p;
        this.bot = b;
        opponentMove = new int[4];
        botMove = new int[5];
    }

    public String getPlayer() {
        return player;
    }

    public boolean setBoard(String s, int pos) {
        boolean b;
        switch (pos) {
            case 1:
                b = board.setValueAt(0, 0, s);
                break;
            case 2:
                b = board.setValueAt(0, 1, s);
                break;
            case 3:
                b = board.setValueAt(0, 2, s);
                break;
            case 4:
                b = board.setValueAt(1, 0, s);
                break;
            case 5:
                b = board.setValueAt(1, 1, s);
                break;
            case 6:
                b = board.setValueAt(1, 2, s);
                break;
            case 7:
                b = board.setValueAt(2, 0, s);
                break;
            case 8:
                b = board.setValueAt(2, 1, s);
                break;
            case 9:
                b = board.setValueAt(2, 2, s);
                break;

            default:
                System.out.println("Invalid input, try again");
                b = false;
                break;

        }
        if (b) {
            freeSpaces = removeSpace(freeSpaces, pos);

        }
        return b;
    }

    private int[] removeSpace(final int[] array, int pos) {

        return IntStream.of(array).filter(array_element -> array_element != pos).toArray();
    }

    private void displaySpace() {
        StringBuilder s = new StringBuilder();
        s.append("Free spaces left at: ");
        for (int i = 0; i < freeSpaces.length; i++) {
            s.append(freeSpaces[i]);
            if(i <freeSpaces.length-1) {
                s.append(", ");
            }
        }
        System.out.println(s);
    }


    public Board getBoard() {
        return board;
    }

    private void addMove(int i, int j, int k){
        if(k == 1) {
            opponentMove[i] = j;
        }
        else botMove[i] = j;
    }

    private void getPlayerChoice(int i) {
        boolean b;
        int p=0;
        do {
            System.out.println("Player " + " ( " + player + " ) " + "turn, enter the position");
            displaySpace();
            try {
                p = scanner.nextInt();
                b = setBoard(player, p);

            } catch (InputMismatchException e) {
                System.err.println("Please enter a number between 1 - 9");
                b = false;
                scanner = new Scanner(System.in);
            }
        } while (!b);

            addMove(i, p, 1);
        System.out.println(Arrays.toString(opponentMove));
        board.drawBoard();
    }

    private void getBotChoice(String d, int i) {
        boolean b;
        int p;
        System.out.println("Bot's " + "( " + bot + " )" + " turn");
        do { p = makeDecision(d);
            b = setBoard(bot, p);
        } while (!b);
        addMove(i ,p,2);
        board.drawBoard();
    }

    public void playGame(String d) {
        this.board = new Board();
        boolean b;
        int i=0;
        do {
            if (d.equals("B")||(d.equals("N"))) {
                getPlayerChoice(i);
                if (board.gameOver()) break;
                getBotChoice(d, i);
            }
            else{
                getBotChoice(d,i);
                if (board.gameOver()) break;
                getPlayerChoice(i);
            }
            i++;
        } while (!board.gameOver());
    }

    private int checkCorner() {
        if (board.get(1) == null) {
            return 1;
        } else if (board.get(3) == null) {
            return 3;
        } else if (board.get(7) == null) {
            return 7;
        } else if (board.get(9) == null) {
            return 9;
        }
        return checkCentre();
    }

    private int checkCentre() {
        if (board.get(2) == null) {
            return 2;
        } else if (board.get(4) == null) {
            return 4;
        } else if (board.get(6) == null) {
            return 6;
        } else if (board.get(8) == null) {
            return 8;
        } else if (board.get(5) == null) {
            return 5;
        } else return 0;
    }

    private int normal() {
        if (board.get(4) == null) {
            return 4;
        } else if (board.get(2) == null) {
            return 2;
        } else if (board.get(1) == null) {
            return 1;
        } else if (board.get(3) == null) {
            return 3;
        } else if (board.get(7) == null) {
            return 7;
        } else if (board.get(5) == null) {
            return 5;
        } else if (board.get(6) == null) {
            return 6;
        } else if (board.get(8) == null) {
            return 8;
        } else if (board.get(9) == null) {
            return 9;
        } else return 0;
    }

    private int secondMove(){
        if (opponentMove[0] != 5 ){
            if(opponentMove[0]!= 3){
                return 3;
            }
            else return 1;
        }
        else{
            return 3;
        }
    }

    private int thirdMove() {
        if (opponentMove[0] == 5) {  //this is for opponent placing at centre
            if (opponentMove[1] == 9) {
                return 1;
            } else if (opponentMove[1] == 1) return 9;

            else return block();
        } else if (botMove[1] == 3) {
            if (board.get(5) == null) {
                return 5;
            }
                else if(opponentMove[1]==5){
                    return 9;
                }
                else return 1;
            }
        else if (botMove[1] == 1 && opponentMove[1]!= 4) return 4;
        else return 9;
        }


    private int fourthMove(){
        if (opponentMove[0]==5){
           if(botMove[2] ==1 ) {
               if (opponentMove[2] ==2)
                return 4;
               else return 2;
           }
           else if (botMove[2] ==9){
               if (opponentMove[2] ==6)
                   return 8;
               else return 6;
           }
           else return freeSpaces[1];
        }
        else {
            if(botMove[2]==9 && botMove[1] == 3){
                if(opponentMove[2]==6) return 8;
                else return 6;
            }
            else if (botMove[2]==9 && botMove[1] == 1){
                if(opponentMove[2]==5) return 8;
                else return 5;
            }
            else  if(opponentMove[2]==2) return 4;
            else return 2;
        }
    }


private int hard() {
    if (opponentMove[0] == 0) {
        return 7;
    } else if (opponentMove[1] == 0) {
        return secondMove();
    } else if (opponentMove[2] == 0) {
        return thirdMove();
    }
    else if (opponentMove[3] == 0){
        return fourthMove();
    }
    else {return freeSpaces[0];}
}

private int block(){
if (opponentMove[1]== 2) return 8;
else if (opponentMove[1]== 4) return 6;
else if (opponentMove[1]== 6) return 4;
else if (opponentMove[1] == 8) return 2;
else return 0;
}

    private int makeDecision(String d) {
        if (d.equals("N")) {
            return normal();
        }
        else if(d.equals("H")){
           return hard();
        }
        else return checkCorner();
    }

    /**   public int analyse(int i, OorX a){
     if(i!=-1){
     checkCorners(a);

     if(checkcentre()){

     }
     if(checkmiddle()){

     }

     }
     else return 5;
     }
     **/
}
