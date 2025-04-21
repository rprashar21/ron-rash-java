package strings;

public class JavaStringSample {


    public static void main(String[] args) {
        System.out.println("hello");

        String constantString = "Baeldung"; // string is created in a string constant pool ,,
        String anitherString ="Baeldung"; // both will be pointing to the same place
        final String another ="Baeldung"; // this is alos pointing to the same reference
        constantString="hello world"; // now constant reference is pointing to another string in constant pool
       // another="annoher"; cannot make it point to some other referenc ebecause it is final
        String newString = new String("Baeldung"); // this is created in the heap // this is not recommended
        // but can be useful when
        // This can be useful when you want to have a
        // distinct object for synchronization purposes or to ensure the string has a unique identity.
        //Control over String Interning: You can decide to intern a string explicitly
        // if you want to move it to the string pool using the intern() method.
        // This gives you control over when or if you want to pool your string.
//        String name = "rohit"; uses the string pool and can reuse instances if the string already exists in the pool.
//        String newName = new String("rohit"); always creates a new string object on the heap, regardless of the content of the string pool.
    }
}
