package com.imesaros.crackingthetechnicalinterview.chapter2;

import java.util.stream.IntStream;

public class CustomHashMapWithDynamicArray<K, V> {

    private static final int DEFAULT_CAPACITY = 4;
    private DynamicArray dynamicArray = new DynamicArray();


    public void put(K key, V value) {
        int hash = hash(key);
        if (dynamicArray.get(hash) == null) {
            dynamicArray.put(new Entry<K, V>(key, value), hash);
            return;
        }
        Entry<K, V> previous = null;
        Entry<K, V> current = dynamicArray.get(hash);
        while (current != null) {
            if (current.key.equals(key)) {
                current.key = key;
                current.value = value;
                return;
            }
            previous = current;
            current = dynamicArray.get(current.next);
        }
        previous.next = dynamicArray.put(new Entry<K, V>(key, value), hash);
    }

    public V get(K key) {
        int hash = hash(key);
        if (dynamicArray.get(hash) == null) {
            return null;
        }
        Entry<K, V> entry = dynamicArray.get(hash);
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = dynamicArray.get(entry.next);
        }
        return null;
    }

    public boolean remove(K key) {
        int hash = hash(key);
        if (dynamicArray.get(hash) == null) {
            return false;
        }
        return dynamicArray.remove(key, hash);
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % DEFAULT_CAPACITY;
    }


    private class DynamicArray {
        private Entry<K, V>[] array = new Entry[DEFAULT_CAPACITY];
        private int lastElementIndex = DEFAULT_CAPACITY - 1;

        private int put(Entry<K, V> entry, int hash) {
            if (array[hash] == null) {
                array[hash] = entry;
                return hash;
            }
            if (lastElementIndex == array.length - 1) {
                Entry<K, V>[] newArray = new Entry[2 * array.length];
                IntStream.rangeClosed(0, array.length - 1).forEach(i -> newArray[i] = array[i]);
                array = newArray;
            }
            lastElementIndex++;
            array[lastElementIndex] = entry;
            return lastElementIndex;
        }

        private Entry<K, V> get(Integer index) {
            return index == null ? null : array[index];
        }

        private boolean remove(K key, int hash) {
            if (array[hash] == null) {
                return false;
            }
            Entry<K, V> current = array[hash];
            int currentIndex = hash;
            while (current != null) {
                if (current.key.equals(key)) {

                    Integer nextIndex = current.next;
                    while (nextIndex != null) {
                        Entry<K, V> next = array[nextIndex];

                        if (next.next == null) {
                            array[currentIndex] = new Entry<K, V>(next.key, next.value, null);
                        } else {

                            array[currentIndex] = new Entry<K, V>(next.key, next.value, nextIndex);
                        }
                        currentIndex = nextIndex;
                        nextIndex = next.next;
                    }
                    array[currentIndex] = null;
                    if (currentIndex == lastElementIndex) {
                        do {
                            lastElementIndex--;
                        }
                        while ((lastElementIndex >= 0) && (array[lastElementIndex] == null));

                    }

                    return true;
                }
                currentIndex = current.next;
                current = dynamicArray.get(current.next);
            }
            return false;
        }
    }

    private static class Entry<K, V> {
        private K key;
        private V value;
        private Integer next;

        private Entry(K key, V value) {
            this(key, value, null);
        }

        private Entry(K key, V value, Integer next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
