package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2)).isTrue();
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenAddSameChildThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2)).isTrue();
        assertThat(tree.add(1, 3)).isTrue();
        assertThat(tree.add(1, 3)).isFalse();
        assertThat(tree.findBy(1)).isPresent();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(1, 3)).isFalse();
    }

    @Test
    void whenIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 3);
        tree.add(5, 6);
        tree.add(5, 1);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenIsNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(1, 5);
        tree.add(5, 6);
        tree.add(5, 1);
        assertThat(tree.isBinary()).isFalse();
    }
}