package datastructures.queues;

public class CustomQueue02<T extends Comparable<T>> {

    private Node<T> headNode;
    private int count;

    //  10-->20--->30

    // this will not have constant time complexity
    private void enqueue(T data)
    {
        if(headNode==null)
        {
            headNode = new Node<>(data);
        }
        else {
            Node currentNode = headNode;
            while(currentNode.getNextNode()!=null)
            {
                currentNode =currentNode.getNextNode();
            }
            currentNode.setNextNode(new Node<>(data));
        }
    }
    private T dequeue()
    {
        if(headNode==null)
            return null;
        T data = headNode.getData();
        headNode= headNode.getNextNode();
        return data;
    }

    private T peek()
    {
        return headNode.getData();
    }

    public static void main(String[] args) {

        CustomQueue02<Integer> customQueue02 = new CustomQueue02();
        customQueue02.enqueue(10);
        customQueue02.enqueue(20);
        customQueue02.enqueue(30);

        System.out.println(customQueue02.peek());
        System.out.println(customQueue02.dequeue());
        System.out.println(customQueue02.dequeue());
        System.out.println(customQueue02.dequeue());


    }
}
class Node<T extends Comparable<T>>
{
    private T data;
    private Node<T> nextNode;

    public Node(final T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(final Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}
