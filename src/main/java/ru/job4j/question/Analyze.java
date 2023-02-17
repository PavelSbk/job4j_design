package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    private static Map<Integer, String> setToMap(Set<User> set) {
        return set.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
    }

    private static int deleted(Set<User> previous, Set<User> current) {
        int rst = 0;
        Map<Integer, String> prevMap = setToMap(previous);
        Map<Integer, String> currMap = setToMap(current);
        for (Integer key : prevMap.keySet()) {
            if (!currMap.containsKey(key)) {
                rst++;
            }
        }
        return rst;
    }

    private static int change(Set<User> previous, Set<User> current) {
        int rst = 0;
        Map<Integer, String> prevMap = setToMap(previous);
        Map<Integer, String> currMap = setToMap(current);
        for (Integer key : prevMap.keySet()) {
            if (currMap.containsKey(key)
                    && !currMap.get(key).equals(prevMap.get(key))) {
                rst++;
            }
        }
        return rst;
    }

    private static int added(Set<User> previous, Set<User> current) {
        return current.size() - previous.size() + deleted(previous, current);
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        return new Info(added(previous, current),
                change(previous, current),
                deleted(previous, current));
    }
}
