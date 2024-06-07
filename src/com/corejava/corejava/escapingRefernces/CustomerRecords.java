package com.corejava.corejava.escapingRefernces;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomerRecords {

    private Map<String, Customer> customerRecords = new HashMap<>();

    public void addCustomer(Customer c) {
        this.customerRecords.put(c.getName(), c); // name and the customer objects
    }

    public Map<String, Customer> getCustomerRecords() {
        // make this an immutable map as to not return normal map
        //return this.customerRecords; in java 11 Map.copyOf(this.records)
        return Collections.unmodifiableMap(this.customerRecords);
    }

    // i have a method which returns the customer object

    public Customer findCustomerByName(final String name)
    {
        // escaping reference by always creating a new customer
        return new Customer(this.customerRecords.get(name));
    }

    public static void main(String[] args) {


        // escaping refernces we can get the the customer names and can modify them or clear them
        CustomerRecords customerRecords1 = new CustomerRecords();
        customerRecords1.addCustomer(new Customer("jack"));
        customerRecords1.addCustomer(new Customer("jill"));


        for (Customer c : customerRecords1.getCustomerRecords().values()) {
            System.out.println(c.getName());
        }

        // escaping refernece
        // getting hold of this map and clearing them // secutity threat

        // so now if try to make any change to the immutable map i will get an error
       // customerRecords1.getCustomerRecords().clear();

        // step 2 .. here we can get the person objet and can modify the value of the object

        Customer customerByName = customerRecords1.findCustomerByName("jack");
        customerByName.setName("jacks new name");

        // even though i cannot modify the list i can still modify the object with the above 2 statements
        for (Customer c : customerRecords1.getCustomerRecords().values()) {
            System.out.println(c.getName());
        }


        // solution to this is create a copy of the sutomer object
    }
}
