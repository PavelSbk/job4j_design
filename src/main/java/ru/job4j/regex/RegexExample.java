package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    /**
     * Pattern compile() - создает неизменяемый шаблон из переданной последовательности символов,
     * который используется для проверки его на наличие присутствия в тексте
     * Matcher matches() - проверяет данное сопоставление на полное соответствие шаблона тексту
     */
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("Я учусь на Job4j");
        Matcher matcher = pattern.matcher("Я учусь на Job4j");
        boolean isPresent = matcher.matches();
        System.out.println(isPresent);
        Matcher matcher1 = pattern.matcher("Я учусь на курсе Job4j");
        Matcher matcher2 = pattern.matcher("я учусь на Job4j"); // регистрозависимость
        System.out.println(matcher1.matches());
        System.out.println(matcher2.matches());
        System.out.println("===========================================");
        /**
         * compile(String regex, int flags)
         * CASE_INSENSITIVE - регистр букв учитываться не будет (латинские буквы)
         */
        pattern = Pattern.compile("Я учусь на Job4j", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher("Я учусь на jOb4j");
        System.out.println(matcher.matches());
        System.out.println("===========================================");
        /**
         * find() - проверяет шаблон на его присутствие в тексте
         * find() ищет ближайшее совпадение. Его можно применять многократно.
         * Каждый вызов метода find() начинается с места, где закончился его предыдущий вызов.
         * Чтобы найти многократные вхождения шаблона в коде, нужно использовать find() в цикле
         */
        pattern = Pattern.compile("Job4j");
        matcher = pattern.matcher("Я учусь на Job4j");
        System.out.println(matcher.find());
        matcher = pattern.matcher("Job4j и Job4j и Job4j");
        while (matcher.find()) {
            System.out.println("Найдено совпадение");
        }
        System.out.println("===========================================");
        /**
         * Matcher group() - возвращает найденное совпадение в виде строки
         */
        matcher = pattern.matcher("Job4j1 и Job4j2 и Job4j3");
        while (matcher.find()) {
            System.out.println("Найдено совпадение " + matcher.group());
        }
        System.out.println("===========================================");
        /**
         * start() - получает индекс, в котором находится первый символ искомой последовательности символов.
         * end() - получает индекс, следующий за последним символов искомой последовательности символов.
         */
        matcher = pattern.matcher("Job4j2 и Job4j3 и Job4j4");
        while (matcher.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher.start() + " iEnd: " + matcher.end());
        }
        System.out.println("===========================================");
        /**
         * replaceAll() - заменяет найденные совпадения другой строкой
         */
        pattern = Pattern.compile("123");
        matcher = pattern.matcher("1231 1232 1233");
        System.out.println(matcher.replaceAll("Job4j"));
        System.out.println("===========================================");
        /**
         * Метасимвол - это символ или последовательность символов, имеющие специальное назначение
         * Позиция - это место перед или после любого символа в слове
         * Позиционные метасимволы:
         * ^ - начало строки (_текст1 555 текст).
         * Находит заданную последовательность, только если она является началом строки
         * $ - конец строки (текст1 555 текст_).
         * Находит заданную последовательность, только если она является концом строки
         * \b - граница слова (_текст1_ 555 _тек%ст_).
         * Слово - это непрерывная последовательность любых букв, цифр, символов.
         * \B - не граница слова (т_е_к_с_т_1 5_5_5). Все позиции, кроме границ слов
         * Метасимволы группировки (символьные классы). Определяют диапазоны символов:
         * [abc] - символ(ы) или диапазон символов.
         * Каждый проверяемый в тексте символ проверяется на присутствие в заданном диапазоне.
         * Запись может быть в разном виде.
         * Например, [a] - символ a,
         * [at] - символы a и t,
         * [a-k] - символы от a до k,
         * [1-4] - символы от 1 до 4 и т.д.
         * [^abc] - все символы, кроме перечисленных.
         * Каждый проверяемый в тексте символ проверяется на отсутствие в заданном диапазоне.
         * Противоположность предыдущему варианту [abc]. Виды записи такие же, как в [abc].
         * [a-сx-z] - объединение диапазонов.
         * В данной записи совмещены диапазоны а-с и x-z.
         * Запись может содержать и одиночные символы.
         * [^a-сx-z] - все символы, кроме входящих в данные диапазоны.
         * Противоположность предыдущему варианту [a-сx-z].
         * Запись также может содержать и одиночные символы.
         * Метасимволы группы литералов:
         * . (точка) - любой символ
         * \d - любой цифровой символ
         * \D - любой символ, кроме цифровых
         * \s - символ пробела
         * \S - все символы, кроме пробела
         * \w - символ буквы, цифры или символ подчеркивания
         * \W - любой символ, кроме буквы, цифры и символа подчеркивания
         * Метасимволы редактирования текста:
         * \n - символ новой строки
         * \r - символ возврата каретки в начало строки
         * \t - символ табуляции
         * Квантификатор - это метасимвол, который обозначает количество вхождений символа
         * и всегда ставится после него. При использовании квантификаторов находится не только одиночный символ,
         * но и последовательность символов (кроме нулевых вхождений).
         * * - ноль или более раз
         * + - один или более раз
         * ? - один или отсутствует
         * {x} - х раз. Т.е. символ должен встречаться не меньше х раз подряд
         * {x,} - х раз и более. Т.е. символ должен встречаться не меньше х раз подряд
         * {x, y} - от х до y раз (оба включительно)
         */
        pattern = Pattern.compile("11");
        matcher = pattern.matcher("111111");
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
        System.out.println("===========================================");
        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+"); // "\\D+" любой нецифровой символ от одного раза
        System.out.println(Arrays.toString(rsl));
        System.out.println("===========================================");
        /**
         * Найти в тексте дату, записанную в виде "дд.мм.гггг".
         * Чтобы создать корректный шаблон для поиска,
         * нужно разделить данную дату на последовательности символов (из чего состоит).
         * В нашем случае это 2 цифры, точка, 2 цифры, точка, 4 цифры.
         */
        pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        matcher = pattern.matcher("24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println();
        pattern = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b"); // \b - ограничение (отдельное слово)
        matcher = pattern.matcher("24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println("===========================================");
        /**
         * Нужно найти адреса электронной почты в тексте.
         * Запись адреса электронной почты может выглядеть примерно так: peter-2022@example.com.
         * \S - все символы, кроме пробела
         * {x,} - х раз и более. Т.е. символ должен встречаться не меньше х раз подряд
         */
        pattern = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        matcher = pattern.matcher("peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
