package com.corejava.corejava.oops;
public class  MainEx{
    public static void main(String[] args) {
        AggregateExample aggregateExample = new AggregateExample();
        AggregateExample aggregateExample1 = new AggregateExample();
        AggregateExample aggregateExample2 = new AggregateExample();

        aggregateExample.setId(123);
        aggregateExample.setName("rohit");

        final int id1 = aggregateExample.getId();

        System.out.println(id1);//123
        System.out.println(aggregateExample1.getId());//0
        System.out.println(aggregateExample1.getName());

        System.out.println(AggregateExample.getComminId());

        Child child= new Child(100);
        child.gettAverage();
        System.out.print(child.gettAverage());

        child.setRollNo(AggregateExample.getComminId()*child.getRollNo());


    }
}
