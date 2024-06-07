package datastructures.linkedlists.questions;

public class MergeTowLinkedList {
    public static void main(String[] args) {

        CustomLinkedList l1 = new CustomLinkedList();
        CustomLinkedList l2 = new CustomLinkedList();

        CustomLinkedList ls1 = new CustomLinkedList();
        ls1.add(5);
        ls1.add(6);
        ls1.add(8);

        CustomLinkedList ls2 = new CustomLinkedList();
        ls2.add(7);
        ls2.add(9);
        ls2.add(10);
        ls2.add(11);
        mergeTwoLinkedList(ls1,ls2);
    }

    private static void mergeTwoLinkedList(final CustomLinkedList l1, final CustomLinkedList l2) {

        // compare the elements of the linked list whichever one is greater put in the another custom list
        CustomLinkedList l3 = new CustomLinkedList();
        while(l1!=null && l2!=null)
        {
            if(l1.getHeadNode().getValue() <= l2.getHeadNode().getValue())
            {
                l1.getHeadNode().nextNode=l1.getHeadNode().getNextNode();
                l3.add(l1.getHeadNode().getValue());
            }
            else{
                l2.getHeadNode().nextNode=l1.getHeadNode().getNextNode();
                l3.add(l2.getHeadNode().getValue());
            }

        }
        while(l1.getHeadNode().getNextNode()!=null)
        {
            l3.add(l1.getHeadNode().getValue());
        }
    }
}
