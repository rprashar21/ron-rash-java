package datastructures.linkedlists.leetcode;

public class ReverseALinkedList {

    private ListNode headNode;

    public void add(int data) {
        if (headNode == null) {
            headNode = new ListNode(data);
        } else {
            ListNode currentNode = headNode;
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.setNextNode(new ListNode(data));
        }
    }

    public void display() {
        if (headNode == null)
            System.out.println("There LinkedList is empty");
        else {
            ListNode currentNode = headNode;
            while (currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                currentNode = currentNode.nextNode;
            }
            System.out.println();
        }
    }

    public void reverse() {
        ListNode currentNode = headNode;
        ListNode previousNode = null;
        ListNode nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.nextNode;
            currentNode.setNextNode(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;

        }
        headNode = previousNode;
    }

    public void merge(ListNode list1, ListNode l2) {

        ListNode currentNode1 = list1;
        ListNode currentNode2 = l2;
        ReverseALinkedList l3 = new ReverseALinkedList();
        while (currentNode1 != null && currentNode2 != null) {
            if (currentNode1.getData() <= currentNode2.getData()) {
                l3.add(currentNode1.getData());
                currentNode1 = currentNode1.nextNode;
            } else {
                l3.add(currentNode2.getData());
                currentNode2 = currentNode2.nextNode;
            }
        }

        while (currentNode1 != null) {
            l3.add(currentNode1.getData());
            currentNode1 = currentNode1.nextNode;
        }
        while (currentNode2 != null) {
            l3.add(currentNode2.getData());
            currentNode2 = currentNode2.nextNode;
        }

       l3.display();
    }


    public static void main(String[] args) {
        ReverseALinkedList obj = new ReverseALinkedList();
        obj.add(10);
        obj.add(20);
        obj.add(30);
        System.out.println("before reverse");
        obj.display();
        System.out.println("after reverse");
        // obj.reverse();
        obj.display();
        ReverseALinkedList obj2 = new ReverseALinkedList();
        obj2.add(1);
        obj2.add(15);
        obj2.add(45);
        obj2.add(50);
        obj2.merge(obj.headNode, obj2.headNode);
    }
}

class ListNode {
    int data;
    ListNode nextNode;

    public ListNode(final int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public ListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(final ListNode nextNode) {
        this.nextNode = nextNode;
    }
}