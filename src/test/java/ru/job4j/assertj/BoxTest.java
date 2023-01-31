package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void whenIsTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenIsCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void whenIsUnknownObject() {
        Box box = new Box(2, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void when0Vertices() {
        Box box = new Box(0, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0);
    }

    @Test
    void when4Vertices() {
        Box box = new Box(4, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(4);
    }

    @Test
    void when8Vertices() {
        Box box = new Box(8, 12);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8);
    }

    @Test
    void whenIsExist() {
        Box box = new Box(8, 12);
        boolean exists = box.isExist();
        assertThat(exists).isTrue();
    }

    @Test
    void whenIsNotExist() {
        Box box = new Box(5, 12);
        boolean exists = box.isExist();
        assertThat(exists).isFalse();
    }

    @Test
    void whenAreaIs314dot16() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(314.2D, withPrecision(0.5D));
    }

    @Test
    void whenAreaIs27dot7() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(27.7D, withPrecision(0.5D));
    }

    @Test
    void whenAreaIs24dot0() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24.0D, withPrecision(0.5D));
    }
}