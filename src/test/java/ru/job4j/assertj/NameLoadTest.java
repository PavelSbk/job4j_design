package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    NameLoad nameLoad = new NameLoad();

    @Test
    void checkEmpty() {
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNotContainsSymbolEqual() {
        String input = "key: value";
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain the symbol \"=\"", input);

    }

    @Test
    void whenStartsFromSymbolEqual() {
        String input = "=key: value";
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key", input);
    }

    @Test
    void whenEndsWithSymbolEqual() {
        String input = "key: value=";
        assertThatThrownBy(() -> nameLoad.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", input);
    }
}
