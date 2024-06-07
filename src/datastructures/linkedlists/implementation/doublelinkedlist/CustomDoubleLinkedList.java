package datastructures.linkedlists.implementation.doublelinkedlist;

public class CustomDoubleLinkedList<T extends Comparable<T>> {

    /*The linkedlist in java is a double linked list*/
    private Node<T> headNode;
    private Node<T> tailNode;



    // 5--->6-->7-->8
    // 5-->
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (headNode == null) {
            headNode = newNode;
            tailNode = newNode;
        }
        else {
            // we have to insert the new item  to the ned of the list
            // we can manipulate the tail node  and its refernces in 0(1) complexity
            tailNode.setNextNode(newNode);
            newNode.setPreviousNode(tailNode);
            tailNode = newNode;
        }
    }
    public void traverse() {
        if (headNode == null) {
            return;
        }
        Node<T> currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.getNextNode();
        }
    }

    public void delete(T data) {

        if (headNode == null)
            return;

        if (headNode.getData().compareTo(data) == 0) {
            Node<T> nextNode = headNode.getNextNode();
            nextNode.setPreviousNode(null);
            headNode=nextNode;
            return;
        }

        if (tailNode.getData().compareTo(data) == 0) {
            Node<T>  previousNode = tailNode.getPreviousNode();
            previousNode.setNextNode(null);
            tailNode=previousNode;
            return;
        }
        Node<T> currentNextNode = headNode.getNextNode();
        while (currentNextNode != null) {
            if (currentNextNode.getData().compareTo(data) == 0) {
                currentNextNode.getPreviousNode().setNextNode(currentNextNode.getNextNode());
                currentNextNode.getNextNode().setPreviousNode(currentNextNode.getPreviousNode());
            }
            currentNextNode = currentNextNode.getNextNode();
        }
    }

    public Node<T> getMiddleNode()
    {
        Node<T> slowPointer = this.headNode;
        Node<T> fastPointer = this.headNode;

        while (slowPointer.getNextNode()!=null && fastPointer.getNextNode().getNextNode()!=null)
        {
            slowPointer=slowPointer.getNextNode();
            fastPointer=fastPointer.getNextNode().getNextNode();
        }
        return slowPointer;
    }

}
