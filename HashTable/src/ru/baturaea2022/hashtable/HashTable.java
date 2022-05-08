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
    public <T> T[] toArray(T[] array) {
        T[] resultArray = (T[]) new Object[count];
        int j = 0;

        for (T element : array) {
            int indexByHash = element.hashCode() % capacity;
            if (items[indexByHash] != null) {
                for (Object item : items[indexByHash]) {
                    if (item.equals(element)) {
                        resultArray[j] = element;
                        j++;
                    }
                }
            }
        }

        return resultArray;
    }

    @Override
    public boolean add(T element) {
        int indexByHash = element.hashCode() % capacity;
        if (items[indexByHash] == null) {
            items[indexByHash] = new ArrayList<T>();
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
        T[] arrayFromCollection = (T[]) c.toArray();
        int length = arrayFromCollection.length;
        if (length == 0) {
            return false;
        }

        for (T element : arrayFromCollection) {
            if (!add(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        //TODO переделать, падает
        T[] arrayFromCollection = (T[]) c.toArray();
        if (arrayFromCollection.length == 0) {
            return false;
        }

        for (int i = 0; ; i++) {
            if (i == arrayFromCollection.length) {
                return false;
            }

            if (contains(arrayFromCollection[i])) {
                break;
            }
        }

        boolean hasInItems = false;

        for (int i = 0; i < capacity; i++) {
            if (items[i] != null) {
                for(Object item : items[i]) {
                    for (T element : arrayFromCollection) {
                        if (element.equals(item)) {
                            hasInItems = true;
                            break;
                        }
                    }

                    if(!hasInItems) {
                        remove(item);
                        hasInItems = false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        T[] arrayFromCollection = (T[]) c.toArray();
        int length = arrayFromCollection.length;
        if (length == 0) {
            return false;
        }

        for (T element : arrayFromCollection) {
            if (!remove(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        T[] arrayFromCollection = (T[]) c.toArray();
        if (arrayFromCollection.length == 0) {
            return false;
        }

        for (T e : arrayFromCollection) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Iterator<T> iterator = this.iterator();
        int i = 0;
        while (iterator.hasNext()) {

            builder.append(iterator.next());
            if (i == count - 1) {
                builder.append(']');
                break;
            }
            builder.append(", ");
            i++;
        }

        return builder.toString();
    }
}