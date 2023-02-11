package ru.job4j.set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    Set<Integer> set = new SimpleSet<>();

    @Test
    void whenAddNonNull() {
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAdd12131Left123() {
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set).containsOnly(1,2,3);
    }
}