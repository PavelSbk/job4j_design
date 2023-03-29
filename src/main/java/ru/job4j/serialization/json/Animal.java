package ru.job4j.serialization.json;

import java.util.Arrays;

public class Animal {

    private final String[] species;
    private final String name;
    private final int age;
    private final boolean danger;
    private final char sex;
    private final Cage cage;


    public Animal(String[] species, String name, int age, boolean danger, char sex, Cage cage) {
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
