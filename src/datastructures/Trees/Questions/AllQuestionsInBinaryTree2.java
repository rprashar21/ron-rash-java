package datastructures.Trees.Questions;

import java.util.LinkedList;
import java.util.Queue;

public class AllQuestionsInBinaryTree2 {
    public static void main(String[] args) {

        BinryTree binryTree = new BinryTree();
        binryTree.insert(4);
        binryTree.insert(2);
        binryTree.insert(6);
        binryTree.insert(1);
        binryTree.insert(3);
        binryTree.insert(5);
        binryTree.insert(7);
        // kth Samllest Itme in this Binary Tre without using a a stack
        int k = 3;
        Node kthSmallestItemInBinaryTree = binryTree.kthSmallestItemInBinaryTree(binryTree.rootNode, k);
     //   System.out.println(kthSmallestItemInBinaryTree.getData());


        System.out.println("Parent Node is " + binryTree.getRootNode().getData());
        System.out.println("Smallest Node is " + binryTree.smallestNode().getData());


        // Algo is that if k is smaller than the no of node in left
        // then kth itme s in left subtree alternately k is greater than it should be in right


        // traverlsal inorder preoder postorder ,level order
        System.out.println("INORDER TRAVERSAL");
        binryTree.traversal(binryTree.rootNode);

        System.out.println("");
        System.out.println("Level order is a traversal where u see root node and then bth its child nodes");
        binryTree.levelOrderTraversal(binryTree.rootNode);

        System.out.println("");
        System.out.println("Top View is a traversal where u only see the right nodes");
        binryTree.topView(binryTree.rootNode);

        System.out.println("");

        int heightOfBinaryTree = binryTree.getHeightOfBinaryTree(binryTree.rootNode);
        System.out.println("Height of a binary Tree "+heightOfBinaryTree);
    }
}

class Node {
    private int data;
    private Node leftNode;
    private Node rightNode;

    public Node(final int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(final Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(final Node rightNode) {
        this.rightNode = rightNode;
    }
}

class BinryTree {

    public Node rootNode;

    public void insert(int data) {
        if (rootNode == null) {
            rootNode = new Node(data);
        } else {
            insert(data, rootNode);
        }
    }

    private void insert(int data, Node node) {

        if (data < node.getData()) {
            if (node.getLeftNode() != null) {
                // calling left Node recursively until null
                insert(data, node.getLeftNode());
            } else {
                node.setLeftNode(new Node(data));
            }
        } else {

            if (node.getRightNode() != null) {
                // calling left Node recursively until null
                insert(data, node.getRightNode());
            } else {
                node.setRightNode(new Node(data));
            }
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public Node smallestNode() {
        // leftMost child node with
        if (rootNode == null)
            throw new IllegalArgumentException("Binary Tree is empty");
        Node currentNode = rootNode;
        while (currentNode.getLeftNode() != null) {
            currentNode = currentNode.getLeftNode();
        }
        return currentNode;
    }

    public Node kthSmallestItemInBinaryTree(Node node, int k) {

        int n = findNumberOfNodes(node.getLeftNode()) + 1;

        if (n == k) {
            return node;
        }
        if (n > k) {
            return kthSmallestItemInBinaryTree(node.getLeftNode(), k);
        }
        if (n < k) {
            return kthSmallestItemInBinaryTree(node.getRightNode(), k);
        }

        return null;
    }

    private int findNumberOfNodes(final Node node) {

        if (node == null)
            return 0;


        return ((findNumberOfNodes(node.getLeftNode()) + findNumberOfNodes(node.getRightNode())))+1;
    }

    public void traversal(Node node)
    {
        System.out.print(node.getData()+" "); // inorder -- root left right
        if(node.getLeftNode()!=null)
        {
            traversal(node.getLeftNode());
        }
      //  System.out.print(node.getData()+" "); // preoder -- left right root

        if(node.getRightNode()!=null)
        {
            traversal(node.getRightNode());
        }
     //   System.out.print(node.getData()+" "); // post order == right left root
    }

    public void levelOrderTraversal(Node node)
    {
        /*
        * Add rootNode to the queeu
        * loop until the queue is empty
        * Dequeu the rootNode and the enque its left and right child
        * */
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty() )
        {
            //
            Node currentNode = queue.remove();
            System.out.print(currentNode.getData()+" ");

            if(currentNode.getLeftNode()!=null)
            queue.add(currentNode.getLeftNode());

            if(currentNode.getRightNode()!=null)
            queue.add(currentNode.getRightNode());
        }
    }

    public void topView(Node node)
    {
        // only right sided nodes
        System.out.print(node.getData()+" ");
        if(node.getRightNode()!=null)
        {
            topView(node.getRightNode());
        }
    }

    public int getHeightOfBinaryTree(final Node rootNode) {

        if(rootNode==null)
        {
            return 0;
        }
        else {
            int leftHeight = getHeightOfBinaryTree(rootNode.getLeftNode());
            int rightHeight =getHeightOfBinaryTree(rootNode.getRightNode());

            if(leftHeight>rightHeight)
                return leftHeight+1;
            else
                return rightHeight+1;
        }
    }
}