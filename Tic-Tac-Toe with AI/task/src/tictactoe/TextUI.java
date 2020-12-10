package tictactoe;


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextUI {
    private final Scanner scn;
    private final GameLogic game = new GameLogic();
    private final Random rng = new Random();

    public TextUI(Scanner scn) {
        this.scn = scn;
    }

    public void start() {
        while (!game.isFinished()) {
            System.out.println(game);

            switch (game.getTurn()) {
                case 'X':
                    playerPlay();
                    break;
                case 'O':
                    aiPlay();
                    break;
            }
        }

        System.out.println(game);
        printWinner();
    }

    private void playerPlay() {
        String rgx = "(\\d+) (\\d+)";

        boolean isValidPlay = false;
        while (!isValidPlay) {
            System.out.println("Enter the coordinates:");
            Matcher m = Pattern.compile(rgx).matcher(scn.nextLine());

            if (!m.matches()) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));

            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            isValidPlay = game.play(x, y, true);
            if (!isValidPlay) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private void aiPlay() {
        System.out.println("Making move level \"easy\"");
        int[][] freeCells = game.getFreeCells();
        int cell = rng.nextInt(freeCells.length);
        game.play(freeCells[cell][0], freeCells[cell][1]);
    }

    private void printWinner() {
        if (game.isDraw()) {
            System.out.println("Draw");
        } else {
            System.out.println(game.getWinner() + " wins");
        }
    }
}
