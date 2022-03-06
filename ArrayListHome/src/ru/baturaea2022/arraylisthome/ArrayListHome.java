package ru.baturaea2022.arraylisthome;

import java.util.Arrays;

public class ArrayListHome<T> {
    private Object[] items;
    private int length;

    public ArrayListHome(int capacity) {
        length = 0;
        items = new Object[capacity];
    }

    public ArrayListHome() {
        length = 0;
        items = new Object[10];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public Object get(int index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("Index = " + index
                    + " must be 0 or greater but less " + length);
        }

        return items[index];
    }

    public void set(int index, T obj) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("Index = " + index
                    + " must be 0 or greater but less " + length);
        }

        items[index] = obj;
    }

    public void add(T obj) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = obj;
        length++;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void remove(int index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("Index = " + index
                    + " must be 0 or greater but less " + length);
        }

        if (index < length - 1) {
            System.arraycopy(items, index + 1,
                    items, index, length - index - 1);
        }
        length--;
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity = " + capacity
                    + " must be great 0");
        }

        if (length < capacity) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, length);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < length - 1; i++) {
            builder.append(items[i].toString());
            builder.append(", ");
        }

        builder.append(items[length - 1].toString());
        builder.append("]");

        return builder.toString();
    }
}