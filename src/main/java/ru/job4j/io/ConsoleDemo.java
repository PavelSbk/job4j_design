package ru.job4j.io;

import java.io.Console;
import java.util.Arrays;

/**
 * Доступ к консоли (командной строке) можно получить только из самой командной строки.
 * Попытка получить объект консоли в любой среде разработки вернёт null,
 * поэтому полученный объект требуется проверять на null.
 *
 * readLine() - читает с консоли строку, которую ввёл пользователь. Сохраняет данные в String.
 *
 * readPassword() - читает с консоли строку, которую ввёл пользователь.
 * Отличие от метода readLine() в том, что вводимые пользователем символы не будут отображаться на консоли
 * в целях безопасности. Сохраняет данные в char[] - массив байтов.
 *
 * format() - сохраняет отформатированную строку
 *
 * printf() - выводит отформатированную строку на печать без сохранения.
 *
 * flush() - принудительно выводит в консоль все данные, находящиеся в буфере в данный момент.
 */

public class ConsoleDemo {
    /**
     * В программе сначала получаем объект консоли (командной строки),
     * далее с помощью методов этого объекта сохраняем логин и пароль от пользователя,
     * выводя эти данные на экран.
     */
    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль недоступна");
            return;
        }
        login = console.readLine("%s", "Введите логин: ");
        console.printf("Ваш логин: %s\n", login);
        charPassword = console.readPassword("%s", "Введите пароль: ");
        System.out.println("Ваш пароль: " + String.valueOf(charPassword));
        Arrays.fill(charPassword, ' ');
    }
    /**
     * cd C:\projects\job4j_design\src\main\java\ru\job4j\io\
     * java ConsoleDemo.java
     * java -Dfile.encoding=UTF8 ConsoleDemo.java
     */
}
