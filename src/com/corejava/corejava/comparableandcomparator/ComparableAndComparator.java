package com.corejava.corejava.comparableandcomparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;



public class ComparableAndComparator {

    // Comparable                                                          Comparataor
    // single sorting sequence ie. only one property can be used          multiple sorting sequence --
    // eg student has id name -- only one can be used
    // actual class is modified                                       // doesnt affect the orginal class
    // java.lang                                                      java.util


//    The comparable interface imposes a total ordering on the objects of each class that implements it.
//    This ordering is referred to as the class's natural ordering, and the class's compareTo method is referred to as its natural comparison method.
//        Lists (and arrays) of objects that implement this interface can be sorted automatically by Collections.sort (and Arrays.sort).
//        Objects that implement this interface can be used as keys in a sorted map or as elements in a sorted set, without the need to specify a comparator

    // Comparable has compareTo(Object object)
    //Comparator is used for own natural sorting order
    // has compare(Object object1,Object object2 )

    public static void main(String[] args) {
        // Integer and String class implement Comparable interface
        List<Integer> integerList = Arrays.asList(2, 4, 5, 1);
        List<String> stringList = Arrays.asList("b", "d", "a", "c");
        List<String> weekDays = Arrays.asList("b", "d", "a", "c");
        Collections.sort(integerList); // o/p --> 1,2,4,5
        Collections.sort(stringList); // o/p --> a,b,c,d
//        Util.sortList(list);
//        Util.sortList(stringList);

        Integer integer = Integer.MIN_VALUE;

        int [] array = new int[]{2,1,5,3};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));;


        final List<Student> studentList = Arrays.asList(new Student(2, "ron"),
                new Student(1, "bae"), new Student(3, "apple"));

        // default natural sorting order
        Collections.sort(studentList);
        System.out.println(studentList);

        // customized sorting
        System.out.println("--- reverse sort based on name---");
//        Collections.sort(studentList,new StudentComparator());
//        Util.sortList(studentList);

        Collections.sort(studentList, (s1, s2) -> s1.getName().compareTo(s2.getName()));

        // comparing or sorting person that does not implemet comparable interface

        final List<Person> personList = Arrays.asList(new Person(2, "ron"),
                new Person(1, "bae"), new Person(3, "apple"));

        Collections.sort(personList);

        final List<Emp> empList = Arrays.asList(new Emp(2, "ron"),
                new Emp(1, "bae"), new Emp(3, "apple"));

        // custom sorting
        System.out.println("");
        System.out.println("Custom sorting order using comparator does not require class to implemnet Comparable interface");
        //  Collections.sort(empList, (e1, e2) -> e1.getId() < e2.getId() ? 1 : e1.getId() > e2.getId() ? -1 : 0);
        Collections.sort(empList, (e1, e2) -> e2.getName().compareTo(e1.getName()));
        // or else we can do this as well

        // here using the Comparator we can sort based on id or name
        Collections.sort(empList, new IdComparator());
        System.out.println(empList);
        // now sort based on the name
        Collections.sort(empList, new NameComparator());

        Collections.sort(empList,(e1,e2)->e2.getName().compareTo(e1.getName()));
    }

}

class IdComparator implements Comparator<Emp> {
    @Override
    public int compare(final Emp emp1, final Emp emp2) {
        // if name

        // if by id
        return Integer.compare(emp1.getId(), emp2.getId());

        //  public static int compare(int x, int y) {
        //        return (x < y) ? -1 : ((x == y) ? 0 : 1);
        //    }

    }
}

class NameComparator implements Comparator<Emp>
{
    // comparing based on the name
    @Override
    public int compare(final Emp e1, final Emp e2) {
        return e1.getName().compareTo(e2.getName());
    }
}


class Student implements Comparable<Student> {
    private int id;
    private String name;

    public Student(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object)
            return true;// they are both pointing tto the samre refernce
        if (object == null || object.getClass() != this.getClass())
            return false;

        Student student = (Student) object;
        return id == student.id && Objects.equals(name, student.name);
    }


//    @Override
//    public boolean equals(final Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        final Student student = (Student) o;
//        return id == student.id && Objects.equals(name, student.name);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(final Student student) {
//        int eid = this.id;
//        if(this.id < student.id)
//            return -1;
//        else if(this.id> student.id)
//            return 1;
//        else
//            return 0;

        // or simply write this
        return this.name.compareTo(student.getName());
    }
    //    @Override
    //    public int compareTo(final Student o) {
    //        return this.name.compareTo(o.getName());
    //    }
}

class Person implements Comparable<Person> {
    private int id;
    private String name;

    public Person(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(final Person person) {
        return -this.name.compareTo(person.name);
    }
}

class Emp {
    private int id;
    private String name;

    public Emp(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}