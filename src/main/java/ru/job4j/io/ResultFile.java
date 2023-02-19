package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {

    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }
        return array;
    }

    public static String tableToString(int[][] array) {
        var stringB = new StringBuilder();
        for (int[] arr : array) {
            for (int i : arr) {
                stringB.append(i).append(" ");
            }
            stringB.append(System.lineSeparator());
        }
        return stringB.toString();
    }

    public static void writeMTable(int size, String path) {
        try (FileOutputStream out = new FileOutputStream(path)) {
            out.write(tableToString(multiple(size)).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("Hello, world!".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeMTable(20, "data/multipleTable.txt");
    }
}
