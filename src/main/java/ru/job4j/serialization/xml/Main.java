package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Animal;
import ru.job4j.serialization.json.Cage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        String ln = System.lineSeparator();
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        final Animal boar = new Animal(
                "Pumba",
                5,
                true,
                'M',
                new Cage(2000, 2000, 2000, 8041),
                "Boar", "Cloven-hoofed");
        JAXBContext cont = JAXBContext.newInstance(Animal.class);
        Marshaller marsh = cont.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marsh.marshal(boar, writer);
            xml = writer.getBuffer().toString();
            System.out.printf("%s%s%s", ln, xml, ln);
        }
        Unmarshaller unmarsh = cont.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Animal rst = (Animal) unmarsh.unmarshal(reader);
            System.out.println(rst);
        }
    }
}
