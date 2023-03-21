package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.nio.charset.Charset;

public class ConsoleChat {

    private String input;
    private final Scanner scanner = new Scanner(System.in);
    private final String path;
    private final String botAnswers;
    private final String ln = System.lineSeparator();
    private final List<String> answers = new ArrayList<>();
    private final List<String> log = new LinkedList<>();
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        readPhrases();
        String stop = "Введена команда \"СТОП\" - бот перестал отвечать";
        String cont = "Введена команда \"ПРОДОЛЖИТЬ\" - бот снова отвечает";
        String out = "Введена команда \"ЗАКОНЧИТЬ\" - программа завершена. Лог обновлён.";
        boolean run = true;
        boolean stopAnswer = false;
        String answer;
        int random;
        while (run) {
            System.out.println("Введите слово или фразу: ");
            input = scanner.nextLine();
            log.add(String.format(">> пользователь: %s%s", input, ln));
            if (OUT.equalsIgnoreCase(input)) {
                run = false;
                System.out.print(out);
                log.add(String.format(">> %s%s", out, ln));
                saveLog(log);
                stopAnswer = true;
            }
            if (!stopAnswer && STOP.equalsIgnoreCase(input)) {
                stopAnswer = true;
                System.out.println(stop);
                log.add(String.format(">> %s%s", stop, ln));
            }
            if (!stopAnswer) {
                random = (int) (Math.random() * (answers.size() - 1));
                answer = answers.get(random);
                System.out.print(answer);
                log.add(String.format(">> бот: %s", answer));
            }
            if (stopAnswer && CONTINUE.equalsIgnoreCase(input)) {
                stopAnswer = false;
                System.out.println(cont);
                log.add(String.format(">> %s%s", cont, ln));
            }
        }
    }

    private void readPhrases() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines()
                    .map(s -> s + ln)
                    .forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.defaultCharset(), true))) {
            log.forEach(pw::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/ConsoleChatLog.txt", "data/ConsoleChatAnswers.txt");
        cc.run();
    }
}
