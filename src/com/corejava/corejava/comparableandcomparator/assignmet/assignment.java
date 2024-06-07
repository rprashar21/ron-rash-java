package com.corejava.corejava.comparableandcomparator.assignmet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class assignment {

    //  sort students based on their marks
    // s1,s2,s3,s4 -> 100,99,86,87,98
    public static void main(String[] args) {
        Studentes s1 = new Studentes(1,"rohit",100);
        Studentes s2 = new Studentes(2,"pallavi",99);
        Studentes s3 = new Studentes(3,"sanjay",86);
        Studentes s4 = new Studentes(4,"ajay",98);

        final List<Studentes> studentesList = Arrays.asList(s1, s2, s3, s4);
        // Display students who have got the highest marks

     //   Collections.sort(studentList); throws a compiler error because Student object does not implement comparable interface
     // so we now have to use default comparator
    // list sort methd take in a comparator interface -- Integer class has a compare method similar to comrataor
     studentesList.sort((studentes1, studentes2) -> Integer.compare(studentes2.getMarks(), studentes1.getMarks()));
     Collections.sort(studentesList,(studentes1, studentes2)-> Double.compare(studentes2.getMarks(), studentes1.getMarks()));
     studentesList.sort(Comparator.comparing(Studentes::getName));
     studentesList.sort((st1,st2)-> st1.getMarks()< st2.getMarks()?-1: st1.getMarks()> st2.getMarks()?1:0);
        System.out.println("this sorting is ascending"+studentesList);


      // sorting based on names in reverse order

      Collections.sort(studentesList,new MarksComparator());
        System.out.println("sorting done based on marks ie lowest to highest"+studentesList);

     Collections.sort(studentesList,new NameComparator());
        System.out.println("sorting done based name "+studentesList);
    }
}

class MarksComparator implements Comparator<Studentes>
{

    @Override
    public int compare(final Studentes s1, final Studentes s2) {
        // comparision and sorting based on marks
        // return based on names
        // return s1.getName().compareTo(s2.getName()); // natural sorting order
        // return s2.getName().compareTo(s1.getName()); // reverse sorting order
       // return s1.getMarks()>s2.getMarks()?-1: s1.getMarks()<s2.getMarks()?-1:0;
        //
        return Integer.compare(s1.getMarks(), s2.getMarks()); // sorting in increasing manner
      //  return Integer.compare(s2.getMarks(), s1.getMarks()); // sorting in decreasing manner
    }
}
class NameComparator implements Comparator<Studentes>
{
    @Override
    public int compare(Studentes s1,Studentes s2){
     return s1.getName().compareTo(s2.getName());
}
}

class Studentes {
    private static final String COLLEGE_NAME = "XYZ_ENGINEERING COLLEGE";
    private int id;
    private String name;
    private int marks;


    public Studentes(final int id, final String name, final int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        Studentes other = (Studentes) object;

        return (this.id == other.id && this.name.equals(other.name));

    }

    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getMarks() {
        return marks;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString() {
        return "Studentes{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}