package challenges.aoc2022.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

    private static final String fileName = "src/challenges/aoc2022/day7/day7-data.txt";

    private static HashMap<String, List<File>> fileSystem = new HashMap<>(); // String = directory, Integer filesize
    private static HashMap<String, Integer> lul = new HashMap<>();

    private static HashMap<String, Integer> lol = new HashMap<>();
    private static String currentDir = "";

    public static void day7() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        int count = 0;
        String st;
        while ((st = br.readLine()) != null) {
            Types type = parseText(st);
            if (type != null) {
                if (type.equals(Types.CD)) {
                    cd(st);
                } else if (type.equals(Types.LS)) {
                    List<String> nextLines = getNextLines(count);
                    ls(nextLines);
                }
            }
            count++;
        }
        getSize();
        int sum = 0;
        for (Map.Entry<String, Integer> entry : lol.entrySet()) {

            if (entry.getValue() < 100000) {
                sum += entry.getValue();
            }
            System.out.println(sum);
        }
    }

    private static void getSize() {
        for (Map.Entry<String, List<File>> entry : fileSystem.entrySet()) {
            int sum = 0;
            for (File file : entry.getValue()) {
                sum += file.getSize();
            }
            if (!lul.containsKey(entry.getKey())) {
                lul.put(entry.getKey(), sum);
            }
        }

        for (Map.Entry<String, List<File>> entry : fileSystem.entrySet()) {
            int sum = 0;
            for (Map.Entry<String, Integer> lul : lul.entrySet()) {
                if (lul.getKey().contains(entry.getKey())) {
                    sum += lul.getValue();
                }
                lol.put(entry.getKey(), sum);
            }
        }
    }

    private static void cd(String line) {
        String path = line.replace("$ cd ", "");
        if (path.equals("..")) {
            String s = currentDir;
            String[] split = s.split("/");
            int length = split[split.length - 1].length();
            currentDir = s.substring(0, s.length() - length - 1);
        } else if (!path.equals("/")) {
            currentDir = currentDir + "/" + path;
        } else {
            currentDir = "/";
        }
    }

    private static void ls(List<String> lines) {
        List<File> files = new ArrayList<>();
        for (String line : lines) {
            if (!line.contains("dir")) {
                String[] s = line.split(" ");
                File file = new File();
                file.setName(s[1]);
                file.setSize(Integer.parseInt(s[0]));
                files.add(file);
            }
        }
        if (fileSystem.containsKey(currentDir)) {
            List<File> files1 = fileSystem.get(currentDir);
            files1.addAll(files);
            fileSystem.put(currentDir, files1);
        } else {
            fileSystem.put(currentDir, files);
        }
    }

    private static Types parseText(String line) {
        if (line.contains("$")) {
            if (line.contains("cd")) {
                return Types.CD;
            } else if (line.contains("ls")) {
                return Types.LS;
            }
        }
        if (line.contains("dir")) {
            return Types.DIR;
        }
        return null;
    }

    private static List<String> getNextLines(int start) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int count = 0;
        List<String> lines = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            if (start < count) {
                if (!st.contains("$")) {
                    lines.add(st);
                } else {
                    return lines;
                }
            }
            count++;
        }
        return lines;
    }

}
