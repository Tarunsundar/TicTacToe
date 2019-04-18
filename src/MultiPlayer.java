
import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiPlayer {

    private Scanner scanner;


    public MultiPlayer() {
        this.scanner = new Scanner(System.in);
    }

    public void playGame(String p1, String p2, Board board) {
        boolean b;
        do {
            do {
                System.out.println("Player 1" + " ( " + p1 + " ) " + "turn, enter the position");
                try {
                    b = setBoard(p1, board);
                } catch (InputMismatchException e) {
                    System.err.println("Please enter a number between 1 - 9");
                    b = false;
                    scanner = new Scanner(System.in);
                }
            } while (!b);
            board.drawBoard();
            if (board.gameOver()) break;
            System.out.println("Player 2" + " ( " + p2 + " ) " + "turn, enter the position");
            do {
                try {
                    setBoard(p2, board);
                } catch (InputMismatchException e) {
                    System.err.println("Please enter a valid number");
                    b = false;
                    scanner = new Scanner(System.in);
                }
            } while (!b);
            board.drawBoard();
        } while (!board.gameOver());

    }

    public boolean setBoard(String s, Board board) throws InputMismatchException {
        boolean b, tryAgain;
        int choice;

        choice = scanner.nextInt();
        tryAgain = false;
        switch (choice) {
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
                b = false;
                System.out.println("Invalid input, try again");
                tryAgain = true;
                break;
        }
        return b;
    }
}