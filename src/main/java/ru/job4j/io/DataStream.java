package ru.job4j.io;

import java.io.*;

/**
 * Класс DataOutputStream позволяет записывать примитивные типы данных,
 * а также строковые значения в байтовом представлении.
 * DataInputStream позволяет считывать примитивы из их байтового представления.
 */

public class DataStream {

    public static void main(String[] args) {
        String path = "data/dataOutput.txt";
        String[] names = {"unit1", "unit2", "unit3"};
        int[] amount = {5, 7, 2};
        boolean[] checked = {true, false, true};

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
             DataInputStream in = new DataInputStream(new FileInputStream(path))) {
            for (int i = 0; i < names.length; i++) {
                out.writeUTF(names[i]);
                out.writeInt(amount[i]);
                out.writeBoolean(checked[i]);
            }
            while (true) {
                String n = in.readUTF();
                int a = in.readInt();
                boolean c = in.readBoolean();
                System.out.println("Name: " + n + ", amount: " + a + ", checked: " + c);
            }
            /** EOFException e
             * Это исключение нужно выносить в отдельный блок catch,
             * так как это не ошибка, и нам нужно отлавливать именно это исключение,
             * так как оно будет выброшено по достижении окончания файла и будет обрабатываться особым образом.
             */
        } catch (EOFException e) {
            System.out.println("End of file reached");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
