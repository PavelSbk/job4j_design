package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            /**
             * Данные в файл записываются в байтовом формате, поэтому нельзя просто открыть файл и прочитать значения.
             * Более того, при работе с файлами, содержащими данные в байтовом виде,
             * нужно точно знать как устроен файл,
             * чтобы считывать данные в правильном виде (считывать нужное количество байт из нужной позиции).
             */
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);
            /**
             * В данный момент указатель находится в самом конце файла,
             * то есть в конце последнего добавленного значения.
             * Чтобы считать все данные, нужно переместить указатель в начало с помощью метода seek(0);
             */
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            /**
             * Необходимо перезаписать символ A на символ B.
             * После считывания char указатель у нас сейчас находится
             * на позиции 6 (4 байта int + 2 байта char, то есть позади указателя ячейки 0-5.
             * Проверить положение указателя можно с помощью метода getFilePointer():
             */
            System.out.println(randomAccess.getFilePointer());
            /**
             * Чтобы переписать наш char, нужно установить позицию указателя прямо перед char,
             * а далее записать char еще раз:
             */
            System.out.println("=============");
            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            /**
             * Необходимо добавить к файлу новые данные double d = 3.14 и byte b = 127
             */
            System.out.println("=============");
            randomAccess.seek(randomAccess.length());
            randomAccess.writeDouble(3.14);
            randomAccess.writeByte(127);
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            System.out.println(randomAccess.readDouble());
            System.out.println(randomAccess.readByte());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
