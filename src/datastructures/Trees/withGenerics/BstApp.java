package datastructures.Trees.withGenerics;

import datastructures.Trees.withGenerics.BinarySearchTree01;

public class BstApp {

    /*
    * Binary tree parent child left and right
    * Binary Search Tree is what wwe use
    *  complete and full binary tree
    * In a binary tree we the interior node should have a children
    *
    * In Practocal we use the Binary Search tree
    * Binary Search Tree  ?? left node has less than right and parent
    * Because of which we can do a binary search
    *
    * Insert -- 25 ,20 , 15, 28 ,23,30,26 -- first node is the rootNode
    *          25
    *      20       28
    *    15   23   26   30
    *
    * */

    public static void main(String[] args) {
        // since we have used generic here we can insert anu data
        // generics dont take primitive type

        BinarySearchTree01<Integer> bst1 = new BinarySearchTree01<>();
//        bst1.insert(25);
//        bst1.insert(20);
//        bst1.insert(15);
//        bst1.insert(23);
//        bst1.insert(28);
//        bst1.insert(30);
//        bst1.insert(47);
//        bst1.insert(27);
         bst1.insert(1);
        bst1.insert(2);
        bst1.insert(5);
        bst1.insert(3);
        bst1.insert(4);
        bst1.insert(6);

        System.out.println("inserting done");
        /*
        *            25
        *        20          28
        *    15     23    27    30
        *                       47
        *
        * preoder - 25,15,20,23.28,27,20,47
        * inorder - 15,
        * */

        System.out.println("Min value is "+bst1.getMin());
        System.out.println("Man value is "+bst1.getMax());

        BinarySearchTree01<String> bst2 = new BinarySearchTree01<>();
        // this will do natural sorting order
        bst2.insert("India");
        bst2.insert("Austria");
        bst2.insert("Bangladesh");
        bst2.insert("United KingDom");
        bst2.insert("Pakistan");

        System.out.println("insertion done");

        //25,20,15,23,28,30,47
        // after tree balancing
        // lest do inorder left root right
        // 15 20 23 25 28 30

        bst1.traversal();
        //  bst1.topView();

    }


}
