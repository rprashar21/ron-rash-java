package datastructures.queues;

public class CustomQueue03<T extends Comparable<T>> {

    // this is the actual implementation of a queue
    // 10-->20-->30

    // we will take 2  nodes first node and the second node
    private Node<T> firstNode;
    private Node<T> lastNode;

    private void enqueue(T data)
    {
        Node oldlastNode = this.lastNode;
        this.lastNode = new Node<>(data);
        this.lastNode.setNextNode(null);

        if(isEmpty())
        {
            this.firstNode=this.lastNode;
        }
        else {
            oldlastNode.setNextNode(lastNode);
        }

    }

    private boolean isEmpty() {
        return firstNode==null;
    }

    private T dequeu()
    {
        if(firstNode==null)
        {
            return null;
        }
        T data = firstNode.getData();
        firstNode=firstNode.getNextNode();
        return data;

    }

    public static void main(String[] args) {
        CustomQueue03<Integer> customQueue03 = new CustomQueue03<>();
        customQueue03.enqueue(10);
        customQueue03.enqueue(20);
        customQueue03.enqueue(30);

        Integer value = customQueue03.dequeu();
        System.out.println(value);
        System.out.println(customQueue03.dequeu());

    }
}
