package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                .map(s -> s.substring(1))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(s -> s[0], s -> s[1])));
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String s : args) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + s + "' does not start with a '-' character"
                );
            }
            if (s.contains("-=")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + s + "' does not contain a key"
                );
            }
            if (s.indexOf('=') == s.length() - 1) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + s + "' does not contain a value"
                );
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + s + "' does not contain an equal sign"
                );
            }
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8="});
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
