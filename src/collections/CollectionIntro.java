package collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;

public class CollectionIntro {
    /*
    *  https://www.javatpoint.com/collections-in-java
    *
    *  Iterator(I)--Collection(I) --> Collections
    *  List(I) --> ArrayList LInkedList Vector Stack
    *  Queue(i) --> PriorityQueue
    *  Dequqie(i) -- ArrayDeque
    *  Set(i)--> HashSet LinkedHasSet
    *
    * Iterator interface provides the facility of iterating the elements in a forward direction only.
    * basically iterates over a collection
    *    boolean hasNext() Object next() int remove()
    *
    * The Collection interface is the interface which is implemented by all the classes in the collection framework.
    * It declares the methods that every collection will have.
    * In other words, we can say that the Collection interface builds the foundation on which the collection framework depends.
    * Some of the methods of Collection interface are Boolean add ( Object obj), Boolean addAll ( Collection c), void clear(), etc. which are implemented by all the subclasses of Collection interface.
    *  List Interface
   List interface is the child interface of Collection interface.
* It inhibits a list type data structure in which we can store the ordered collection of objects.
*  It can have duplicate values.

   List interface is implemented by the classes ArrayList, LinkedList, Vector, and Stack.
* ArrayList -- maintains the insertion order,can contain duplicates fast/randoom access because of index
* LinkedList -- uses double linkedList internally -- maintains insertion order ,non synchronized, insertion [][][]
*   nodes se linked hai so it can perform additiona and removal at starting ending very qucikly than ArrayList
* Vector -- same as arraylist but is synchronized
* The stack is the subclass of Vector. It implements the last-in-first-out data structure, i.e., Stack. The stack contains all of the methods of Vector class and also provides its methods like boolean push(), boolean peek(), boolean push(object o), which defines its properties

* Queue interface maintains the first-in-first-out order.
* It can be defined as an ordered list that is used to hold the elements which are about to be processed.
* There are various classes like PriorityQueue, Deque, and ArrayDeque which implements the Queue interface.
* The PriorityQueue class implements the Queue interface.
* It holds the elements or objects which are to be processed by their priorities. PriorityQueue doesn't allow null values to be stored in the queue.
    *
    *
    *
    *
    * *
    *
    * */

    public static void main(String[] args) {

        // About Queues
        // Queue interface to hold elements which are about to be processed.
        // PriorityQueue doesn't allow null values to be stored in the queue.

//        Initially, the PriorityQueue stores the strings in a way that respects their natural ordering.
//        When you call remove() and poll(), the first two elements according to natural string ordering are removed. Since String objects are ordered lexicographically in Java, "Amit Sharma" and "JaiShankar" would typically be considered the first two elements to be removed, assuming there are no elements with a lexicographically smaller value. However, it's important to note that PriorityQueue's iterator does not guarantee to traverse the elements in any particular order. To see the elements in their priority order, one should poll elements from the queue one by one.
//
//                After removing two elements with remove() and poll(), the remaining elements are printed,
//        showcasing the updated state of the queue.



        PriorityQueue<String> queue=new PriorityQueue<String>();//["A",""]
        queue.add("Amit Sharma");
        queue.add("Vijay Raj");
        queue.add("JaiShankar");
        queue.add("Raj");
        System.out.println("head:"+queue.element());
        System.out.println("head:"+queue.peek());
        System.out.println("iterating the queue elements:");
        Iterator itr= queue.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        queue.remove();
        queue.poll();
        System.out.println("after removing two elements:");
        Iterator<String> itr2=queue.iterator();
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }


//        Deque interface extends the Queue interface. In Deque, we can remove and add the elements from both the side.
//        Deque stands for a double-ended queue which enables us to perform the operations at both the ends.
//        ArrayDeque class implements the Deque interface.
//        It facilitates us to use the Deque. Unlike queue, we can add or delete the elements from both the ends.
//        ArrayDeque is faster than ArrayList and Stack and has no capacity restrictions.

        Deque<String> deque = new ArrayDeque<String>();
        deque.add("Gautam");
        deque.add("Karan");
        deque.add("Ajay");
//Traversing elements
        for (String str : deque) {
            System.out.print(str+" "); // its Array Deque so it maintains the insertion order ,where as the priorrity Queue will sort

        }
        System.out.println("-------");
        System.out.println( deque.peekFirst());// Gaitam
        System.out.println(deque.peekLast());// Ajay
        deque.addFirst("Rohit");// will add starting of the queue
        deque.addLast("Harun");//

        for (String str : deque) {
            System.out.print(str+" "); // its Array Deque so it maintains the insertion order ,where as the priorrity Queue will sort

        }
    }
}
