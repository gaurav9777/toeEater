//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Game!!!");
        System.out.println("You have 3 change to start the game.");
        System.out.println("To start the game type (s) and to quit type (q) : ");
        int openCloseGame = 1;
        String winner = gameLoop(openCloseGame);
        if (winner.equals("Not started")) {
            System.out.println("Game not started!!!");
        } else if (winner.equals("Both Lost")) {
            System.out.println("Both Player Lost No One wins, to restart again type s or to quite type q");
            gameLoop(1);
        } else {
            System.out.println("Player " + winner + " wins the game!");
        }
        exit(1);
    }

    private static String gameLoop(int openCloseGame) {
        Scanner sc = new Scanner(System.in);
        while (openCloseGame <= 3) {
            String gameState = sc.next();
            switch (gameState) {
                case "s":
                    System.out.println("Provide the dimension for which you want to play : ");
                    int dimension = sc.nextInt();
                    String winner = startGame(dimension);
                    if (winner.equals("Both Lost")) {
                        System.out.println("Both Player Lost No One wins, to restart again type s or to quite type q :");
                        gameLoop(1);
                    } else {
                        return winner;
                    }
                case "q":
                    endGame();
                default:
                    System.out.println("Please type 's' to start and 'q' to quit");
                    System.out.println("remaining attempt to start game " + (3 - openCloseGame));
                    openCloseGame += 1;
            }
        }
        return "Not started";
    }

    private static void endGame() {
        System.out.println("Thanks for playing!");
        exit(1);
    }

    private static String startGame(int dimension) {
        Scanner sc = new Scanner(System.in);
        int totalState = dimension * dimension;
        String[][] gamePosition = new String[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            Arrays.fill(gamePosition[row], "-");
        }
        System.out.println("Game floor looks like : ");
        boolean playersTurn = true;

        while (totalState > 0) {

            printCurrentState(gamePosition);

            String winner = checkForWinner(gamePosition);
            if (Objects.nonNull(winner)) return winner;

            System.out.println(playersTurn ? "Player A to play " : "Player B to play " + ": enter position : ");

            int coOrdinatePosition = sc.nextInt();
            int status = placeAtPosition(gamePosition, coOrdinatePosition - 1, dimension, playersTurn);

            if (status == -1) {
                System.out.println("already that position is filled please check the game status\n and place provide the untouched position");
            } else if (status == 0) {
                System.out.println("position is not correct choose between 1 To " + totalState);
            } else {
                playersTurn = !playersTurn;
                totalState -= 1;
            }
        }

        printCurrentState(gamePosition);

        return "Both Lost";
    }

    private static int placeAtPosition(String[][] gamePosition, int coOrdinatePosition, int dimension, boolean playerAToPlay) {
        int gameFloorLen = gamePosition.length;
        int gameFloorWid = gamePosition[0].length;
        int rowPosition = (coOrdinatePosition / dimension);
        int colPosition = (coOrdinatePosition % dimension);
        if (rowPosition >= gameFloorLen || colPosition >= gameFloorWid) {
            return 0;
        }
        if (rowPosition < 0 || colPosition < 0) {
            return 0;
        }
        if (!gamePosition[rowPosition][colPosition].equals("-")) {
            return -1;
        }
        gamePosition[rowPosition][colPosition] = playerAToPlay ? "X" : "O";
        return 1;
    }

    private static void printCurrentState(String[][] gamePosition) {
        for (int row = 0; row < gamePosition.length; row++) {
            for (int col = 0; col < gamePosition[0].length; col++) {
                System.out.print(gamePosition[row][col] + "  ");
            }
            System.out.println();
        }
    }

    private static String checkForWinner(String[][] gamePosition) {


        //check for row wise winner
        for (String[] strings : gamePosition) {
            int xCount = 0;
            int oCount = 0;
            for (int col = 0; col < gamePosition[0].length; col++) {
                if (strings[col].equals("X")) {
                    xCount++;
                } else if (strings[col].equals("O")) {
                    oCount++;
                }
            }
            if (xCount == gamePosition.length) {
                return "A";
            } else if (oCount == gamePosition.length) {
                return "B";
            }
        }

        //check for col wise winner
        for (int col = 0; col < gamePosition[0].length; col++) {
            int xCount = 0;
            int oCount = 0;
            for (int row = 0; row < gamePosition.length; row++) {
                if (gamePosition[row][col].equals("X")) {
                    xCount++;
                } else if (gamePosition[row][col].equals("O")) {
                    oCount++;
                }
            }
            if (xCount == gamePosition.length) {
                return "A";
            } else if (oCount == gamePosition.length) {
                return "B";
            }
        }

        int xCount = 0;
        int oCount = 0;

        //check for positive diagonal winner

        for (int col = 0; col < gamePosition[0].length; col++) {
            for (int row = 0; row < gamePosition.length; row++) {
                if (row == col) {
                    if (gamePosition[col][row].equals("X")) {
                        xCount++;
                    } else if (gamePosition[col][row].equals("O")) {
                        oCount++;
                    }
                }
            }
        }

        if (xCount == gamePosition.length) {
            return "A";
        } else if (oCount == gamePosition.length) {
            return "B";
        }

        // check for reverse diagonal winner
        xCount = 0;
        oCount = 0;
        for (int col = 0; col < gamePosition[0].length; col++) {
            for (int row = 0; row < gamePosition.length; row++) {
                if ((row + col + 1) == gamePosition.length) {
                    if (gamePosition[col][row].equals("X")) {
                        xCount++;
                    } else if (gamePosition[col][row].equals("O")) {
                        oCount++;
                    }
                }
            }
        }

        if (xCount == gamePosition.length) {
            return "A";
        } else if (oCount == gamePosition.length) {
            return "B";
        }

        return null;
    }

}
