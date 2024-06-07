package datastructures.stacks.implementation;

class MinStack {

    private static final int DEFAULT_SIZE = 10;
    int[] array;
    int size = 0;

    public MinStack() {
        this.array = new int[DEFAULT_SIZE];
    }

    public void push(int val) {
        if (isFull()) {
            resize();
        }
        array[size++] = val;
    }

    public void pop() {

        // removing an element frrom the array [1,2,3]
        size--;
    }

    public int top() {
        if (size <= 0) {
            throw new ArrayIndexOutOfBoundsException("no element in the stack");
        }
        return array[size-1];
    }

    public int getMin() {
        int minElement = Integer.MAX_VALUE;
        ;
        for (int i = 0; i < size; i++) {
            minElement = Math.min(array[i], minElement);
        }
        return minElement;
    }

    public boolean isFull() {
        return this.size == array.length;
    }

    public void resize() {
        int[] tempArray = new int[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }



}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */