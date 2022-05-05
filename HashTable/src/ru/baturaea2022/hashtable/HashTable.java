package ru.baturaea2022.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private int count;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private final ArrayList[] items;

    public HashTable(int capacity) {
        this.capacity = capacity;
        items = new ArrayList[capacity];

    }

    public HashTable() {
        capacity = DEFAULT_CAPACITY;
        items = new ArrayList[capacity];

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < count;
        }

        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            ++currentIndex;
            int indexInList = currentIndex;
            for (int i = 0; i < capacity; i++) {
                if (items[i] != null) {
                    if (indexInList < items[i].size()) {
                        return (T) items[i].get(indexInList);
                    }

                    indexInList -= items[i].size();
                }

            }

            return null;
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T element) {
        int indexByHash = element.hashCode() % capacity;
        if (items[indexByHash] == null) {
            items[indexByHash] = new ArrayList<T>();
        }

        items[indexByHash].add(element);
        count++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int indexByHash = o.hashCode() % capacity;
        if (items[indexByHash] == null) {
            return false;
        }

        if (items[indexByHash].remove(o)) {
            count--;
            modCount++;
        }

        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            items[i] = null;
        }

        count = 0;
        modCount++;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
}