package challenges.aoc2022.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {

    private static final int length = 8;

    static HashMap<Integer, List<String>> ship = new HashMap<>();

    public static void day5() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/challenges/aoc2022/day5/day5-data.txt"));

        int lineCount = 0;
        boolean challenge2 = false;

        String st;
        while ((st = br.readLine()) != null) {
            lineCount++;
            if (lineCount <= length) {
                getContainer(st);
            }
            if (lineCount > length + 2) {
                moveContainer(st, challenge2);
            }
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < ship.size(); i++) {
            res.append(ship.get(i + 1).get(0).replace("[", "").replace("]", ""));
        }
        System.out.println(res);
    }

    private static void moveContainer(String st, boolean c2) {
        List<Integer> movement = getMovement(st);
        int move = movement.get(0);
        int from = movement.get(1);
        int to = movement.get(2);

        List<String> fromContainer = ship.get(from);
        List<String> containerToMovie = new ArrayList<>();
        for (int i = 0; i < move; i++) {
            containerToMovie.add(fromContainer.get(0));
            fromContainer.remove(0);
        }
        ship.put(from, fromContainer);

        if (c2) {
            Collections.reverse(containerToMovie);
        }

        List<String> toContainer = ship.get(to);
        for (String s : containerToMovie) {
            toContainer.add(0, s);
        }
        ship.put(to, toContainer);

    }

    private static List<Integer> getMovement(String s) {
        Matcher matcher = Pattern.compile("\\d+").matcher(s);

        List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.valueOf(matcher.group()));
        }
        return numbers;
    }

    private static void getContainer(String s) {
        String line = s;
        Matcher m = Pattern.compile("\\[(.*?)]").matcher(s);
        String lul = "";
        while (m.find()) {
            lul = m.group();
            int pos = (line.indexOf(m.group()) / 4) + 1;
            if (ship.get(pos) == null) {
                List<String> strings = new ArrayList<>();
                ship.put(pos, strings);
            }
            List<String> strings = ship.get(pos);
            strings.add(lul);
            ship.put(pos, strings);
            line = line.replaceFirst(lul, "0");
        }
    }

}
