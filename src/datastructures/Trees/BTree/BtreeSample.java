package datastructures.Trees.BTree;



public class BtreeSample {

// A b+ tree is a self balancing tree primarily used in dbs and file systems for indexing purposes
 // key properties
    // its a n-ary search tree not binary
    // All values are stored at the leaf level
    // the internal of non leafy nodes store keys for navigation , leafy nodes are linked for faster sequential access
    // maintains balance (height is always logarithmic to number of records)

//    Real-World Analogy
//
//    Think of a library catalog:
//
//    Main categories (A-D, E-H, etc.) guide you.
//
//    Final leaf nodes list actual books.
//
//    You can quickly navigate by skipping entire shelves

// | Use Case             | Why B+ Tree?                                                    |
//| -------------------- | --------------------------------------------------------------- |
//| **Database indexes** | Supports fast **range queries** and **binary search** on disk.  |
//| **File systems**     | Directory structure in NTFS, HFS, etc.                          |
//| **Search engines**   | Used for inverted index storage.                                |
//| **Key-value stores** | e.g., RocksDB, LevelDB — optimized for disk I/O using B+ trees. |

 //When a user searches for all transactions between two dates (BETWEEN clause),
    // a B+ Tree index on timestamp can serve that in O(log N) + range length time.

    // suppose u have a table like users table

 // CREATE INDEX idx_orders_user_status
    //ON Orders (user_id, status);

  // SELECT * FROM Orders
    //WHERE user_id = 101 AND status = 'shipped';

  //This is still stored as a B+ Tree.
    //
    //Ordered first by user_id, then status in the leaf nodes.

  // leaf nodes will store
//  [user_id, status, pointer_to_row]

 // internal nodes will store user_id only



}
