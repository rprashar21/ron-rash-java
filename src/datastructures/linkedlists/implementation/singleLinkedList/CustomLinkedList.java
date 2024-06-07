package datastructures.linkedlists.implementation.singleLinkedList;

import static java.util.Objects.isNull;

public class CustomLinkedList<T extends Comparable<T>> implements List<T> {

    // this will have the methods like insert,remove,traverse,size;

    private Node<T> headNode;
    private int nodeSize = 0;

    @Override
    public void insert(final T data) {
        //5--> 6
        if (headNode == null)
            headNode = new Node<>(data);
        else {
            //  insertAtBeginning(data);
            insertAtEnd(data);
        }
        nodeSize++;
    }

    private void insertAtBeginning(final T data) {
        Node newNode = new Node<>(data);
        newNode.setNextNode(headNode);
        headNode = newNode;
    }

    private void insertAtEnd(final T data) {
        Node currentNode = headNode;
        while (currentNode.getNextNode() != null)
            currentNode = currentNode.getNextNode();
        currentNode.setNextNode(new Node(data));
    }

    @Override
    public void delete(final T data) {

        if (isNull(headNode)) {
            System.out.println("This list is empty");
            return;
        }
        Node currentNode = headNode;

        if (headNode.getData().compareTo(data) == 0) {
            headNode = currentNode.getNextNode();
            return;
        }
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getData().compareTo(data) == 0) {
                // here we need to update the references
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                currentNode=currentNode.getNextNode();
            }
        }
        nodeSize--;
    }

    @Override
    public void traverse() {

        if (isNull(headNode)) {
            System.out.println("Your List is empty");
            return;
        }
        Node currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.getNextNode();
        }
    }

    @Override
    public int size() {
        return nodeSize;
    }
}
