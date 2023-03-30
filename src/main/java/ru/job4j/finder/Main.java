package ru.job4j.finder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        args = new String[]{
                "-d=c:/projects",
                "-n=(?i).*StartUITest.*",
                "-t=regex",
                "-o=C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\finder\\log.txt"
        };
        ArgsName argsName = ArgsName.of(args);
        Finder finder = new Finder(argsName);
        finder.writer();
    }
}
