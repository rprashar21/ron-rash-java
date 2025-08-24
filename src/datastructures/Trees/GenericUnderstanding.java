package datastructures.Trees;

public class GenericUnderstanding {

    // Tree is a nn heirerachial ds
    //| Scenario                                 | Reason to Use Tree                        |
    //| ---------------------------------------- | ----------------------------------------- |
    //| Data has a **hierarchy**                 | Trees model parent-child relationships    |
    //| You need **fast search/sort/insert**     | Balanced trees give O(log n) ops          |
    //| Data changes dynamically                 | Trees adapt better than arrays/lists      |
    //| You need **range queries**               | Tree structures (e.g. BST, segment trees) |
    //| Organizing **indexable/searchable data** | Trees provide log(n) indexing             |

    // Analogy of a tree
    //| Computer Folders               | Tree Structure           |
    //| ------------------------------ | ------------------------ |
    //| Root Folder `/`                | Root node                |
    //| Subfolders like `/usr`, `/var` | Children nodes           |
    //| `/usr/bin`, `/usr/lib` etc.    | Deeper children          |
    //| Files                          | Leaf nodes (no children) |

//    /Other Intuitions:
//
//    BST = Like a dictionary — you know where to search based on sorted keys
//
//            Trie = Like an auto-complete engine — paths split by each character
//
//            Heap = Like a priority list — you always get the most urgent task
//
//    B+ Tree = Like a library index — keys at top, actual books at bottom

    //Example 1: File Storage System
    //Use an N-ary Tree to model folders & files.
    //Each node can have arbitrary children.
    //Traversal = folder navigation.

    //xample 2: Search System (e.g., product search)
    //Use Trie to store search terms.
    //User types cam → instantly return camera, camping tent, etc
}
