package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Gson gson = new GsonBuilder().create();
        final Animal boar = new Animal(new String[]{"Boar", "Cloven-hoofed"},
                "Pumba",
                5,
                true,
                'M',
                new Cage(2000, 2000, 2000, 8041));
        final String boarJson =
                "{"
                        + "\"species\":"
                        + "[\"Boar\",\"Cloven-hoofed\"],"
                        + "\"name\":"
                        + "\"Pumba\","
                        + "\"age\":5,"
                        + "\"danger\":true,"
                        + "\"sex\":\"M\","
                        + "\"cage\":{"
                        + "\"hight\":2000,"
                        + "\"lenght\":2000,"
                        + "\"width\":2000,"
                        + "\"number\":2152"
                        + "}"
                        + "}";
        final Animal animalMod = gson.fromJson(boarJson, Animal.class);
        System.out.println(gson.toJson(boar));
        System.out.println(animalMod);
    }
}
