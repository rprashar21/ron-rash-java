package datastructures.linkedlists.implementation.singleLinkedList;

public class Node<T extends Comparable<T>> {
    // linkedlist is a clooection of nodes which has data and the address of the next node

    private T data;
    private Node<T> nextNode;

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
}
