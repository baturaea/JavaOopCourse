package ru.baturaea2022.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;
    private int modCount = 0;

    private void increaseCapacity() {
        if (elements.length == 0) {
            elements = Arrays.copyOf(elements, DEFAULT_CAPACITY);
        }
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity = " + capacity
                    + " must be 0 or greater");
        }

        elements = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, boolean isAdding) {
        int maxIndex = isAdding ? size : size - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index = " + index + " out of range [0 , " + maxIndex + "]");
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index, false);

        T oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int EXPECTED_MOD_COUNT = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            if (modCount != EXPECTED_MOD_COUNT) {
                throw new ConcurrentModificationException("The list has changed during the iteration.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("The following element is missing from the list");
            }

            ++currentIndex;

            return elements[currentIndex];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(elements, size, array.getClass());
        }

        System.arraycopy(elements, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T element) {
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index, true);

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
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
        checkIndex(index, false);

        T deletedElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        size--;
        modCount++;

        return deletedElement;
    }

    @Override
    public void clear() {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }

            size = 0;
            modCount++;
        }
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean retainAll(Collection c) {
        //noinspection unchecked
        return batchRemove(c, false);
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }
        //noinspection unchecked
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<T> c, boolean isRemove) {
        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i]) == isRemove) {
                remove(i);
                i--;
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                return true;

            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index, true);
        int length = c.size();

        if (length == 0) {
            return false;
        }

        if (length > elements.length - size) {
            ensureCapacity(length + size);
        }

        if (index < size) {
            System.arraycopy(elements, index, elements, index + length, size - index);
        }

        for (T element : c) {
            elements[index] = element;
            index++;
        }

        size += length;
        modCount++;

        return true;
    }

    public void trimToSize() {
        if (size < elements.length) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            elements = Arrays.copyOf(elements, capacity);
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
            builder.append(elements[i]);
            builder.append(", ");
        }

        builder.setLength(builder.length() - 2);
        builder.append(']');

        return builder.toString();
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
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}