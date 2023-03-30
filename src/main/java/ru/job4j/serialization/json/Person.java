package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {

    private boolean sex;
    private int age;
    private Contact contact;
    private String[] status;

    public Person() {
    }

    public Person(boolean sex, int age, Contact contact, String... status) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.status = status;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Person {"
                + " sex = " + sex
                + ", age = " + age
                + ", contact = " + contact
                + ", status = " + Arrays.toString(status)
                + '}';
    }

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        System.out.println(jsonContact);

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        System.out.println(jsonStatuses);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person));
    }
}
