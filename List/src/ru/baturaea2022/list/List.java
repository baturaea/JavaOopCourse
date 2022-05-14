package ru.baturaea2022.list;

import java.util.NoSuchElementException;

public class List<T> {
    private ListNode<T> head;
    private int size;

    public List() {
    }

    public List(T data) {
        head = new ListNode<>(data);
        size = 1;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("The list is empty");
        }

        return head.getData();
    }

    public T getData(int index) {
        checkIndex(index, false);

        ListNode<T> currentNode = getNode(index);

        return currentNode.getData();
    }

    public T setData(int index, T data) {
        checkIndex(index, false);

        ListNode<T> currentNode = getNode(index);

        T oldData = currentNode.getData();
        currentNode.setData(data);

        return oldData;
    }

    public T deleteFirst() {
        if (size < 1) {
            throw new NoSuchElementException("The list is empty");
        }

        T deletedData = head.getData();
        head = head.getNext();
        size--;

        return deletedData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index, false);

        if (index == 0) {
            return deleteFirst();
        }

        ListNode<T> previousNode = getNode(index - 1);
        ListNode<T> deletedNode = previousNode.getNext();
        previousNode.setNext(deletedNode.getNext());
        size--;

        return deletedNode.getData();
    }

    public boolean deleteByData(T data) {
        if (size == 0) {
            return false;
        }
        ListNode<T> currentNode = head;
        ListNode<T> previousNode = null;
        while (currentNode != null) {
            if (currentNode.getData() == data || (currentNode.getData() != null && currentNode.getData().equals(data))) {
                if (previousNode != null) {
                    previousNode.setNext(currentNode.getNext());
                    size--;

                    return true;
                }

                head = currentNode.getNext();
                size--;

                return true;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        return false;
    }

    public void insertFirst(T data) {
        head = new ListNode<>(data, head);
        size++;
    }

    public void insert(int index, T data) {
        checkIndex(index, true);

        if (index == 0) {
            insertFirst(data);

            return;
        }

        ListNode<T> previousNode = getNode(index - 1);
        previousNode.setNext(new ListNode<>(data, previousNode.getNext()));
        size++;
    }

    public void reverse() {
        if (getSize() < 2) {
            return;
        }

        ListNode<T> currentNode = head.getNext();
        head.setNext(null);

        while (currentNode != null) {
            ListNode<T> next = currentNode.getNext();
            currentNode.setNext(head);
            head = currentNode;
            currentNode = next;
        }
    }

    public List<T> copy() {
        if (size == 0) {
            return null;
        }

        ListNode<T> currentNode = head;
        List<T> copyList = new List<>(currentNode.getData());
        ListNode<T> copyNode = copyList.head;
        copyList.size = size;

        while (currentNode.getNext() != null) {
            copyNode.setNext(new ListNode<>(currentNode.getNext().getData(), null));
            copyNode = copyNode.getNext();
            currentNode = currentNode.getNext();
        }
        return copyList;
    }

    private ListNode<T> getNode(int index) {
        checkIndex(index, false);
        ListNode<T> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    private void checkIndex(int index, boolean isAdding) {
        int maxIndex = isAdding ? size : size - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index = " + index + " out of range [0 , " + maxIndex + "]");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (ListNode<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            builder.append(currentNode.getData());
            builder.append(", ");
        }

        builder.setLength(builder.length() - 2);
        builder.append(']');

        return builder.toString();
    }
}