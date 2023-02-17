package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    static int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int bucket(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private boolean checkBucketForNull(K key) {
        return table[bucket(key)] == null;
    }

    private boolean checkKey(K key) {
        int bucket = bucket(key);
        return !checkBucketForNull(key)
                && Objects.hashCode(key) == Objects.hashCode(table[bucket].key)
                && Objects.equals(table[bucket].key, key);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] prevTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> map : prevTable) {
            if (map != null) {
                table[bucket(map.key)] = map;
            }
        }
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rst = checkBucketForNull(key);
        if (rst) {
            table[bucket(key)] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rst;
    }

    @Override
    public V get(K key) {
        V rst = null;
        if (checkKey(key)) {
            rst = table[bucket(key)].value;
        }
        return rst;
    }

    @Override
    public boolean remove(K key) {
        boolean rst = checkKey(key);
        if (rst) {
            table[bucket(key)] = null;
            count--;
            modCount++;
        }
        return rst;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {
            private final int exModCount = modCount;
            private int counter = 0;

            @Override
            public boolean hasNext() {
                if (exModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (counter < table.length && table[counter] == null) {
                    counter++;
                }
                return counter < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[counter++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
