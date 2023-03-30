package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Gson gson = new GsonBuilder().create();
        final Animal boar = new Animal(
                "Pumba",
                5,
                true,
                'M',
                new Cage(2000, 2000, 2000, 8041),
                "Boar", "Cloven-hoofed");
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
                        + "\"height\":2000,"
                        + "\"length\":2000,"
                        + "\"width\":2000,"
                        + "\"number\":2152"
                        + "}"
                        + "}";
        final Animal animalMod = gson.fromJson(boarJson, Animal.class);
        System.out.println(gson.toJson(boar));
        System.out.println(animalMod);
        System.out.println();
        JSONObject jsonObject = new JSONObject();
        List<String> species = List.of("Boar", "Cloven-hoofed");
        jsonObject.put("species", species);
        jsonObject.put("name", boar.getName());
        jsonObject.put("age", boar.getAge());
        jsonObject.put("danger", boar.isDanger());
        jsonObject.put("sex", boar.getSex());
        jsonObject.put("cage", new Cage(2000, 2000, 2000, 8041));
        System.out.println(jsonObject);
        System.out.println(new JSONObject(boar));
    }
}
