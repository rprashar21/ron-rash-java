package datastructures.Trees.withGenerics;

public class BinarySearchTree01<T extends Comparable<T>> {

    // T extends Comparabale

    // We need the root node so that we can store the reference somewhere
    private TreeNode<T> rootNode;

    public void insert(T data) {
        // when the parent is null
        if (rootNode == null) {
            rootNode = new TreeNode<>(data, rootNode);
        } else {
            // there is some data present now  fill in the left node and right node
            // call the insert method recursively
            insert(data, rootNode);
        }
    }

    private void insert(final T data, final TreeNode<T> node) {

        // here we are filling in the left and right nodes
        // check where to insert left or right

        if (node.getData().compareTo(data) > 0) {
            // data is smaller that the current node data insert in left side
            // checking whether leftNode is null
            if (node.getLeftNode() != null) {
                // if not null do the same process on left subtree repeat the same process on the left subtree
                insert(data, node.getLeftNode());
            } else {
                // left subtree is null insert the data here
                node.setLeftNode(new TreeNode<>(data, node));
            }
        } else { // repeat same in right subtree
            if (node.getRightNode() != null) {
                // if not null do the same process on right subtree repeat the same process on the left subtree
                insert(data, node.getRightNode());
            } else {
                // left subtree is null insert the data here
                node.setRightNode(new TreeNode<>(data, node));
            }
        }
    }

    // return min value
    //
    public T getMin() {
        if (rootNode == null)
            throw new IllegalArgumentException("Binary Tree is empty");
        TreeNode currentNode = rootNode;
        while (currentNode.getLeftNode() != null) {
            currentNode = currentNode.getLeftNode();
        }
        return (T) currentNode.getData();
    }

    public T getMax() {
        if (rootNode == null)
            throw new IllegalArgumentException("Binary Tree is empty");
        TreeNode currentNode = rootNode;
        while (currentNode.getRightNode() != null) {
            currentNode = currentNode.getRightNode();
        }
        return (T) currentNode.getData();
    }

    public void traversal(){
       // inoder -- left rot right
       // preoder -- root left right
       // post order -- letf right root
        if(rootNode==null)
        {
            throw new IllegalArgumentException("Binary Tree is empty");
        }
        else
            traversal(rootNode);
    }

    private void traversal(final TreeNode<T> node) {
       // System.out.println(node+" - "); // proder
        if(node.getLeftNode()!=null)
        {
            traversal(node.getLeftNode());
        }
      //  System.out.println(node+" - "); this is inorder

        if(node.getRightNode()!=null)
        {
            traversal(node.getRightNode());
        }
        System.out.print(node+" - "); //this is post order
    }

    public void topView(final TreeNode<T> node) {
         if(rootNode!=null)
         {
             System.out.print(node+" "); //this is post order
             if(node.getRightNode()!=null)
             {
                 traversal(node.getRightNode());
             }
         }


    }
}
