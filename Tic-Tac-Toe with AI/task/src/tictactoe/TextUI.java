package tictactoe;


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextUI {
    private final Scanner scn;
    private GameLogic game;
    private final Random rng = new Random();
    private String action;
    private String player1;
    private String player2;

    public TextUI(Scanner scn) {
        this.scn = scn;
    }

    public void start() {
        while (!"exit".equals(action)) {
            clearCommands();
            enterCommand();

            switch (action) {
                case "start":
                    gameLoop();
                    break;
                case "exit":
                    break;
            }
        }
    }

    private void gameLoop() {
        game = new GameLogic();

        System.out.println(game);

        while (!game.isFinished()) {
            switch (game.getTurn()) {
                case 'X':
                    play(player1);
                    break;
                case 'O':
                    play(player2);
                    break;
            }
            System.out.println(game);
        }

        printWinner();
    }

    private void clearCommands() {
        action = "";
        player1 = "";
        player2 = "";
    }

    private void enterCommand() {
        boolean correctCommands = false;
        String cmdRgx = "(\\w+) ?(?:(\\w+) (\\w+))?";
        String actionRgx = "start|exit";
        String playerRgx = "user|easy";

        while (!correctCommands) {
            System.out.println("Input command:");
            Matcher m = Pattern.compile(cmdRgx).matcher(scn.nextLine());
            boolean matches = m.matches();

            if (matches) {
                action = m.group(1);
                correctCommands = action.matches(actionRgx);
            }

            if ("start".equals(action)) {
                if (m.groupCount() == 3) {
                    player1 = m.group(2);
                    player2 = m.group(3);
                    correctCommands = player1.matches(playerRgx);
                    correctCommands &= player2.matches(playerRgx);
                } else {
                    correctCommands = false;
                }
            }

            if (!correctCommands) {
                System.out.println("Bad parameters!");
            }
        }
    }

    private void play(String player) {
        switch (player) {
            case "user":
                humanPlay();
                break;
            case "easy":
                aiEasyPlay();
                break;
        }
    }

    private void humanPlay() {
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

    private void aiEasyPlay() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
