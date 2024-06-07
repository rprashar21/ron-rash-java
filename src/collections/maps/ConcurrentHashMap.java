package collections.maps;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentHashMap {

    /*
    https://youtu.be/UwurUtvil7w

    HashMap is not thread safe
    can have one null key

    HashTable is thread safe -- entire hashTable is synchronized
    slow preformance -- no null keys and values

    SynchrnoizedHasMap threadSafe slow performance

    ConcurrentHashMap
    all segments are thread safe fast performance 16

    Important Questions
    Can two threads write from the same segment ?
    No thread acquires lock on the segment in put() operation and at a time only one thread can write from the segment
    but the thread can do a write operation on some other segment

    Can 2 threads Read in a single segment ?
    Yes 2 or multiple threads can read from a segment as it does not require lock on that segment

    Can multiple threads read from same segment ??
    Yes Threads dont acquire read lock on get() operation -- so multiple threads can read from same segment

    If one thread is writing into the segment can anothe Thread read from that segment
    yes -- last updated value will be seen by  the reading thread

    * */

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("a"); stringList.add("b");

        List<String> stringList1 = new ArrayList<>();
        stringList1.add("a");

//        stringList.addAll(stringList1);
//        System.out.println(stringList);
        stringList.removeAll(stringList1);
        System.out.println(stringList);
    }
}
