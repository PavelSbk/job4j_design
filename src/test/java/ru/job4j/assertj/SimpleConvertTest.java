package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void whenCheckList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "first", "second", "three", "second", "four", "five");
        assertThat(list).hasSize(7)
                .contains("second")
                .containsOnly("first", "first", "second", "three", "second", "four", "five")
                .contains("three", Index.atIndex(3))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(2));
    }

    @Test
    void whenCheckSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet(
                "first", "second", "first", "three", "second", "four", "five"
        );
        assertThat(set).hasSize(5)
                .contains("second")
                .containsOnly("first", "second", "three", "four", "five")
                .containsAnyOf("zero", "second", "six")
                .containsOnlyOnceElementsOf(Collections.singleton("first"))
                .doesNotContainNull()
                .doesNotContain("six");
    }

    @Test
    void whenCheckMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(
                "first", "second", "first", "three", "second", "four", "five"
        );
        assertThat(map).hasSize(5)
                .containsKeys("first", "three", "five")
                .containsValues(0, 6, 3)
                .doesNotContainKey("six")
                .doesNotContainValue(4)
                .containsEntry("four", 5);
    }
}
