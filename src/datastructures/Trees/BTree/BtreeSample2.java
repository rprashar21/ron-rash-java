package datastructures.Trees.BTree;

public class BtreeSample2 {

    //| order\_id | user\_id | total\_amount | status  | created\_at |
    //| --------- | -------- | ------------- | ------- | ----------- |
    //| 201       | 101      | 50.00         | shipped | 2023-01-02  |
    //| 202       | 102      | 40.00         | pending | 2023-01-03  |
    //| 203       | 101      | 75.00         | shipped | 2023-01-04  |
    //| 204       | 103      | 60.00         | shipped | 2023-01-05  |
    //| 205       | 102      | 80.00         | failed  | 2023-01-06  |
    //| 206       | 104      | 90.00         | pending | 2023-01-07  |

    // u create an index on order_id
    //CREATE INDEX idx_orders_user_id
    //ON Orders (user_id);

    // query  -- SELECT * FROM Orders WHERE user_id = 102;

    // constructed b+ tree
    //               [102]
    //              /     \
    //      [101,101]    [102,102,103,104]
    //     ↑ Leaf 1         ↑ Leaf 2

    //Step-by-Step:
    //
    //Start at root/internal node → key is 102.
    //
    //Go right (since 102 >= 102)
    //
    //In leaf node: [102, 102, 103, 104]
    //
    //Return matching entries for 102:
    //
    //Points to rows: order_id = 202, 205
    //
    //✅ No table scan. Just 1 internal lookup + 1 leaf scan = FAST
}
