package datastructures.stacks;

public class StackUsingArrayExtension<T> extends StackUsingArray{

    public StackUsingArrayExtension() {
        super();
    }
    public static void main(String[] args) {

        StackUsingArrayExtension<Integer> stackUsingArrayExtension = new StackUsingArrayExtension<>();
        stackUsingArrayExtension.push(10);
        stackUsingArrayExtension.push(20);

        System.out.println(stackUsingArrayExtension.peek());
    }
}
