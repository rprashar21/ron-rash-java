package datastructures.Trees.withoutgenerics;

public class Tree {

    private TreeNode rootNode;

    public void insert(int data) {

        if (rootNode == null) {
            rootNode = new TreeNode(data);
        } else {
            // data is there
            insert(data, rootNode);
        }
    }

    public void insert(int value, TreeNode node) {

        if (value < node.getData()) {
            if (node.getLeftNode() != null) {
                // left node is not null repeat the same process on the current node
                insert(value, node.getLeftNode());
            } else {
                // left node is null and insert that data
                node.setLeftNode(new TreeNode(value));
            }
        } else {
            if (node.getRightNode() != null) {
                // left node is not null repeat the same process on the current node
                insert(value, node.getRightNode());
            } else {
                // left node is null and insert that data
                node.setRightNode(new TreeNode(value));
            }
        }
    }

    // lets do the traversal
    // 32,10
    public void traversal()
    {
        // three kinds of traversal
        //1. inorder -- left root right
        // preoder  -- root left and right
        // and postorder --right r
    }
}
