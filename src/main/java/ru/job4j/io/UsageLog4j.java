package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String ln = System.lineSeparator();
        String name = "Ivan Inavoff";
        byte age = 23;
        short id = 212;
        int by = 2000;
        float weight = 80.2F;
        double height = 180.55;
        char gender = 'M';
        boolean marriageStatus = false;
        LOG.debug("{}User info name : {}, {}"
                        + "age : {}, {}"
                        + "id number: {}, {}"
                        + "year of birth : {}, {}"
                        + "weight: {}, {}"
                        + "height: {}, {}"
                        + "gender: {}, {}"
                        + "marriageStatus: {}", ln, name, ln, age, ln, id, ln, by, ln,
                weight, ln, height, ln, gender, ln, marriageStatus);
    }
}
