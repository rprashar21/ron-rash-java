package datastructures.stacks;
public class CustomStack<T extends Comparable<T>> {
    private Node<T> headNode;
    private int count;

    private void push(T data) {
        if (headNode == null) {
            headNode = new Node<>(data);
        } else {
            Node oldNode = headNode;
            Node<T> newNode = new Node<>(data);
            newNode.setNextNode(oldNode);
            headNode = newNode;
        }
        count++;
    }

    private Object pop() {
        if(this.isEmpty())
        {
            System.out.println("Stack is empty ");
            return null;
        }
        Object data = headNode.getData();
        headNode = headNode.getNextNode();
        count--;
        return data;
    }

    private int size() {
        //0(1)
        return count;
    }

    private boolean isEmpty(){
        //0(1)
        return this.size()==0;
    }

    private Object topOfStack(){
        return this.headNode.getData();
    }

    public static void main(String[] args) {

        // implement stack with linkedList datastructure 10 <--- 20 <--- 30 <-- 40
        // head  //head //head

        CustomStack<String > stack = new CustomStack<>();
        stack.push("rohit");
        stack.push("swati");
        stack.push("myraa");


        System.out.println("current top of stack is "+stack.topOfStack());

        System.out.println(stack.size());

        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());

        System.out.println("current top of stack is "+stack.topOfStack());

        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
    }
}

class Node<T extends Comparable<T>> {

    private Object data;
    private Node nextNode;

    public Node(final T data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(final Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}
