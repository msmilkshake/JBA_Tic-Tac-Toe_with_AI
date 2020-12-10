package tictactoe;


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
    private boolean isDraw = false;

    private void swapTurn() {
        turn = turn == 'X' ? 'O' : 'X';
    }

    public boolean play(int x, int y) {
        if (grid[y][x] != ' ') {
            return false;
        }
        grid[y][x] = turn;
        ++turnCount;

        checkWinner();
        updateState();

        return true;
    }

    public boolean play(int x, int y, boolean humanCoords) {
        --x;
        --y;
        y = Math.abs(2 - y);
        return play(x, y);
    }

    private void updateState() {
        if (winner != '\0' || turnCount == 9) {
            isFinished = true;
            isDraw = winner == '\0';
        }
    }

    private void checkWinner() {
        int rows = 0;
        int cols = 0;
        int diag1 = 0;
        int diag2 = 0;

        for (int i = 0; i < 3; ++i) {
            int y = Math.abs(2 - i);

            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == turn) {
                    ++rows;
                }
                if (grid[j][i] == turn) {
                    ++cols;
                }
            }

            rows = rows >= 3 ? 3 : 0;
            cols = cols >= 3 ? 3 : 0;

            if (grid[i][i] == turn) {
                ++diag1;
            }
            if (grid[y][i] == turn) {
                ++diag2;
            }
        }

        List<Integer> sums =
                Arrays.asList(rows, cols, diag1, diag2);
        if (Collections.max(sums) >= 3) {
            winner = turn;
        } else {
            swapTurn();
        }
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

    public boolean isDraw() {
        return isDraw;
    }

    public char getWinner() {
        return winner;
    }

    public char getTurn() {
        return turn;
    }
}
