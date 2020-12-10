package tictactoe;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GameLogic {

    private final char[][] grid = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };

    private char turn = 'X';
    private char winner = '\0';
    private int turnCount = 0;
    private boolean isFinished = false;

    private int[][] winSpots = new int[0][];
    private int[][] blockSpots = new int[0][];

    private void swapTurn() {
        turn = turn == 'X' ? 'O' : 'X';
    }

    public boolean play(int x, int y) {
        return play(x, y, false);
    }

    public boolean play(int x, int y, boolean humanCoords) {
        if (humanCoords) {
            --x;
            --y;
            y = Math.abs(2 - y);
        }
        if (grid[y][x] != ' ') {
            return false;
        }

        System.err.println("Played at: " + x + ", " + y);
        grid[y][x] = turn;

        blockSpots = evaluateBoard(turn);
        ++turnCount;
        updateState();

        if (!isFinished) {
            swapTurn();
            winSpots = evaluateBoard(turn);
        }

        return true;
    }

    private void updateState() {
        if (winner != '\0' || turnCount == 9) {
            isFinished = true;
        }
    }

    private int[][] evaluateBoard(char player) {
        int[][] hotSpots = new int[0][];
        int rows = 0;
        int cols = 0;
        int diag1 = 0;
        int diag2 = 0;

        List<int[]> blankSpots = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                rows += grid[i][j] == player ? 1 : 0;
                cols += grid[j][i] == player ? 1 : 0;
            }

            // Backtrack, find empty cell if sum == 2:
            for (int j = 2; j >= 0; --j) {
                if (rows == 2 && grid[i][j] == ' ') {
                    blankSpots.add(new int[] {j, i});
                }
                if (cols == 2 && grid[j][i] == ' ') {
                    blankSpots.add(new int[] {i, j});
                }
            }

            rows = rows >= 3 ? 3 : 0;
            cols = cols >= 3 ? 3 : 0;

            int y = Math.abs(2 - i);
            diag1 += grid[i][i] == player ? 1 : 0;
            diag2 += grid[y][i] == player ? 1 : 0;
        }

        // Backtrack, find empty cell if sum == 2:
        for (int k = 2; k >= 0; --k) {
            int y = Math.abs(2 - k);
            if (diag1 == 2 && grid[k][k] == ' ') {
                blankSpots.add(new int[] {k, k});
            }
            if (diag2 == 2 && grid[y][k] == ' ') {
                blankSpots.add(new int[] {k, y});
            }
        }

        hotSpots = new int[blankSpots.size()][];
        blankSpots.toArray(hotSpots);

        List<Integer> sums =
                Arrays.asList(rows, cols, diag1, diag2);
        if (Collections.max(sums) >= 3) {
            winner = player;
        }
        return hotSpots;
    }

    public int[][] getFreeCells() {
        List<int[]> freeCells = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[j][i] == ' ') {
                    freeCells.add(new int[] {i, j});
                }
            }
        }
        int[][] arr = new int[freeCells.size()][];
        return freeCells.toArray(arr);
    }

    public boolean hasWinner() {
        return winner != '\0';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("---------\n| ");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(grid[i][j]).append(" ");
            }
            if (i == 2) {
                sb.append("|\n---------");
            } else {
                sb.append("|\n| ");
            }
        }
        return sb.toString();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public char getWinner() {
        return winner;
    }

    public char getTurn() {
        return turn;
    }

    public int[][] getWinSpots() {
        return  winSpots;
    }

    public int[][] getBlockSpots() {
        return blockSpots;
    }
}
