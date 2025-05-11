package collections.list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corejava.corejava.equalsandhascodes.Person;

public class ListImmutable
{
    // An immutable list in Java is a List implementation
    // whose contents cannot be changed after it’s created.
    // Once you’ve constructed it, any attempt to add, remove or replace elements will throw an exception
    // (usually UnsupportedOperationException), and there’s no way to mutate its internal state
    public static void main(String[] args) {

        List<String> colors = List.of("red", "green", "blue");
// colors.add("yellow");  // throws UnsupportedOperationException

        List<String> original = Arrays.asList("a", "b"); // the original list mutable
        List<String> copy = List.copyOf(original); // copy of we have copied the original list
// copy is immutable, even if original changes\

        // Guava Implementatio
//        ImmutableList<String> guavaList = ImmutableList.of("one", "two", "three");



        // Use case
        // Configuration Parameters
        // Thread-safety by design: No locks needed for read-only acces



        PersonObj p1 = new PersonObj("Alice", 20);
        PersonObj p2 = new PersonObj("Bob",   20);
        List<PersonObj> roster = List.of(p1, p2);  // creates an unmodifiable list

// This will throw UnsupportedOperationException:
        roster.add(new PersonObj("Carol", 30));

// But this is perfectly fine, assuming PersonObj has setters:
        p1.setAge(25);
        System.out.println(roster.get(0).getAge());  // prints 25


   // lets see and example of immutbale list in cache

   // we will first create a cache

        // we are making this list immutable -- structurally it cannot change ie the length [] but we can modify the contents of the list
        // since they are mutbale ,, we should make them immutable as well
        Map<String,List<Person>> cacheEmployees  = new HashMap<>();

        // check if the employee is the cache then get it from the cache or else hit the db

        // look at the cache problem for this
        // cache.get("flat 9").clear();   // → UnsupportedOperationException



    }

}
class PersonObj{
    String name;
    int age;

    public PersonObj(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}