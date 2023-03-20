package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        System.out.printf("%-30s%20s%s", "File Name:", "File size:", System.lineSeparator());
        for (File subFile : file.listFiles()) {
            System.out.printf("%-30s%20d%s", subFile.getName(), subFile.length(), System.lineSeparator());
        }
    }
}
