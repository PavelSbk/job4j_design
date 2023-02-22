package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    private static boolean checkString(String string) {
        final Pattern pattern = Pattern.compile("[^=].*=.+");
        boolean rst = !string.isEmpty();
        if (string.contains("#")) {
            return false;
        }
        if (!string.matches(String.valueOf(pattern))) {
            throw new IllegalArgumentException("string does not match pattern key=value");
        }
        return rst;
    }

    public void load() {
        final Pattern pattern = Pattern.compile("([^=\\s]+)=(.*).*");
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            values.putAll(in.lines()
                    .filter(Config::checkString)
                    .map(pattern::matcher)
                    .filter(Matcher::matches)
                    .collect(Collectors.toMap(matcher -> matcher.group(1),
                            matcher -> matcher.group(2))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
