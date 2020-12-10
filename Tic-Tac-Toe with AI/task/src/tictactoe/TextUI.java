package tictactoe;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextUI {
    private Scanner scn;
    private GameLogic game;

    public TextUI(Scanner scn) {
        this.scn = scn;
        game = new GameLogic();
    }

    public void start() {
        game.setGridState(getInput());
        System.out.println(game);
        enterCoordinates();
        System.out.println(game);
        printWinner();
    }

    private String getInput() {
        while (true) {
            System.out.println("Enter cells:");
            String input = scn.nextLine();
            if (input.matches("[xoXO_]{9}")) {
                return input;
            }
            System.out.println("Wrong input. Try again.");
        }
    }

    private void enterCoordinates() {
        String rgx = "(\\d+) (\\d+)";
        int x = -1;
        int y = -1;

        while (true) {
            System.out.println("Enter the coordinates:");
            Matcher m = Pattern.compile(rgx).matcher(scn.nextLine());

            if (m.matches()) {
                x = Integer.parseInt(m.group(1)) - 1;
                y = Integer.parseInt(m.group(2)) - 1;
                if (x > 2 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (game.play(x, y)) {
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private void printWinner() {
        if (game.checkWinner()) {
            System.out.println(game.getTurn() + " wins");
        } else if (game.getTurnCount() == 9) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        }
    }

}
