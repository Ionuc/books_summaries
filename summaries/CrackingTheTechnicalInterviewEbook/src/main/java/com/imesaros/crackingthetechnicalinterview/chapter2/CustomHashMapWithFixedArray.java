package com.imesaros.crackingthetechnicalinterview.chapter2;

public class CustomHashMapWithFixedArray<K, V> {

    private final int capacity;

    private final Entry<K, V>[] map;

    public CustomHashMapWithFixedArray(int capacity) {
        this.capacity = capacity;
        map = new Entry[capacity];
    }

    public void put(K key, V value) {
        int hash = hash(key);
        if (map[hash] == null) {
            map[hash] = new Entry<K, V>(key, value);
            return;
        }
        Entry<K, V> previous = null;
        Entry<K, V> current = map[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                current.key = key;
                current.value = value;
                return;
            }
            previous = current;
            current = current.next;
        }
        previous.next = new Entry<K, V>(key, value);
    }

    public V get(K key) {
        int hash = hash(key);
        if (map[hash] == null) {
            return null;
        }
        Entry<K, V> entry = map[hash];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean remove(K key) {
        int hash = hash(key);
        if (map[hash] == null) {
            return false;
        }
        Entry<K, V> previous = null;
        Entry<K, V> current = map[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    map[hash] = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    private int hash(K k) {
        return Math.abs(k.hashCode()) % capacity;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this(key, value, null);
        }

        private Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
