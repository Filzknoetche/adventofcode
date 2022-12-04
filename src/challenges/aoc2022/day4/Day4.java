package challenges.aoc2022.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {

    public static void day4() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/challenges/aoc2022/day4/day4-data.txt"));

        int count = 0;
        int count2 = 0;

        String st;
        while ((st = br.readLine()) != null) {
            String[] split = st.split(",");
            String[] e1 = split[0].split("-");
            String[] e2 = split[1].split("-");

            List<Integer> e1Range = getNumbersInBetween(Integer.parseInt(e1[0]), Integer.parseInt(e1[1]));
            List<Integer> e2Range = getNumbersInBetween(Integer.parseInt(e2[0]), Integer.parseInt(e2[1]));

            if (isInRange(e1Range, e2Range)) {
                count++;
            }

            if (part2(e1Range, e2Range)) {
                count2++;
            }
        }

        System.out.println(count);
        System.out.println(count2);
    }

    private static boolean isInRange(List<Integer> e1, List<Integer> e2) {
        int e1Start = e1.get(0);
        int e2Start = e2.get(0);
        int d1End = e1.get(e1.size() - 1);
        int e2End = e2.get(e2.size() - 1);

        if (e1Start <= e2Start && d1End >= e2End) {
            return true;
        }

        return e2Start <= e1Start && e2End >= d1End;
    }

    private static boolean part2(List<Integer> e1, List<Integer> e2) {
        for (Integer l1 : e1) {
            for (Integer l2 : e2) {
                if (l1.equals(l2)) {
                    return true;
                }
            }
        }
        for (Integer l1 : e2) {
            for (Integer l2 : e1) {
                if (l1.equals(l2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Integer> getNumbersInBetween(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

}
