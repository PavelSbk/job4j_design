package ru.job4j.io.objectstream;

import java.io.Serializable;

/**
 * Serializable - это маркерный интерфейс, который не содержит никаких методов,
 * он просто говорит JVM, что объект этого класса можно сериализовать, то есть сохранить в байтовом представлении.
 */
public class Car implements Serializable {

    /** final long serialVersionUID
     * В переменной serialVersionUID хранится уникальный ключ,
     * который при начале сериализации и окончании десериализации должен совпадать,
     * то есть если на входе и выходе эти идентификаторы совпадают,
     * значит объект восстановился точно в том же состоянии, в котором он был перед началом процесса трансформации.
     */
    private static final long serialVersionUID = 1L;

    private String brand;

    private String model;

    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car {"
                + " brand = '" + brand + '\''
                + ", model = '" + model + '\''
                + ", year = " + year
                + '}';
    }
}
