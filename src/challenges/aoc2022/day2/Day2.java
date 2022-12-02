package challenges.aoc2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    public static void day2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/challenges/aoc2022/day2/day2-data.txt"));

        int playerScore = 0;
        int playerScore2 = 0;
        String st;
        while ((st = br.readLine()) != null) {
            String[] s = st.split(" ");
            String enemy = getPlayerChoice(s[0]);
            String player = getPlayerChoice(s[1]);

            int i = getChoiseScore(player) + turn(enemy, player);
            playerScore += i;

            int c2 = partTwo(enemy, s[1]);
            playerScore2 += c2;
        }
        System.out.println("Day2: " + playerScore + " challange2: " + playerScore2);
    }

    private static String getPlayerChoice(String choise) {
        return switch (choise) {
            case "A", "X" -> "Rock";
            case "B", "Y" -> "Paper";
            case "C", "Z" -> "Scissors";
            default -> "";
        };
    }

    private static int getChoiseScore(String choise) {
        return switch (choise) {
            case "Rock" -> 1;
            case "Paper" -> 2;
            case "Scissors" -> 3;
            default -> 0;
        };
    }

    private static int partTwo(String enemy, String roundEnd) {
        // Y DRAW
        // X LOOSE
        // Z WIN

        if (enemy.equals("Rock") && roundEnd.equals("Y")) {
            return turn(enemy, "Rock") + getChoiseScore("Rock");
        }
        if (enemy.equals("Rock") && roundEnd.equals("X")) {
            return turn("Rock", "Scissors") + getChoiseScore("Scissors");
        }
        if (enemy.equals("Rock") && roundEnd.equals("Z")) {
            return turn("Rock", "Paper") + getChoiseScore("Paper");
        }

        if (enemy.equals("Paper") && roundEnd.equals("Y")) {
            return turn(enemy, "Paper") + getChoiseScore("Paper");
        }
        if (enemy.equals("Paper") && roundEnd.equals("X")) {
            return turn(enemy, "Rock") + getChoiseScore("Rock");
        }
        if (enemy.equals("Paper") && roundEnd.equals("Z")) {
            return turn(enemy, "Scissors") + getChoiseScore("Scissors");
        }

        if (enemy.equals("Scissors") && roundEnd.equals("Y")) {
            return turn(enemy, "Scissors") + getChoiseScore("Scissors");
        }
        if (enemy.equals("Scissors") && roundEnd.equals("X")) {
            return turn(enemy, "Paper") + getChoiseScore("Paper");
        }
        if (enemy.equals("Scissors") && roundEnd.equals("Z")) {
            return turn(enemy, "Rock") + getChoiseScore("Rock");
        }

        return 0;
    }

    private static int turn(String enemy, String player) {
        int win = 6;
        int loose = 0;
        int draw = 3;

        if (enemy.equals(player)) {
            // draw
            return draw;
        }

        // ROCK
        if (player.equals("Rock") && enemy.equals("Scissors")) {
            return win;
        }
        if (player.equals("Rock") && enemy.equals("Paper")) {
            return loose;
        }

        // Paper
        if (player.equals("Paper") && enemy.equals("Rock")) {
            return win;
        }
        if (player.equals("Paper") && enemy.equals("Scissors")) {
            return loose;
        }

        // Scissors
        if (player.equals("Scissors") && enemy.equals("Rock")) {
            return loose;
        }
        if (player.equals("Scissors") && enemy.equals("Paper")) {
            return win;
        }

        return 0;
    }
}
