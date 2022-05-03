package ru.baturaea2022.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount = 0;

    private static final int DEFAULT_CAPACITY = 10;

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        items = (T[]) new Object[capacity];
    }

    private void checkIndexInSize(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Index = " + index
                    + " must be 0 or greater but less " + size);
    }

    @Override
    public T get(int index) {
        checkIndexInSize(index);

        return items[index];
    }

    @Override
    public T set(int index, T obj) {
        checkIndexInSize(index);

        T tmp = items[index];
        items[index] = obj;

        return tmp;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {

            return currentIndex + 1 < size;
        }

        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }


    @Override
    public T[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public boolean add(T element) {
        if (size == items.length) {
            increaseCapacity();
        }

        items[size] = element;
        size++;
        modCount++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndexInSize(index);

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index,
                items, index + 1, size - index);
        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        checkIndexInSize(index);

        T oldItem = items[index];
        if (index < size - 1) {
            System.arraycopy(items, index + 1,
                    items, index, size - index - 1);
        }
        size--;
        modCount++;

        return oldItem;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int i = size - 1; i > 0; i--) {
            if (element.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection c) {
        T[] arrayFromCollection = (T[]) c.toArray();
        if (arrayFromCollection.length == 0) {
            return false;
        }

        for (int i = 0; ; i++) {
            if (i == size) {
                return false;
            }

            if (c.contains(items[i])) {
                break;
            }
        }

        boolean hasInItems = false;

        for (int i = size - 1; i >= 0; i--) {
            for (T e : arrayFromCollection) {
                if (e.equals(items[i])) {
                    hasInItems = true;
                    break;
                }
            }
            if (!hasInItems) {
                remove(i);
            }
            hasInItems = false;
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection c) {
        T[] arrayFromCollection = (T[]) c.toArray();
        if (arrayFromCollection.length == 0) {
            return false;
        }

        for (int i = 0; ; i++) {
            if (i == size)
                return false;
            if (c.contains(items[i])) {
                break;
            }
        }

        for (int i = 0; i < size; i++) {
            for (T e : arrayFromCollection) {
                if (e.equals(items[i])) {
                    remove(i);
                    break;
                }
            }
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsAll(Collection c) {
        T[] arrayFromCollection = (T[]) c.toArray();
        if (arrayFromCollection.length == 0) {
            return false;
        }

        for (T e : arrayFromCollection) {
            if (indexOf(e) < 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        T[] arrayFromCollection = (T[]) c.toArray();
        int length = arrayFromCollection.length;
        if (length == 0) {
            return false;
        }

        if (length > items.length - size) {
            ensureCapacity(length + size);
        }

        System.arraycopy(arrayFromCollection, 0, items, size, length);
        size += length;
        modCount++;

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexInSize(index);

        T[] arrayFromCollection = (T[]) c.toArray();
        int length = arrayFromCollection.length;
        if (length == 0) {
            return false;
        }

        if (arrayFromCollection.length > items.length - size) {
            ensureCapacity(length + size);
        }

        int numberMoved = size - index;
        System.arraycopy(items, index, items, index + numberMoved, length);
        System.arraycopy(arrayFromCollection, 0, items, index, length);
        size = +length;
        modCount++;

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(Object[] array) {
        if (array.length < size) {
            return Arrays.copyOf(items, size);
        }

        System.arraycopy(items, 0, array, 0, items.length);
        return (T[]) array;
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i < size; i++) {
            builder.append(items[i]);

            if (i == size -1) {
                builder.append(']');
                break;
            }
            builder.append(", ");
        }

        return builder.toString();
    }
}