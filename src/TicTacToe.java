import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        // Pseudocode:
        // DO (play again loop)
        //   clear board, player = X, moveCount = 0
        //   DO (move loop)
        //     show board
        //     DO get row/col (1-3), convert to 0-2, until move is valid
        //     record move, increment moveCount
        //     if moveCount >= 5 check win; if moveCount >= 7 check tie
        //     if win or tie: announce, done = true
        //     else toggle player
        //   WHILE not done
        //   ask to play again
        // WHILE play again

        Scanner in = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean done = false;

            do {
                display();
                int row;
                int col;

                do {
                    row = SafeInput.getRangedInt(in, "Player " + player + " - enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(in, "Player " + player + " - enter col", 1, COLS) - 1;
                    if (!isValidMove(row, col)) {
                        System.out.println("That space is already taken. Try again.");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCount++;

                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    done = true;
                } else if (moveCount >= 7 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    done = true;
                } else {
                    player = player.equals("X") ? "O" : "X";
                }
            } while (!done);

            playAgain = SafeInput.getYNConfirm(in, "Play again?");
        } while (playAgain);

        System.out.println("Thanks for playing!");
    }

    // sets every board element to a space
    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    // shows the board
    private static void display() {
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            System.out.print(" ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c]);
                if (c < COLS - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (r < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    // true if the cell is empty
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        return board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player);
    }

    // tie = every one of the 8 win lines contains both an X and an O
    private static boolean isTie() {
        for (int r = 0; r < ROWS; r++) {
            if (!isBlocked(board[r][0], board[r][1], board[r][2])) {
                return false;
            }
        }
        for (int c = 0; c < COLS; c++) {
            if (!isBlocked(board[0][c], board[1][c], board[2][c])) {
                return false;
            }
        }
        if (!isBlocked(board[0][0], board[1][1], board[2][2])) {
            return false;
        }
        return isBlocked(board[0][2], board[1][1], board[2][0]);
    }

    // true if a line has at least one X and at least one O
    private static boolean isBlocked(String a, String b, String c) {
        boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
        boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");
        return hasX && hasO;
    }
}