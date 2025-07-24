package cache.practice;

import java.util.HashMap;

public class LruCache01 {

    //wwhat is a cache --

    // lru cache -- least recently used
    // cache -- arrya

     //                   0 1 2 3
    // cache ---6--> 5 [6|5|1|2|] -- get(2) [5|6|1|2|].  --> 7
    //                  h.    t              h.    t

    // basic

    // insert [1,2,3,4]---> // fixed size 4
    // get[index] -- swap
    // checksize [10,1,2,3]


    private int[] array;

}
