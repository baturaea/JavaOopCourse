package ru.baturaea2022.list;

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
        checkIndexInSize(index);

        ListNode<T> current = iterator(index);

        return current.getData();
    }

    public T setData(int index, T data) {
        checkIndexInSize(index);

        ListNode<T> current = iterator(index);

        T oldData = current.getData();
        current.setData(data);

        return oldData;
    }

    public T deleteFirst() {
        if (size < 1) {
            throw new IllegalStateException("The list is empty");
        }

        T deletedData = head.getData();
        head = head.getNext();
        size--;

        return deletedData;
    }

    public T deleteByIndex(int index) {
        checkIndexInSize(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListNode<T> previous = iterator(index - 1);
        ListNode<T> deletedNode = previous.getNext();
        previous.setNext(deletedNode.getNext());
        size--;

        return deletedNode.getData();
    }

    public boolean deleteByData(T data) {
        int i = 0;

        for (ListNode<T> current = head; current != null; current = current.getNext()) {
            if (current.getData().equals(data)) {
                deleteByIndex(i);

                return true;
            }
            i++;
        }

        return false;
    }

    public void insertFirst(T data) {
        head = new ListNode<>(data, head);
        size++;
    }

    public void insert(int index, T data) {
        checkIndexInSize(index);

        if (index == 0) {
            insertFirst(data);
        } else {
            ListNode<T> previous = iterator(index - 1);
            ListNode<T> insertNode = new ListNode<>(data, previous.getNext());
            previous.setNext(insertNode);
            size++;
        }
    }

    public void rotate() {
        if (getSize()>1){
            ListNode<T> current = head.getNext();
            head.setNext(null);

            while (current != null) {
                ListNode<T> next = current.getNext();
                current.setNext(head);
                head = current;
                current = next;
            }
        }
    }

    public List<T> copy() {
        List<T> copyList = new List<>(head.getData());
        ListNode<T> copyNode = copyList.head;
        if (size > 1) {
            ListNode<T> current = head.getNext();

            while (current != null) {
                copyNode.setNext(new ListNode<>(current.getData(), null));
                copyNode = copyNode.getNext();
                copyList.size++;
                current = current.getNext();
            }
        }

        return copyList;
    }

    private ListNode<T> iterator(int index) {
        int i = 0;

        for (ListNode<T> current = head; current != null; current = current.getNext()) {
            if (i == index) {
                return current;
            }
            i++;
        }

        return null;
    }

    private void checkIndexInSize(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Argument index = " + index + " must be 0 or greater but less " + size);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (ListNode<T> current = head; current != null; current = current.getNext()) {
            builder.append(current.getData());

            if (current.getNext() == null) {
                builder.append(']');
                break;
            }
            builder.append(", ");
        }

        return builder.toString();
    }
}