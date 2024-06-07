package com.corejava.Generics.classes;

// <T> what type of parameter you are going to pass
public class Box<T> {
    // generics do not take primitive types
    // we can create multiple type parameters
    private T type;

    public T getType() {
        return type;
    }

    public void setType(final T type) {
        this.type = type;
    }
}

// multiple type parameters

interface Pair<K,V>{
    public K getKey();
    public V getValue();
}
class OrderedPair<K,V> implements Pair<K,V>
{

    private K keyType;
    private V valueType;

    private String name;
    public OrderedPair(K keyType,V valueType,String name) {
        this.keyType=keyType;
        this.valueType=valueType;
        this.name=name;
    }

    @Override
    public K getKey() {
        return keyType;
    }

    @Override
    public V getValue() {
        return valueType;
    }

    public static void main(String[] args) {
        Pair<Integer,String> pair1 = new OrderedPair<>(1,"value1","pair1");
        Pair<Integer,String> pair2 = new OrderedPair<>(2,"value2","pair2");
        OrderedPair.compare(pair1,pair2);
        System.out.println(OrderedPair.compare(pair1,pair2));
    }

    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}
