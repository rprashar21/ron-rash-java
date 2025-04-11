package collections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorSample {


    // An iterator is ajava object which allows u to loop thru elements of a collection like list set or
    // works with any collection ArrayList,HashSet,LinkedList
    // Lets u  traverse the collection
    // allows removal of elemenys safely
//  hasNext()	Checks if there's a next element
//    next()	Moves to and returns the next element
//    remove()	Removes the current element (optional, rarely used)

//    Scenario	                                    Use
//    Just read values	                        for-each or for
//    Need index	                            Classic for
//    Need to remove safely	                        Iterator
//    Need to move both ways or update	            ListIterator
//    Working with non-List collections like Set	Iterator

    public static void main(String[] args) {
        //Let’s say we want to print  bad orders from our list and also check theri count"
        int counter = 0;
        List<String> orders = new ArrayList<>();
        orders.add("bad order");
        orders.add("good order");
        orders.add("oder is bad");
        orders.add("order is ok");
        Iterator<String> iterator = orders.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if(next.contains("bad")){
                iterator.remove();
                counter++;
            }
        }
        System.out.println(orders);
        System.out.println(counter);

    }
}
