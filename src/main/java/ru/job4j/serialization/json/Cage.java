package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cage {

    private int height;
    private int length;
    private int width;
    private int number;


    public Cage() {
    }

    public Cage(int height, int length, int width, int number) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cage {"
                + " height = " + height
                + ", length = " + length
                + ", width = " + width
                + ", number = " + number
                + '}';
    }
}
