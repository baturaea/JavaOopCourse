package ru.baturaea2022.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int count;
    private int modCount;
    private final int capacity;
    private final ArrayList<T>[] items;

    public HashTable(int capacity) {
        this.capacity = capacity;
        //noinspection unchecked
        items = new ArrayList[capacity];

    }

    public HashTable() {
        capacity = DEFAULT_CAPACITY;
        //noinspection unchecked
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
    public boolean contains(Object element) {
        int indexByHash = element.hashCode() % capacity;
        if (items[indexByHash] == null) {
            return false;
        }

        return items[indexByHash].contains(element);
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
                        return items[i].get(indexInList);
                    }

                    indexInList -= items[i].size();
                }
            }

            return null;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[count];
        int j = 0;

        for (int i = 0; i < capacity; i++) {
            if (items[i] != null) {
                for (Object element : items[i]) {
                    resultArray[j] = element;
                    j++;
                }
            }
        }

        return resultArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < count) {

            for (int i = 0, j = 0; i < capacity; i++) {
                if (items[i] != null) {
                    for (Object element : items[i]) {
                        if (j == array.length) {
                            return array;
                        }
                        //noinspection unchecked
                        array[j] = (T1) element;
                        j++;
                    }
                }
            }
        }

        for (int i = 0, j = 0; i < capacity; i++) {
            if (items[i] != null) {
                for (Object element : items[i]) {
                    //noinspection unchecked
                    array[j] = (T1) element;
                    j++;
                }
            }
        }

        if (array.length > count) {
            array[count] = null;
        }

        return array;
    }

    @Override
    public boolean add(T element) {
        int indexByHash = element.hashCode() % capacity;
        if (items[indexByHash] == null) {
            items[indexByHash] = new ArrayList<>();
        }

        if (items[indexByHash].add(element)) {
            count++;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        int indexByHash = o.hashCode() % capacity;
        if (items[indexByHash] == null) {
            return false;
        }

        if (items[indexByHash].remove(o)) {
            if (items[indexByHash].size() == 0) {
                items[indexByHash] = null;
            }

            count--;
            modCount++;

            return true;
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
        if (c.size() == 0) {
            return false;
        }

        for (Object element : c) {
            //noinspection unchecked
            if (!add((T) element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        int expectedModCount = modCount;

        for (int i = 0; i < capacity; i++) {
            if (items[i] != null) {
                int beforeSize = items[i].size();

                if (items[i].retainAll(c)) {
                    count -= (beforeSize - items[i].size());
                    modCount += beforeSize - items[i].size();
                }

                if (items[i].size() == 0) {
                    items[i] = null;
                }
            }
        }

        return (expectedModCount != modCount);
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }
        int expectedModCount = modCount;

        for (int i = 0; i < capacity; i++) {
            if (items[i] != null) {
                int beforeSize = items[i].size();

                if (items[i].removeAll(c)) {
                    count -= (beforeSize - items[i].size());
                    modCount += beforeSize - items[i].size();
                }

                if (items[i].size() == 0) {
                    items[i] = null;
                }
            }
        }

        return (expectedModCount != modCount);
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }

        for (T element : this) {
            if (c.contains(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (T element : this) {
            builder.append(element);
            builder.append(", ");
        }

        builder.setLength(builder.length() - 2);
        builder.append(']');

        return builder.toString();
    }
}