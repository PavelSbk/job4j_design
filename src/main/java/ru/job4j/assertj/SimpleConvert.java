package ru.job4j.assertj;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleConvert {

    public String[] toArray(String... example) {
        return example;
    }

    public List<String> toList(String... example) {
        return Arrays.stream(example).toList();
    }

    public Set<String> toSet(String... example) {
        return Arrays.stream(example).collect(Collectors.toSet());
    }

    public Map<String, Integer> toMap(String... example) {
        return Stream.iterate(0, i -> i < example.length, i -> i + 1)
                .collect(Collectors.toMap(i -> example[i], i -> i, (s1, s2) -> s1));
    }

    public static void main(String[] args) {
        var sC = new SimpleConvert();
        Map<String, Integer> map = new SimpleConvert()
                .toMap("first", "second", "first", "three", "second", "four", "five");
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
