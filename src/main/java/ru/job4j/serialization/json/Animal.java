package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "animal")
@XmlAccessorType(XmlAccessType.FIELD)
public class Animal {

    private String name;
    private int age;
    private boolean danger;
    private char sex;

    @XmlElementWrapper(name = "species")
    @XmlElement(name = "specie")
    private String[] species;

    private Cage cage;


    public Animal() {
    }

    public Animal(String name, int age, boolean danger, char sex, Cage cage, String... species) {
        this.species = species;
        this.name = name;
        this.age = age;
        this.danger = danger;
        this.sex = sex;
        this.cage = cage;
    }

    @Override
    public String toString() {
        return "Animal {"
                + " species = " + Arrays.toString(species)
                + ", name = '" + name + '\''
                + ", age = " + age
                + ", danger = " + danger
                + ", sex = " + sex
                + ", cage = " + cage
                + '}';
    }
}
