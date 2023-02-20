package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> rst = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rst = in.lines()
                    .filter(l -> l.contains(" 404 "))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rst;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
    }
}
