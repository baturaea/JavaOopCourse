package ru.baturaea2022.list;

public class List<T> {
    private NodeList<T> head;
    private int size = 0;

    public List() {
    }

    public List(T value) {
        this.head = new NodeList<>(value);
        size = 1;
    }

    public int getLength() {
        return size;
    }

    public NodeList<T> getHead() {
        return head;
    }

    public T getValue(int index) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("Component index = " + index
                    + ", must be 0 or greater but less " + size);
        }

        int i = 0;

        for (NodeList<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                return p.getData();
            }
            i++;
        }

        assert head != null;
        return head.getData();
    }

    public T setValue(int index, T value) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("Component index = " + index
                    + ", must be 0 or greater but less " + size);
        }

        int i = 0;

        for (NodeList<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                T oldData = p.getData();
                p.setData(value);

                return oldData;
            }

            i++;
        }

        return null;
    }

    public T deleteFromHead() {
        if (size == 1) {
            throw new IllegalArgumentException("Can't remove head because the list is empty.");
        }

        T oldData = head.getData();
        head = head.getNext();
        size--;

        return oldData;
    }

    public T deleteByIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("Component index = " + index
                    + ", must be 0 or greater but less " + size);
        }

        if (index == 0) {
            return this.deleteFromHead();
        }

        int i = 1;

        for (NodeList<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (i == index) {
                prev.setNext(p.getNext());
                size--;
                return p.getData();
            }

            i++;
        }

        return null;
    }

    public boolean deleteByValue(T value) {
        for (NodeList<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(value)) {
                if (prev != null) {
                    prev.setNext(p.getNext());
                    size--;
                    return true;
                }

                head = p.getNext();
                size--;
                return true;
            }
        }

        return false;
    }

    public void insertInHead(T value) {
        head = new NodeList<>(value, head);
        size++;
    }

    public void insert(int index, T value) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("Component index = " + index
                    + ", must be 0 or greater but less " + size);
        }

        if (index == 0) {
            this.insertInHead(value);
        } else {
            int i = 1;
            NodeList<T> newNode = new NodeList<>(value);

            for (NodeList<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
                if (i == index) {
                    newNode.setNext(p);
                    prev.setNext(newNode);
                    size++;
                    break;
                }

                i++;
            }
        }
    }

    public void reversalList() {
        NodeList<T> endNode = head;

        for (NodeList<T> p = head.getNext(); p != null; p = p.getNext()) {
            head = new NodeList<>(p.getData(), head);
        }

        endNode.setNext(null);
    }

    public List<T> copyList() {
        List<T> copyList = new List<>(head.getData());

        for (NodeList<T> p = head.getNext(); p != null; p = p.getNext()) {
            copyList.insertInHead(p.getData());
        }

        copyList.reversalList();

        return copyList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (NodeList<T> p = head; p != null; p = p.getNext()) {
            builder.append(p.getData());
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }
}