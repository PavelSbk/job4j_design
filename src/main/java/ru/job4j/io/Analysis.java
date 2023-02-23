package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {

    private List<String> separator(List<String> list) {
        List<String> rst = new ArrayList<>();
        boolean flag = false;
        for (String s : list) {
            if (!flag && (s.contains("500 ") || s.contains("400 "))) {
                rst.add(s);
                flag = true;
                continue;
            }
            if (flag && (s.contains("200 ") || s.contains("300 "))) {
                rst.add(s);
                flag = false;
            }
        }
        return rst;
    }

    private List<String> splitter(List<String> list) {
        return separator(list).stream()
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .filter(st -> !st.contains("200")
                        && !st.contains("300")
                        && !st.contains("400")
                        && !st.contains("500"))
                .map(str -> str + ";")
                .collect(Collectors.toList());
    }

    private List<String> crashRange(List<String> list) {
        List<String> rst = new ArrayList<>();
        List<String> slitted = splitter(list);
        for (int i = 0; i < slitted.size() - 1; i += 2) {
            rst.add(slitted.get(i) + slitted.get(i + 1));
        }
        return rst;
    }

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            List<String> temp = crashRange(in.lines().toList());
            temp.forEach(out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.file");
    }
}
