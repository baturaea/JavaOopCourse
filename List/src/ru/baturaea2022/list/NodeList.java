package ru.baturaea2022.list;

public class NodeList<T> {
    private T data;
    private NodeList<T> next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeList<T> getNext() {
        return next;
    }

    public void setNext(NodeList<T> next) {
        this.next = next;
    }

    public NodeList(T data) {
        this.data = data;
    }
    
    public NodeList(T data, NodeList<T> next) {
        this.data = data;
        this.next = next;
    }
}