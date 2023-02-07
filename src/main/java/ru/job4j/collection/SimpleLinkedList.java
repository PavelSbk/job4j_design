package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    private static class Node<E> {

        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<>(value, null);
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> temp = head;
        int counter = 0;
        while (counter < index) {
            temp = temp.next;
            counter++;
        }
        return temp.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            final int definer = modCount;
            Node<E> temp = head;

            @Override
            public boolean hasNext() {
                if (definer > modCount) {
                    throw new ConcurrentModificationException();
                }
                return temp != null;
            }

            @Override
            public E next() {
                Node<E> rst = temp;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                temp = temp.next;
                return rst.item;
            }
        };
    }
}
