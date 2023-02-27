package ru.job4j.io;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {

    public void unavailable(String source, String target) {
        Pattern p = Pattern.compile("[0-2][0-9]:[0-5][0-9]:[0-5][0-9]");
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            boolean flag = true;
            String temp = null;
            while (in.ready()) {
                String s = in.readLine();
                Matcher m = p.matcher(s);
                if (flag && (s.contains("400") || s.contains("500"))) {
                    while (m.find()) {
                        temp = m.group() + ";";
                    }
                    flag = false;
                }
                if (!flag && (s.contains("200") || s.contains("300"))) {
                    while (m.find()) {
                        out.println(temp + m.group() + ";");
                    }
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
