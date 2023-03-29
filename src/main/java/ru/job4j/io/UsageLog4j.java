package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String ln = System.lineSeparator();
        String name = "Ivan Inavoff";
        byte age = 23;
        short mb = 10;
        int yb = 2000;
        long id = 20_212_515L;
        float weight = 80.2F;
        double height = 180.55;
        char gender = 'M';
        boolean marriageStatus = false;
        LOG.debug("{}User info name : {}, {}"
                        + "age : {}, {}"
                        + "month of birth : {}, {}"
                        + "year of birth : {}, {}"
                        + "id number: {}, {}"
                        + "weight: {}, {}"
                        + "height: {}, {}"
                        + "gender: {}, {}"
                        + "marriageStatus: {}", ln, name, ln, age, ln, mb, ln, yb, ln, id, ln,
                weight, ln, height, ln, gender, ln, marriageStatus);
    }
}
