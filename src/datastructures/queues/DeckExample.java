package datastructures.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class DeckExample {

    /*
    *  faster than stacks and linkedList
    *  not thread safe // hence perfomance is good
    *  null elements are nt allowed
    *  insertion and removal can hapen from both ends
    * used in trees bfs to print from let to right and right to left
    *
    *
    *   Comparison of Queue and Deque methods
Queue Method	Equivalent Deque Method
add(e)	addLast(e)
offer(e)	offerLast(e)
remove()	removeFirst()
poll()	pollFirst()
element()	getFirst()
peek()	peekFirst()
    * */




    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

//        deque.add(10);
//        deque.add(20);
//        deque.add(30);
//        System.out.println(deque);
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        System.out.println(deque);
        deque.addFirst(20);
        deque.addLast(30);

        deque.push(40);


        System.out.println(deque);//[40,20,10,30]

    }
}
