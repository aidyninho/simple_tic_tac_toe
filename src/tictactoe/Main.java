package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static char[][] createGrid() {
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
        return grid;
    }

    public static void showGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print(grid[i][j] + " ");
                if (j == 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public static char[][] gridFromUser(String input) {
        char[][] grid = new char[3][3];
        for (int i = 0; i < input.length(); i++) {
            if (i < 3) {
                grid[0][i] = input.charAt(i);
            } else if (i < 6) {
                grid[1][i - 3] = input.charAt(i);
            } else {
                grid[2][i - 6] = input.charAt(i);
            }
        }
        return grid;
    }

    public static String checkGrid(char[][] grid) {
        int xCount = 0;
        int oCount = 0;
        int spaceCount = 0;
        int xWins = 0;
        int oWins = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') {
                    xCount++;
                } else if (grid[i][j] == 'O') {
                    oCount++;
                } else {
                    spaceCount++;
                }
            }
            if (grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X') {
                xWins++;
            } else if (grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O') {
                oWins++;
            } else if (grid[0][i] == 'X' && grid[1][i] == 'X' && grid[2][i] == 'X') {
                xWins++;
            } else if (grid[0][i] == 'O' && grid[1][i] == 'O' && grid[2][i] == 'O') {
                oWins++;
            }
        }

        if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X') {
            xWins++;
        } else if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {
            oWins++;
        }

        if (Math.abs(xCount - oCount) < 2 && xWins + oWins < 2) {
            if (xWins == 0 && oWins == 0 && spaceCount > 0) {
                return "Game not finished";
            } else if (xWins == 0 && oWins == 0 && spaceCount == 0) {
                return "Draw";
            } else if (xWins > oWins) {
                return "X wins";
            } else if (oWins > xWins) {
                return "O wins";
            }
        }
        return "Impossible";
    }

    public static char[][] userMove(char[][] grid, char playerSign) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String row = scanner.next();
        try {
            int rowInt = Integer.parseInt(String.valueOf(row)) - 1;
            String col = scanner.next();
            int colInt = Integer.parseInt(String.valueOf(col)) - 1;
            if (rowInt > 2 || colInt > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                userMove(grid, playerSign);
            } else if (grid[rowInt][colInt] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                userMove(grid, playerSign);
            } else {
                grid[rowInt][colInt] = playerSign;
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            userMove(grid, playerSign);
        }
        return grid;
    }

    public static void twoPlayersMode() {
        char lastPlayer = 'X';
        char[][] grid = createGrid();
        showGrid(grid);
        while (true) {
            grid = userMove(grid, lastPlayer);
            showGrid(grid);
            String check = checkGrid(grid);
            if (check.equals("Draw") || check.equals("X wins") || check.equals("O wins")) {
                System.out.println(check);
                break;
            }
            if (lastPlayer == 'X') {
                lastPlayer = 'O';
            } else if (lastPlayer == 'O') {
                lastPlayer = 'X';
            }
        }
    }

    public static void main(String[] args) {
        twoPlayersMode();
    }
}
