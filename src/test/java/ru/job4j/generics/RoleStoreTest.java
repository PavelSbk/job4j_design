package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    RoleStore store = new RoleStore();

    @Test
    void whenAddAndFindThenRolenameIsPetr() {
        store.add(new Role("1", "Petr"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        store.add(new Role("1", "Petr"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsPetr() {
        store.add(new Role("1", "Petr"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceThenRolenameIsMaxim() {
        store.add(new Role("1", "Petr"));
        store.replace("1", new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Maxim");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        store.add(new Role("1", "Petr"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsPetr() {
        store.add(new Role("1", "Petr"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        store.add(new Role("1", "Petr"));
        boolean rsl = store.replace("1", new Role("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        store.add(new Role("1", "Petr"));
        boolean rsl = store.replace("10", new Role("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        store.add(new Role("1", "Petr"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        store.add(new Role("1", "Petr"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}
