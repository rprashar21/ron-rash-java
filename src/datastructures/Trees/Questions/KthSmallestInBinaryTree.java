package datastructures.Trees.Questions;

import java.util.Stack;

public class KthSmallestInBinaryTree {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(12);
        binaryTree.insert(6);
        binaryTree.insert(3);
        binaryTree.insert(8);
        binaryTree.insert(18);
        binaryTree.insert(20);

        int k =5; // finding the 3rd smallest
        int theKthSmallestNodeInBinaryTree = binaryTree.findTheKthSmallestNodeInBinaryTree(binaryTree, k);

        System.out.println(theKthSmallestNodeInBinaryTree);

        BinaryTree binaryTree2 = new BinaryTree();
        binaryTree2.insert(12);
        binaryTree2.insert(6);
        binaryTree2.insert(3);
        binaryTree2.insert(8);
        binaryTree2.insert(18);
        binaryTree2.insert(20);

        // checking if two binary trees are equal or not
        System.out.println(binaryTree.isSameTrees(binaryTree.rootNode,binaryTree2.rootNode));
    }
}

class BinaryTree {

    public TreeNode rootNode;

    public void insert(int data) {
        if (rootNode == null)
            rootNode = new TreeNode(data);
        else
            insert(rootNode, data);
    }

    private void insert(final TreeNode node, final int data) {

        if (data < node.getVal()) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), data);

            } else {
                node.setLeft(new TreeNode(data));
            }
        } else {
            if (node.getRight() != null) {
                insert(node.getRight(), data);

            } else {
                node.setRight(new TreeNode(data));
            }
        }
    }


    public int findTheKthSmallestNodeInBinaryTree(final BinaryTree binaryTree, final int k) {
        TreeNode currentNode = binaryTree.rootNode;
        Stack<TreeNode> stack = new Stack<>();
        int counter = 0;
        while (currentNode != null || !stack.isEmpty()) { // stack is not empty has to be checked
            // traverse till the end
            if (currentNode != null ) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                currentNode = stack.pop();
                counter++;
                if (counter == k) {
                    return currentNode.getVal();
                }
                currentNode = currentNode.right;
            }
        }
        return -1;
    }

    public boolean isSameTrees(final TreeNode b1, final TreeNode b2) {

        if(b1==null && b2==null)
            return true;
        else if ((b1 == null && b2 != null) || (b1 != null && b2 == null))
            return false;
        else if(b1.getVal()!=b2.getVal())
            return false;

        return isSameTrees(b1.left,b2.left) && isSameTrees(b1.right,b2.right);
        }
    }


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(final int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(final TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(final TreeNode right) {
        this.right = right;
    }
}