package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void readFile(String path) {
        try (FileInputStream in = new FileInputStream(path)) {
            StringBuilder text = new StringBuilder();
            int reader;
            while ((reader = in.read()) != -1) {
                text.append((char) reader);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(
                        Integer.parseInt(line) % 2 == 0 ? line + " is even number" : line + " is odd number"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "data/even.txt";
        readFile(path);
    }
}
