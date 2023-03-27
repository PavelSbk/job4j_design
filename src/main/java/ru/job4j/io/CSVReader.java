package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        validator(argsName);
        String ln = System.lineSeparator();
        List<List<String>> table = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        File path = new File(argsName.get("path"));
        File out = new File(argsName.get("out"));
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNext()) {
                table.add(Arrays.stream(scanner.nextLine().split(delimiter)).toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, List<String>> tempMap = listToMap(table);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            List<String> ls = new ArrayList<>();
            bw.write(String.join(delimiter, filter) + ln);
            for (int i = 0; i < table.size() - 1; i++) {
                for (String f : filter) {
                    ls.add(tempMap.get(f).get(i));
                }
                bw.write(String.join(delimiter, ls) + ln);
                ls.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validator(ArgsName argsName) {
        if (!new File(argsName.get("path")).exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Filter does not exist.");
        }
        if (!(";".equals(argsName.get("delimiter")) || ",".equals(argsName.get("delimiter")))) {
            throw new IllegalArgumentException("Wrong delimiter character.");
        }
        if (!new File(argsName.get("out")).getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Invalid output file type.");
        }
    }

    private static Map<String, List<String>> listToMap(List<List<String>> temp) {
        Map<String, List<String>> rst = new LinkedHashMap<>();
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < temp.get(i).size(); j++) {
                if (i == 0) {
                    rst.put(temp.get(i).get(j), new ArrayList<>());
                } else {
                    rst.get(temp.get(0).get(j)).add(temp.get(i).get(j));
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}