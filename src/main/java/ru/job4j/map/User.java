package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        String ln = System.lineSeparator();
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Pavel", 1, birthday);
        User user2 = new User("Pavel", 1, birthday);
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((k, v) -> System.out.println(
                "User: " + k + ln
                        + "  hashCode: " + k.hashCode() + ln
                        + "  hash: " + (k.hashCode() ^ (k.hashCode() >>> 16)) + ln
                        + "  bucket: " + ((k.hashCode() ^ (k.hashCode() >>> 16)) & 15) + ln
                        + "Object: " + v + ln));
    }
}
