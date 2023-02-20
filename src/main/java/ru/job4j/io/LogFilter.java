package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class LogFilter {

    public List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
        return in.lines()
                .map(l -> l.split("0 "))
                .flatMap(Arrays::stream)
                .filter(l -> l.contains(" 404 "))
                .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
    }
}
