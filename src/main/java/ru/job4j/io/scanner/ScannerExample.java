package ru.job4j.io.scanner;

import java.io.*;
import java.util.Scanner;

public class ScannerExample {
    /**
     * Используя Scanner вычленяет только числа из потока данных.
     */
    public static void onlyNumbers(String data) {
        Scanner scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt() + " ");
            } else {
                scanner.next();
            }
        }
    }

    /**
     * Из потока данных вычленяет адреса почты, которые разделены между собой ","
     */
    public static void onlyEmail(String data) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(data.getBytes())).useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    /**
     * Читает числа в шестнадцатеричном виде и выводит в десятичном
     */
    public static void hexToDec(String data, File file) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt() + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        var ls = System.lineSeparator();
        var data = String.join(
                ls,
                "1 A 2",
                "3 4 B",
                "C 5 6"
        );
        onlyNumbers(data);
        System.out.println(ls + "=============");
        data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        onlyEmail(data);
        data = "A 1B FF 110";
        System.out.println("=============");
        var file = File.createTempFile("data", null);
        hexToDec(data, file);
    }
}
