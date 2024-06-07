package datastructures.stacks.implementation;

public class StackCustomLinkedList<T> {

    private Node headNode;

    public void push(T data) {
        if (headNode == null) {
            headNode = new Node<>(data);
        } else {
            Node oldNode = headNode;
            Node newNode = new Node<>(data);
            newNode.setpreviousNode(oldNode);
            headNode = newNode;
        }
    }

    public Object pop() {
        Object data = headNode.getData();
        headNode = headNode.getpreviousNode();
        return data;
    }
    
    public Object peek()
    {
        return headNode.getData();
    }
    
    public boolean isEmpty()
    {
        return headNode==null;
    }
}


class Node<T> {

    private Object data;
    private Node<Object> previousNode; // addresss to the next node

    public Node(final Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public Node<Object> getpreviousNode() {
        return previousNode;
    }

    public void setpreviousNode(final Node<Object> nextNode) {
        this.previousNode = nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}