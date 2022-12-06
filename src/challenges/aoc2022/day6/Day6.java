package challenges.aoc2022.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static void day6() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/challenges/aoc2022/day6/day6-data.txt"));

        String st;
        while ((st = br.readLine()) != null) {
            getMarker(st, 4);
            getMarker(st, 14);
        }
    }

    private static void getMarker(String line, int marker) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < line.toCharArray().length; i++) {
            if (i < line.length() - marker) {
                list.add(line.substring(i, i + marker));
            }
        }
        for (String entry : list) {
            if (entry.length() == entry.chars().distinct().count()) {
                System.out.println(line.indexOf(entry) + marker);
                return;
            }
        }
    }

}
