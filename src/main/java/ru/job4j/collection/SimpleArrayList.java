package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (container.length <= size) {
            container = grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T oldValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        container[container.length - 1] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private T[] grow() {
        return Arrays.copyOf(container, (container.length + 1) * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final int definer = modCount;
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (definer != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}
