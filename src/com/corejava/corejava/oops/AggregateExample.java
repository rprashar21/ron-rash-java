package com.corejava.corejava.oops;

 public class AggregateExample {

    static int comminId=1234;

   static int getComminId()
   {
       return comminId;
   }
   static int increment()
   {
       return 2*10;
   }

    private int id ;
    private String Name;

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setName(final String name) {
        Name = name;
    }

    public int gettAverage()
    {
        System.out.println("parent");
        return 0;
    }

    public int gettAverage(int x)
    {
        return 10;
    }

     public int gettAverage(Integer x)
     {
         return 10;
     }


}

class Child extends AggregateExample{

    private int rollNo;

    public Child(final int rollNo) {
        this.rollNo = rollNo;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(final int rollNo) {
        this.rollNo = rollNo;
    }
    public int gettAverage()
    {
        System.out.println("child");
        return 12;
    }

}

