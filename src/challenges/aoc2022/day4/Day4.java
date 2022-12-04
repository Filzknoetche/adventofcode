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
            String[] elf1 = split[0].split("-");
            String[] elf2 = split[1].split("-");

            List<Integer> elf1Range = getNumbersUsingIntStreamRangeClosed(Integer.parseInt(elf1[0]), Integer.parseInt(elf1[1]));
            List<Integer> elf2Range = getNumbersUsingIntStreamRangeClosed(Integer.parseInt(elf2[0]), Integer.parseInt(elf2[1]));

            if (isInRange(elf1Range, elf2Range)) {
                count++;
            }

            if (part2(elf1Range, elf2Range)) {
                count2++;
            }
        }

        System.out.println(count);
        System.out.println(count2);
    }

    private static boolean isInRange(List<Integer> elf1, List<Integer> elf2) {
        int elf1Start = elf1.get(0);
        int elf2Start = elf2.get(0);
        int elf1End = elf1.get(elf1.size() - 1);
        int elf2End = elf2.get(elf2.size() - 1);

        if (elf1Start <= elf2Start && elf1End >= elf2End) {
            return true;
        }

        return elf2Start <= elf1Start && elf2End >= elf1End;
    }

    private static boolean part2(List<Integer> elf1, List<Integer> elf2) {
        for (Integer l1 : elf1) {
            for (Integer l2 : elf2) {
                if (l1.equals(l2)) {
                    return true;
                }
            }
        }
        for (Integer l1 : elf2) {
            for (Integer l2 : elf1) {
                if (l1.equals(l2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Integer> getNumbersUsingIntStreamRangeClosed(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

}
