package collections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastList {
    // what is fail fast and fail safe iterator ??
    // an iterator which fails fast does not allow modifcation while iterating like arraylist, hashmap  Vector

    // fail safe -- allows us to modify while iterating   --> CopyOnWriteArrayList, ConcurenHashMap;

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");

        Iterator<String> iterator = stringList.iterator(); // this is used to iterate over the list

        while(iterator.hasNext()){
            String element = iterator.next();
            System.out.println(element);
            // trying to add something while iterating
            stringList.add("c"); // fails fast and give concurrent modifcation exception--
        }

        // to over come this use CopyWriteOnArrayList
        List<String> stringList2 = new CopyOnWriteArrayList<>(); // this wil fail safe whihc means it will allow the modifcation
        // as this copys the value in new cloned arrayList

        stringList2.add("a");
        stringList2.add("b");

        Iterator<String> iterator2 = stringList2.iterator(); // this is used to iterate over the list

        while(iterator2.hasNext()){
            String element = iterator2.next();
            System.out.println(element);
            // trying to add something while iterating
            stringList2.add("c"); // fails fast and give concurrent modifcation exception--
        }
        System.out.println(stringList2); //
    }
}
