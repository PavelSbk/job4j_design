package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact {"
                + " zipCode = " + zipCode
                + ", phone = '" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final var contact = new Contact(123456, "+7 (111) 111-11-11");

        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (var fos = new FileOutputStream(tempFile);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        /* Чтение объекта из файла */
        try (var fis = new FileInputStream(tempFile);
             var ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
