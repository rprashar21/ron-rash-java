package datastructures.Trees.withoutgenerics;

public class TreeNode {
    private int data;
    private TreeNode leftNode;
    private TreeNode RightNode;

    public TreeNode(final int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(final TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return RightNode;
    }

    public void setRightNode(final TreeNode rightNode) {
        RightNode = rightNode;
    }

}
