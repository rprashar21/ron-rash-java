package datastructures.linkedlists.questions;

public class RemoveDuplicatesSortedList {
    public static void main(String[] args) {

        CustomLinkedList ls = new CustomLinkedList();
        ls.add(1);
        ls.add(1);
        ls.add(1);
        ls.add(2);
        ls.deleteDuplicates();
        ls.traverse();

        CustomLinkedList ls1 = new CustomLinkedList();
        ls1.add(5);
        ls1.add(6);
        ls1.add(8);

        CustomLinkedList ls2 = new CustomLinkedList();
        ls2.add(7);
        ls2.add(9);
        ls2.add(10);
        ls2.add(11);
        final CustomLinkedList customLinkedList = mergeTwoLIsts(ls1, ls2);
        System.out.println(customLinkedList);

    }

    private static CustomLinkedList mergeTwoLIsts(final CustomLinkedList ls1, final CustomLinkedList ls2) {
        ListNode firsthead = ls1.getHeadNode();
        ListNode secondHead = ls2.getHeadNode();

        CustomLinkedList ls = new CustomLinkedList();
        while (firsthead != null && secondHead != null) {
            if (firsthead.getValue() < secondHead.getValue()) {
                ls.add(firsthead.getValue());
                firsthead = firsthead.nextNode;
            } else {
                ls.add(secondHead.getValue());
                secondHead = secondHead.getNextNode();
            }

        }
        // add the remaining elements of whichever linkedlist has left over elements
        if (firsthead != null) {
            while (firsthead != null) {
                ls.add(firsthead.getValue());
                firsthead = firsthead.nextNode;
            }
        } else {
            while (secondHead != null) {
                ls.add(secondHead.getValue());
                secondHead = secondHead.nextNode;
            }
        }
        return ls;
    }

}

class ListNode {
    int value;
    ListNode nextNode;

    public ListNode(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public ListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(final ListNode nextNode) {
        this.nextNode = nextNode;
    }
}

class CustomLinkedList {

    private ListNode headNode;

    public void add(int data) {
        if (headNode == null) {
            headNode = new ListNode(data);
        } else {
            ListNode currentNode = headNode;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(new ListNode(data));
        }
    }

    public void traverse() {
        if (headNode == null)
            return;
        ListNode currentNode = headNode;
        while (currentNode != null) {
            System.out.print(currentNode.getValue() + " ");
            currentNode = currentNode.getNextNode();
        }
    }

    public void deleteDuplicates() {
        if (headNode == null)
            return;
        ListNode currentNode = headNode;
        while (currentNode.getNextNode() != null) {
            if (currentNode.getValue() == currentNode.getNextNode().getValue()) {
                // detele the node and set to the next node and make that node the currrent one
                currentNode.setNextNode(currentNode.nextNode.nextNode);
                currentNode = currentNode.nextNode.nextNode;
            } else {
                currentNode = currentNode.nextNode;
            }
        }
    }

    public ListNode getHeadNode() {
        return headNode;
    }

    @Override
    public String toString() {
        return "CustomLinkedList{" +
                "headNode=" + headNode +
                '}';
    }
}
