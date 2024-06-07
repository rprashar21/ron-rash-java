package strings;

public class StringSample01 {
    public static void main(String[] args) {

        // String implements these interfaces Serializable,comparable, immutable
//        String literals are stored in the String Pool in the heap memory,
//        allowing for reuse of existing values.
//        Strings created with the new() keyword are stored in separate memory space in the heap memory.

        // get created in String Pool inside Heap Memory
        String name1 ="rohit";

        // get referenced to the existing object in spring pool
        String name2 ="rohit";

        // content check: returns true
        System.out.println("Content Check: "+name1.equals(name2));

        // reference check: returns true
        // since they both point to same object in String Pool
        System.out.println("Reference Check: "+ name1 == name2);


        //get created in Heap Memory outside String Pool
        String name3= new String("John Doe");

// this is also stored as new object in Heap Memory
        String name4=new String("John Doe");

//content check: returns true
        System.out.println("Content Check: "+ name3.equals(name4));

// reference check : returns false
// since their reference in Heap Memory is Different
        System.out.println("Reference Check "+name3 == name4);
    }
}
