package cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceCache {

    //  immutable List<Employee>
    // when to use this Caching data that is global and immutable (or safely immutable via wrappers) across all users/objects.
    // You want your cache to survive as long as the class is loaded
    //e.g. a lookup table of country codes, configuration parameters loaded at startup, a registry of all available plugins.

    public static final Map<String, List<Employee>> cache = new HashMap<>();

    // without static keyword
    // use this for user session per-user preferences cache, a per-connection JDBC statement cache, or a per-tenant feature-flag cache.
    // public  final Map<String, List<Employee>> cache = new HashMap<>();

    ///  every user has its own session
//    public class UserSession {
//        // each session has its own cache of “recently viewed” items
//        public final Map<String, Item> recentItems = new HashMap<>();
//    }

    static {
        cache.putIfAbsent("flat 9", List.of(new Employee("rohit", 32), new Employee("swati", 28)));
        cache.putIfAbsent("flat 10", List.of(new Employee("barry", 40), new Employee("claudia", 38)));
    }


    public List<Employee> getEmployeesByDept(String deptId) {
//        return cache.computeIfAbsent(deptId, id -> {
//            List<Employee> loaded = loadFromDatabase(id);
//            // Make immutable copy so no one can modify it later
//            return List.copyOf(loaded);
//        });

        if (cache.containsKey(deptId)) {
            return cache.get(deptId);
        } else {
            List<Employee> employees = loadFromDatabase(deptId);
            cache.put(deptId, employees);

            //List.copyOf(...) produces a new, unmodifiable snapshot. Once you hand that back to callers, they can’t structurally alter it.
            return List.copyOf(employees);
        }
    }

    private List<Employee> loadFromDatabase(String deptId) {
        return List.of(new Employee("rohit", 20));
    }


    public static void main(String[] args) {
        EmployeeServiceCache employeeServiceCache = new EmployeeServiceCache();
        System.out.println(employeeServiceCache.getEmployeesByDept("flat 9"));
        System.out.println(employeeServiceCache.getEmployeesByDept("flat 10"));
        System.out.println(employeeServiceCache.getEmployeesByDept("flat 11"));
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}
