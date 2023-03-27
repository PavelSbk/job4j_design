package ru.job4j.io.objectstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {

    public static void serialization(Object obj, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deserialization(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Object deserialized = (Object) in.readObject();
            System.out.println(deserialized.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Car car = new Car("Brand", "Model", 2000);
        String path = "data/serialized.dat";
        serialization(car, path);
        deserialization(path);
    }
}
