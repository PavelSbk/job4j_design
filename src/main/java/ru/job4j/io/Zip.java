package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipOutputStream;
import java.io.*;
import java.util.zip.ZipEntry;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile().getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validator(ArgsName value) {
        if (!Files.isDirectory(Paths.get(value.get("d")))) {
            throw new IllegalArgumentException("Root folder setting error.");
        }
        if (!value.get("e").startsWith(".") || value.get("e").length() < 2) {
            throw new IllegalArgumentException("Wrong file extension.");
        }
        if (!value.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Wrong file extension '.zip' missed");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Root folder has a value not equal to three. Usage ROOT_FOLDER.");
        }
        ArgsName arg = ArgsName.of(args);
        validator(arg);
        Path source = Paths.get(arg.get("d"));
        List<Path> list = Search
                .search(source, path -> !path.toFile().getName().endsWith(arg.get("e")));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFiles(list, new File(arg.get("o")));
    }
}
