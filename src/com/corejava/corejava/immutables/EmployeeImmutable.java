package com.corejava.corejava.immutables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class EmployeeImmutable {
    //An object is immutable if its state cannot be changed mmutable objects are thread-safe,
    // as they cannot be modified concurrently by multiple threads.
    // Wrapper classes are immutable String is immutable
    // Mostly used in concurrent applicatuons ,bcz their state cannot be changed by thread interference
    // fprivateinal so that this class cannot be extended
    // private scope of the variable is within this class
    // final so that the values are not re-assigned and hence we have to initialize them in the constructor
    private final Integer id;
    private final String name;
    private final Date doj; // this mutable
    private final List<String> mobile; // the reference will not change but we can chang its content so make it an immutable list
    // we are returning a new copy of the orginal list instead of givinn the original list

    private final Address address;


    // final so that the values are not re-assigned and hence we have to initialize them in the constructor
    public EmployeeImmutable(final Integer id, final String name, final Date doj, final List<String> mobile,final Address address) {
        this.id = id;
        this.name = name;
        this.doj = doj;
        this.mobile = mobile;
        this.address = address;
    }

    // only getters and no setters so that they cannot be accessed outside the class
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //    public Date getDoj() {
//        return doj;
//    }
    public Date getDoj() {
        return new Date();/// always will retun a new date
    }

    //    public List<String> getMobile() {
//        return mobile;
//    }
    public List<String> getMobile() {
        return new ArrayList<>(mobile); // the refenece will not change and we will be returning a new list
    }
    public Address getAddress()
    {
        return new Address(address.getCode(),address.getCity());
    }

    @Override
    public String toString() {
        return "EmployeeImmutable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doj=" + doj +
                ", mobile=" + mobile +
                ", address=" + address +
                '}';
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("123");

        EmployeeImmutable employeeImmutable1 =
                new EmployeeImmutable(1,
                        "rohit", new Date(),
                        Arrays.stream(new String[]{"123"}).collect(Collectors.toList()),
                        new Address(1,"bangalore"));

        EmployeeImmutable employeeImmutable =
                new EmployeeImmutable(1,
                        "rohit", new Date(),
                        Arrays.stream(new String[]{"123"}).collect(Collectors.toList()),
                        new Address(1,"bangalore"));

        employeeImmutable1.getDoj().setDate(12);  // this will allow us to modif so change in getter
        employeeImmutable1.getMobile().add("456");
        employeeImmutable1.getAddress().setCity("pune");
// same for List

        // the original contents are not changed

        System.out.println(employeeImmutable);
    }
}
