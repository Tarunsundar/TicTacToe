import java.util.Scanner;

public class Application {
    private static Scanner scanner;
    private Board board;
    private String player1;
    private String player2;
    private int xWins;
    private int oWins;
    private MultiPlayer m;

    private Application() {
        scanner = new Scanner(System.in);
        player1 = "o";
        player2 = "x";
        xWins = 0;
        oWins = 0;
        this.m = new MultiPlayer();
    }


    private static boolean getChoice() {
        boolean tryAgain;
        boolean output;
        scanner = new Scanner(System.in);
        do {
            tryAgain = false;
            String ans = scanner.nextLine().toUpperCase();
            if (ans.equals("Y")) {
                output = true;
            } else if (ans.equals("N")) {
                output = false;
            } else {
                System.err.println("Wrong input, try again");
                tryAgain = true;
                output = false;
            }
        } while (tryAgain);
        return output;
    }

    private void setPlayer() {
        String temp;
        if (board.getWinner().equals("o")) {
            player1 = "x";
            player2 = "o";
        } else if (board.getWinner().equals("x")) {
            player1 = "o";
            player2 = "x";
        } else {
            temp = player1;
            player1 = player2;
            player2 = temp;
        }
    }

    private void printNum() {
        System.out.println();
        System.out.println("Position for places are as below ");
        System.out.println("1" + " " + " | " + "2" + " " + " | " + "3");
        System.out.println("¯¯¯|¯¯¯¯|¯¯¯");
        System.out.println("4" + " " + " | " + "5" + " " + " | " + "6");
        System.out.println("¯¯¯|¯¯¯¯|¯¯¯");
        System.out.println("7" + " " + " | " + "8" + " " + " | " + "9");
        System.out.println();
    }

    private void multiPlayer() {
        boolean r;
        printNum();
        do {
            this.m.playGame(this.player1, this.player2, this.board = new Board());
            this.xWins += this.board.getXWins();
            this.oWins += this.board.getOWins();
            System.out.println(" O wins: " + this.oWins);
            System.out.println(" X wins: " + this.xWins);
            this.setPlayer();
            System.out.println("Do you want to play again(Y/N)");
            r = getChoice();

        } while (r);
    }

    private boolean check(String x) {
        if (x.equals("B") || x.equals("E") || x.equals("N") || x.equals("H")) {
            return true;
        } else
            System.out.println("Select valid option");
        return false;

    }

    private String getDifficulty() {
        String x;
        System.out.println("Select Difficulty level :- ");
        //  System.out.println("B - Beginner");
        System.out.println("E - Easy ");
        System.out.println("N - Normal ");
         System.out.println("H - Hard ");
        do {
            x = scanner.next().toUpperCase();
        } while (!check(x));
        printNum();
        return x;
    }

    private void singlePlayer() {
        boolean tryAgain, r;
        do {
            tryAgain = false;
            System.out.println("Do you want X or O?");
            String c = scanner.nextLine().toUpperCase();
            if (c.equals("X")) {
                player1 = "X";
                player2 = "O";
            } else if (c.equals("O")) {
                player1 = "O";
                player2 = "X";
            } else {
                System.out.println("Invalid choice, please enter X or O");
                tryAgain = true;
            }
            System.out.println();
        } while (tryAgain);
        do {
            SinglePlayer s = new SinglePlayer(player1, player2);
            s.playGame(getDifficulty());
            this.xWins += s.getBoard().getXWins();
            this.oWins += s.getBoard().getOWins();
            if(s.getPlayer().equals("O")) {
                System.out.println(" player (O) wins: " + this.oWins);
                System.out.println(" Bot (X) wins: " + this.xWins);
            }
            else if(s.getPlayer().equals("X")) {
                System.out.println(" player (X) wins: " + this.xWins);
                System.out.println(" Bot (o) wins: " + this.oWins);
            }
            System.out.println("Do you want to play again(Y/N)");
            r = getChoice();
        } while (r);
    }

    private void startGame() {
        System.out.println("Do you want to play single player(y - singleplayer ,N- multiplayer)");
        boolean r = getChoice();
        if (r) {
            singlePlayer();
        } else multiPlayer();
        System.out.println("Thanks for playing!!");
    }

    public static void main(String[] args) {
        Application ap = new Application();
        ap.startGame();
    }
}
