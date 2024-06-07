package datastructures.linkedlists.questions;

public class CycleLinkedList {


    public static void main(String[] args) {
        // linkedList --> 3,2,0,-4 here 4 ispointijg to 2
        // fast and slow pointers do 2 things to chekc if the elemnet is present in the linkedList and second is to check
         // whether there is a cycle-- if the 2 pointers meet somewhere then it means that there is a cycle


       // Q. Find if it is the cylce linked list
      //  Q . find the length of the cylce  -- slow and faster again meets
      // Q . where

        CustomLinkedList ls1 = new CustomLinkedList();
        ls1.add(3);
        ls1.add(2);
        ls1.add(0);
        ls1.add(-4);


        isCycleLinkedList(ls1);
    }

    private static boolean isCycleLinkedList(final CustomLinkedList ls1) {

        final ListNode headNode = ls1.getHeadNode();
            ListNode fastNode = headNode;
            ListNode slowNode = headNode;
            while(slowNode.nextNode!=null && fastNode.nextNode.nextNode!=null)
            {
                slowNode=slowNode.nextNode;
                fastNode=fastNode.nextNode.nextNode;
                if(slowNode == fastNode)
                {
                    // to find the length of the cycle from here we keep the fast counter as it and check when we again geth the slow pointer
                    return true;
                }
            }
            return false;
        }


    private static int lengthOfCycleLInkedLis(final CustomLinkedList ls1) {

        final ListNode headNode = ls1.getHeadNode();
        ListNode fastNode = headNode;
        ListNode slowNode = headNode;
        while(fastNode!=null && fastNode.nextNode!=null)
        {
            slowNode=slowNode.nextNode;
            fastNode=fastNode.nextNode.nextNode;
            if(slowNode == fastNode)
            {
                // to find the length of the cycle from here we keep the fast counter as it and check when we again geth the slow pointer
                int counter=0;
               ListNode temp =slowNode;
               do{
                   temp=temp.nextNode;
                   counter++;
               }while (temp!=slowNode);
               return counter;
            }
        }
        return 0;
    }
}
