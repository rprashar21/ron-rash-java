package com.corejava.Generics.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericsInhertance {

    public static void main(String[] args) {
        // you can assign one object type to another, object can be assigned to integer bcz Object is integers supertype
        // is a realtionship
        Object someObject = new Object();
        Integer integerObject = new Integer(10);

        someObject=integerObject;

      // same for generics as we ll

        //
     //   public void boxTest(Box<Number> number) { /* ... */ }

      //  because Box<Integer> and Box<Double> are not subtypes of Box<Number>.

    }
}
class Box<T>{

    private  void add( T number) {
        System.out.println(number);
    }
    public void boxTest(Box<Number> numberBox)
    {
        ////  because Box<Integer> and Box<Double> are not subtypes of Box<Number>.
    }

    public static void main(String[] args) {
        Box<Number> box = new Box<>();
        box.add(new Integer(10));   // OK
        box.add(new Double(10.1));
        // this is not possible bcoz Box<Integer> and Box<Double> are not subtypes of Box<Number>.
      //  box.boxTest(Box<Integer>);
    }
}

class Normal<T>{
    private T x;
    private void add(T x){
        this.x=x;
        System.out.println("inside normal "+this.x);
    }

    public static void main(String[] args) {
        Normal<Number> normal = new Normal();
        normal.add(new Double(10.1));
    }
}