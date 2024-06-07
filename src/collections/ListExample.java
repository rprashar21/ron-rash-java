package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListExample {

    public static void main(String[] args) {
        List<StudentNew> studentNewList = Arrays.asList(new StudentNew("rohit",32,"C"),
                new StudentNew("rahul",33,"B"),new StudentNew("pallavi",33,"D"),new StudentNew("priya",70,"B"));

        System.out.println("ProductCatalog"+studentNewList);
        studentNewList.stream()
                .filter(studentNew -> studentNew.getCode().equalsIgnoreCase("B"))
                .forEach(studentNew -> studentNew.setCode("BB"));

        final List<StudentNew> studentNewList1 = studentNewList.stream()
                .filter(studentNew -> studentNew.getCode().equalsIgnoreCase("B"))
                .map(studentNew -> new StudentNew(studentNew.getName(), studentNew.getAge(), "BBB"))
                .collect(Collectors.toList());

        System.out.println("after"+studentNewList);
        System.out.println(studentNewList1);


        // what if a need to compare two lists by equals method are equal if the two list have Integers

        final List<String> stringList = Arrays.asList("rohit", "pranav", "rahul");
        final List<String> stringList1 = Arrays.asList("rohit", "rahul", "pranav");

        // list should maitain length, insertion order
        System.out.println("lists are equal "+stringList.equals(stringList1));

        // check for objects only if the underlying objects ovverride the equals and hashcode method
        // This is important when we use a map
        final List<StudentNew> rohitList = Arrays.asList(new StudentNew("rohit", 32, "C"));
        final List<StudentNew> rohitList2 = Arrays.asList(new StudentNew("rohit", 32, "C"));

        System.out.println("lists are equal "+rohitList.equals(rohitList2));

    }
}
