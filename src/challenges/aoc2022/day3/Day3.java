package challenges.aoc2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void day3() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/challenges/aoc2022/day3/day3-data.txt"));

        int prioScore = 0;
        int groupScore = 0;

        List<String> lul = new ArrayList<>();

        String st;
        while ((st = br.readLine()) != null) {
            final int mid = st.length() / 2;
            String[] parts = {st.substring(0, mid), st.substring(mid)};
            String s = compareLetters(parts[0], parts[1]);
            prioScore += getPrioScore(s);

            lul.add(st);
            if (lul.size() == 3) {
                String s1 = compareLettersC2(lul.get(0), lul.get(1), lul.get(2));
                groupScore += getPrioScore(s1);

                lul.clear();
            }

        }

        System.out.println("Day3: c1: " + prioScore + " c2: " + groupScore);
    }

    public static String compareLetters(String name1, String name2) {
        for (char c1 : name1.toCharArray()) {
            for (char c2 : name2.toCharArray()) {
                if (c1 == c2) {
                    return String.valueOf(c1);
                }
            }
        }
        return "";
    }

    public static String compareLettersC2(String name1, String name2, String name3) {
        for (char c1 : name1.toCharArray()) {
            for (char c2 : name2.toCharArray()) {
                for (char c3 : name3.toCharArray()) {
                    if (c2 == c3 && c1 == c3) {
                        return String.valueOf(c1);
                    }
                }
            }
        }
        return "";
    }

    private static int getPrioScore(String prio) {
        char c = prio.charAt(0);
        if (Character.isUpperCase(c)) {
            return alphabet.toUpperCase().indexOf(prio) + 27;
        }
        return alphabet.indexOf(prio) + 1;
    }

}
