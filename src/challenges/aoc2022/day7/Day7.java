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
    private static final HashMap<String, List<File>> fileSystem = new HashMap<>();
    private static final HashMap<String, Integer> pathWithTotalSize = new HashMap<>();
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

        task1();
        task2();
    }

    private static void task1() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : pathWithTotalSize.entrySet()) {
            if (entry.getValue() < 100000) {
                sum += entry.getValue();
            }
        }
        System.out.println(sum);
    }

    private static void task2() {
        int totalSize = 70000000;
        int totalUsedSpace = pathWithTotalSize.get("/");
        int availableSpace = totalSize - totalUsedSpace;
        String curDir = "";
        int curSize = 0;
        for (Map.Entry<String, Integer> entry : pathWithTotalSize.entrySet()) {
            int i = availableSpace + entry.getValue();
            if (i > 30000000) {
                if (curDir.length() > 0) {
                    if ((entry.getValue()) < curSize) {
                        curSize = entry.getValue();
                        curDir = entry.getKey();
                    }
                } else {
                    curSize = entry.getValue();
                    curDir = entry.getKey();
                }
            }
        }
        System.out.println(curSize);
    }

    private static void getSize() {
        HashMap<String, Integer> tmpList = new HashMap<>();
        for (Map.Entry<String, List<File>> entry : fileSystem.entrySet()) {
            int sum = 0;
            for (File file : entry.getValue()) {
                sum += file.getSize();
            }
            if (!tmpList.containsKey(entry.getKey())) {
                tmpList.put(entry.getKey(), sum);
            }
        }

        for (Map.Entry<String, List<File>> entry : fileSystem.entrySet()) {
            int sum = 0;
            for (Map.Entry<String, Integer> ent : tmpList.entrySet()) {
                if (ent.getKey().contains(entry.getKey())) {
                    sum += ent.getValue();
                }
                pathWithTotalSize.put(entry.getKey(), sum);
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
