package datastructures.Trees.Questions;

public class BinaryTreeQuestions {

    public static void main(String[] args) {

        BinaryTreeSample binaryTreeSample = new BinaryTreeSample();
        binaryTreeSample.insert(3);
        binaryTreeSample.insert(5);
        binaryTreeSample.insert(2);
        binaryTreeSample.insert(1);
        binaryTreeSample.insert(4);
        binaryTreeSample.insert(6);
        binaryTreeSample.insert(7);

        binaryTreeSample.traversal("pre");
    }

}

class BinaryTreeSample {

    private NodeTree rootNode;

    public void insert(int data) {
        //
        if (rootNode == null) {
            rootNode = new NodeTree(data);
        } else {
            insert(rootNode, data);
        }
    }

    private void insert(NodeTree node, int data) {

        if (data < node.getData()) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), data);
            } else {
                node.setLeft(new NodeTree(data));
            }
        } else {
            if (node.getRight() != null) {
                insert(node.getRight(), data);
            } else {
                node.setRight(new NodeTree(data));
            }
        }
    }

    public void traversal(String traversalName) {
        // // preoder    root left  right
        // inordertraversal  left ,root ,right
        // postOrder // right left root
        // levelOrderTraversal root child
//        inorderTraversal();
//        preoderTraversal();
//        postOrder();
//        levelOrderTraversal();
        if (Traversal.INORDER.getName().equals(traversalName)) {
            inorderTraversal(rootNode);
        } else if (Traversal.POSTORDER.getName().equals(traversalName)) {
            postOrderTraversal(rootNode);
        } else if (Traversal.PREODER.getName().equals(traversalName)) {
            preOrder(rootNode);
        }
    }

    private void inorderTraversal(NodeTree node) {

        if (node.getLeft() != null) {
            inorderTraversal(node.getLeft());
        }
        System.out.print(node.getData() + " ");
        if (node.getRight() != null) {
            inorderTraversal(node.getRight());
        }
    }

    private void preOrder(NodeTree node) {
        System.out.println(node.getData() + " ");
        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }

        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    private void postOrderTraversal(NodeTree node) {

        if (node.getLeft() != null) {
            postOrderTraversal(node.getLeft());
        }

        if (node.getRight() != null) {
            postOrderTraversal(node.getRight());
        }
        System.out.println(node.getData() + " ");
    }

    private void levelOrder(NodeTree node) {

        if (rootNode.getLeft() != null) {
            levelOrder(node.getLeft());
        }
        System.out.println(node.getData() + " ");
        if (node.getRight() != null) {
            levelOrder(node.getRight());
        }
    }

}

class NodeTree {

    private int data;
    private NodeTree left;
    private NodeTree right;

    public NodeTree(final int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public NodeTree getLeft() {
        return left;
    }

    public void setLeft(final NodeTree left) {
        this.left = left;
    }

    public NodeTree getRight() {
        return right;
    }

    public void setRight(final NodeTree right) {
        this.right = right;
    }
}