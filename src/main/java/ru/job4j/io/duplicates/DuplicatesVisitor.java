package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        files.get(fileProperty).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printInfo() {
        files.forEach((key, value) -> {
            System.out.println(key + " ");
            value.forEach(System.out::println);
        });
    }
}
