package datastructures.linkedlists.implementation.doublelinkedlist;

public class App {

    public static void main(String[] args) {
        CustomDoubleLinkedList<Integer> customDoubleLinkedList = new CustomDoubleLinkedList<>();
        customDoubleLinkedList.insert(10);
        customDoubleLinkedList.insert(20);
        customDoubleLinkedList.insert(30);
        customDoubleLinkedList.traverse();

        customDoubleLinkedList.delete(20);
        customDoubleLinkedList.traverse();

        // get middle node
        final Node<Integer> middleNode = customDoubleLinkedList.getMiddleNode();
        System.out.println(middleNode);

    }

}
