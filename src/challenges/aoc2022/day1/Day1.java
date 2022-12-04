package challenges.aoc2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void day1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/challenges/aoc2022/day1/day1-data.txt"));

        int numb = 0;
        List<Integer> lul = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            if (st.length() > 0) {
                numb += Integer.parseInt(st);
            }
            if (st.length() == 0) {
                lul.add(numb);
                numb = 0;
            }
        }
        lul.sort(Collections.reverseOrder());
        int sum = lul.get(0) + lul.get(1) + lul.get(2);
        System.out.println("Day1: " + lul.get(0) + " " + lul.get(1) + " " + lul.get(2) + " = " + sum);
    }

}
