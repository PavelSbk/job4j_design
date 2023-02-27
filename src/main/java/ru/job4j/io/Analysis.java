package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            boolean flag = true;
            String temp = null;
            while (in.ready()) {
                String[] strings = in.readLine().split(" ");
                if (flag && (strings[0].contains("400") || strings[0].contains("500"))) {
                    temp = strings[1];
                    flag = false;
                }
                if (!flag && (strings[0].contains("200") || strings[0].contains("300"))) {
                    out.println(temp + ";" + strings[1] + ";");
                    flag = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.file");
    }
}
