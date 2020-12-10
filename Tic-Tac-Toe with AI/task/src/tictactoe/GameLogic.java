package tictactoe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GameLogic {

    private char[][] gameGrid = new char[][] {
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
        if (gameGrid[y][x] != ' ') {
            return false;
        }
        gameGrid[y][x] = turn;

        blockSpots = evaluateGrid(turn);
        ++turnCount;
        updateState();

        if (!isFinished) {
            swapTurn();
            winSpots = evaluateGrid(turn);
        }

        return true;
    }

    private void updateState() {
        if (winner != '\0' || turnCount == 9) {
            isFinished = true;
        }
    }

    private boolean hasWinner(char[][] grid, char turn) {
        int rows = 0;
        int cols = 0;
        int diag1 = 0;
        int diag2 = 0;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                rows += grid[i][j] == turn ? 1 : 0;
                cols += grid[j][i] == turn ? 1 : 0;
            }

            rows = rows >= 3 ? 3 : 0;
            cols = cols >= 3 ? 3 : 0;

            int y = Math.abs(2 - i);
            diag1 += grid[i][i] == turn ? 1 : 0;
            diag2 += grid[y][i] == turn ? 1 : 0;
        }
        List<Integer> sums =
                Arrays.asList(rows, cols, diag1, diag2);
        return Collections.max(sums) >= 3;
    }



    private int[][] evaluateGrid(char turn) {
        int rows = 0;
        int cols = 0;
        int diag1 = 0;
        int diag2 = 0;

        List<int[]> blankSpots = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                rows += gameGrid[i][j] == turn ? 1 : 0;
                cols += gameGrid[j][i] == turn ? 1 : 0;
            }

            // Backtrack, find empty cell if sum == 2:
            for (int j = 2; j >= 0; --j) {
                if (rows == 2 && gameGrid[i][j] == ' ') {
                    blankSpots.add(new int[] {j, i});
                }
                if (cols == 2 && gameGrid[j][i] == ' ') {
                    blankSpots.add(new int[] {i, j});
                }
            }

            rows = rows >= 3 ? 3 : 0;
            cols = cols >= 3 ? 3 : 0;

            int y = Math.abs(2 - i);
            diag1 += gameGrid[i][i] == turn ? 1 : 0;
            diag2 += gameGrid[y][i] == turn ? 1 : 0;
        }

        // Backtrack, find empty cell if sum == 2:
        for (int k = 2; k >= 0; --k) {
            int y = Math.abs(2 - k);
            if (diag1 == 2 && gameGrid[k][k] == ' ') {
                blankSpots.add(new int[] {k, k});
            }
            if (diag2 == 2 && gameGrid[y][k] == ' ') {
                blankSpots.add(new int[] {k, y});
            }
        }

        int[][] hotSpots = new int[blankSpots.size()][];
        blankSpots.toArray(hotSpots);

        List<Integer> sums =
                Arrays.asList(rows, cols, diag1, diag2);
        if (Collections.max(sums) >= 3) {
            winner = turn;
        }
        return hotSpots;
    }

    public int[][] getFreeCells() {
        return getFreeCells(gameGrid);
    }

    private char[][] cloneGrid(char[][] grid) {
        char[][] cloned = new char[grid.length][];
        for (int i = 0; i < grid.length; ++i) {
            cloned[i] = new char[grid[0].length];
            System.arraycopy(grid[i], 0, cloned[i], 0, grid[i].length);
        }
        return cloned;
    }

    public int[][] getFreeCells(char[][] grid) {
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

    public int[][] getHardMoves() {
        int[][] freeCells = getFreeCells(gameGrid);
        char[][] gridClone = cloneGrid(gameGrid);

        List<Integer> scores = miniMax(gridClone, turn, 0);
        int max = Collections.max(scores);

        List<int[]> results = new ArrayList<>();
        for (int i = 0; i < scores.size(); ++i) {
            if (scores.get(i).equals(max)) {
                results.add(freeCells[i]);
            }
        }
        int[][] moves = new int[results.size()][];
        return results.toArray(moves);
    }

    private List<Integer> miniMax(char[][] grid, char turn, int level) {
        int[][] freeCells = getFreeCells(grid);
        List<Integer> results = new ArrayList<>(freeCells.length);
        if (freeCells.length == 0) {
            results.add(0);
        }

        for (int[] cell : freeCells) {
            char[][] gridClone = cloneGrid(grid);
            gridClone[cell[1]][cell[0]] = turn;
            if (hasWinner(gridClone, turn)) {
                results.add(this.turn == turn ? 1 : -1);
            } else if (hasWinner(gridClone, turn == 'X' ? 'O' : 'X')) {
                results.add(this.turn == turn ? -1 : 1);
            } else {
                results.addAll(miniMax(gridClone,
                        turn == 'X' ? 'O' : 'X', level + 1));
            }
        }

        if (level == 0) {
            return results;
        }
        if (this.turn == turn) {
            return Collections.singletonList(Collections.max(results));
        } else {
            return Collections.singletonList(Collections.min(results));
        }
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
                sb.append(gameGrid[i][j]).append(" ");
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

    public int[][] getMediumMoves() {
        if (winSpots.length > 0) {
            return winSpots;
        }
        return blockSpots;
    }
}
