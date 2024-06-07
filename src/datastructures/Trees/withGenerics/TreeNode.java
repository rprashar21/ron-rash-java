package datastructures.Trees.withGenerics;

public class TreeNode<T>{

    private T data;
    private TreeNode<T> leftNode;
    private TreeNode<T> rightNode;
    // when we implement the reomve method
    private TreeNode<T> parentNode;

    public TreeNode(final T data,TreeNode<T> parentNode) {
        this.data = data;
        this.parentNode=parentNode;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public TreeNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(final TreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(final TreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    public TreeNode<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(final TreeNode<T> parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
