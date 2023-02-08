package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    private static class Node<T> {

        private T item;
        private Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T tempItem = head.item;
        Node<T> tempHead = head;
        head = tempHead.next;
        tempHead.next = null;
        size--;
        modCount++;
        return tempItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            final int definer = modCount;
            Node<T> temp = head;

            @Override
            public boolean hasNext() {
                if (definer != modCount) {
                    throw new ConcurrentModificationException();
                }
                return temp != null;
            }

            @Override
            public T next() {
                Node<T> rst = temp;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                temp = temp.next;
                return rst.item;
            }
        };
    }
}
