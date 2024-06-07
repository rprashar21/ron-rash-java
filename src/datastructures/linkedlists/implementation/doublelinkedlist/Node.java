package datastructures.linkedlists.implementation.doublelinkedlist;

public class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> nextNode;
    private Node<T> previousNode;

    public Node(final T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(final Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(final Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
