package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Public Sbk");
    }

    @Test
    void whenPairWithKeyValueNum() {
        String path = "data/pair_with_key_value";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("ключ1")).isEqualTo("значение=1");
        assertThat(config.value("ключ2")).isEqualTo("значение=");
    }

    @Test
    void whenPairWithComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "data/pair_without_key";
        assertThrows(IllegalArgumentException.class,
                () -> new Config(path).load());
    }

    @Test
    void whenPairWithoutValue() {
        String path = "data/pair_without_value";
        assertThrows(IllegalArgumentException.class,
                () -> new Config(path).load());
    }

    @Test
    void whenPairWithoutComparingSign() {
        String path = "data/pair_without_comparing_sign";
        assertThrows(IllegalArgumentException.class,
                () -> new Config(path).load());
    }

    @Test
    void whenPairWithoutKeyAndValue() {
        String path = "data/pair_without_key_and_value";
        assertThrows(IllegalArgumentException.class,
                () -> new Config(path).load());
    }
}
