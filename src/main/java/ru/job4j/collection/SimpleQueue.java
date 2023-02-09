package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize = 0;
    private int outSize = 0;

    public T poll() {
        if (inSize == 0) {
            throw new NoSuchElementException();
        } else {
            while (inSize != 0) {
                out.push(in.pop());
                outSize++;
                inSize--;
            }
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        if (outSize == 0) {
            in.push(value);
            inSize++;
        } else {
            while (outSize != 0) {
                in.push(out.pop());
                inSize++;
                outSize--;
            }
        }
    }
}
