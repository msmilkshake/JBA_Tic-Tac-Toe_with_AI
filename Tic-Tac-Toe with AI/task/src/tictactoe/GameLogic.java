package tictactoe;


public class GameLogic {
    private char[][] grid;
    private char turn = 'X';
    private char winner = '\0';
    private int turnCount;

    public GameLogic() {
        grid = new char[][] {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
        turnCount = 0;
    }

    public void setGridState(String state) {
        state = state.toUpperCase().replace('_', ' ');
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i][j] = state.charAt(i * 3 + j);
            }
        }
        checkTurn();
    }

    private void checkTurn() {
        int countX = 0;
        int countO = 0;
        for (char[] ch : grid) {
            for (char c : ch) {
                countX += c == 'X' ? 1 : 0;
                countO += c == 'O' ? 1 : 0;
            }
        }
        turnCount += countX + countO;
        turn = countX == countO ? 'X' : 'O';
    }

    private void swapTurn() {
        turn = turn == 'X' ? 'O' : 'X';
    }

    public boolean play(int x, int y) {
        y = Math.abs(2 - y);
        if (grid[y][x] != ' ') {
            return false;
        }
        grid[y][x] = turn;
        ++turnCount;
        return true;
    }

    public char getTurn() {
        return turn;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean checkWinner() {
        int count = 0;

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (grid[y][x] == turn) {
                    ++count;
                }
            }
            if (count == 3) {
                winner = turn;
                break;
            }
            count = 0;
        }

        count = 0;
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                if (grid[y][x] == turn) {
                    ++count;
                }
            }
            if (count == 3) {
                winner = turn;
                break;
            }
            count = 0;
        }

        count = 0;
        int count2 = 0;
        for (int i = 0; i < 3; ++i) {
            int y = Math.abs(2 - i);
            if (grid[y][i] == turn) {
                ++count;
            }
            if (grid[i][i] == turn) {
                ++count2;
            }
        }
        if (count == 3 || count2 == 3) {
            winner = turn;
        }

        boolean hasWon = winner != '\0';
        if (!hasWon) {
            swapTurn();
        }
        return hasWon;
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
}
