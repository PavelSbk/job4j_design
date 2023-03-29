package ru.job4j.serialization.json;

public class Cage {

    private final int hight;
    private final int lenght;
    private final int width;
    private final int number;

    public Cage(int hight, int lenght, int width, int number) {
        this.hight = hight;
        this.lenght = lenght;
        this.width = width;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cage {"
                + " hight = " + hight
                + ", lenght = " + lenght
                + ", width = " + width
                + ", number = " + number
                + '}';
    }
}
