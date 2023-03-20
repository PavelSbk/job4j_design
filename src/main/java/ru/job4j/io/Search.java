package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        argsValidator(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    private static void argsValidator(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Less than two parameters.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException("Root folder setting error.");
        }
        if (args[1].length() > 1 && !args[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong file extension.");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
