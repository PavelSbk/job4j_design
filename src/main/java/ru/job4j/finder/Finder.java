package ru.job4j.finder;

import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Finder {

    private final ArgsName argsName;
    private Pattern pattern;

    public Finder(ArgsName argsName) {
        this.argsName = argsName;
    }

    private void validator() {
        if (argsName.get("d").matches("^[A-Za-z]:\\W")) {
            throw new IllegalArgumentException("Wrong path in -d parameter. "
                    + "You do not have sufficient privileges or permissions to operate in this directory, "
                    + "append package to the path.");
        }
        if (argsName.get("o").matches("^[A-Za-z]:\\W")) {
            throw new IllegalArgumentException("Wrong path in -o parameter. "
                    + "You do not have sufficient privileges or permissions to operate in this directory, "
                    + "append package to the path.");
        }
        if (!argsName.get("t").toLowerCase().endsWith("mask")
                && !argsName.get("t").toLowerCase().endsWith("name")
                && !argsName.get("t").toLowerCase().endsWith("regex")) {
            throw new IllegalArgumentException("Illegal argument -t parameter. "
                    + "Argument must be \"mask\" or \"name\" or \"regex\"");
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException("Parameter -n is empty.");
        }
    }

    private String regexConverter(String t) {
        String str = t.replaceAll("\\*", ".\\*");
        return str.replaceAll("\\?", ".\\?");
    }

    private Predicate<Path> predicate() {
        Predicate<Path> rst = p -> false;
        String t = argsName.get("t");
        String n = argsName.get("n");
        if ("mask".equalsIgnoreCase(t)) {
            pattern = Pattern.compile(regexConverter(n));
            rst = p -> p.toFile().getName().toLowerCase().matches(pattern.toString());
        }
        if ("name".equalsIgnoreCase(t)) {
            rst = p -> p.toFile().getName().contains(n);
        }
        if ("regex".equalsIgnoreCase(t)) {
            pattern = Pattern.compile(n);
            rst = p -> p.toFile().getName().toLowerCase().matches(pattern.toString());
        }
        return rst;
    }

    private List<Path> searcher() {
        Path root = Paths.get(argsName.get("d"));
        SearchFiles searcher = new SearchFiles(predicate());
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    public void writer() {
        validator();
        Path whereTo = Paths.get(argsName.get("o"));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(whereTo.toFile())))) {
            for (Path path : searcher()) {
                out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

