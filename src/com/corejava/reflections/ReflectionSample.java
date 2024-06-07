package com.corejava.reflections;

import java.lang.reflect.Field;

public class ReflectionSample {
    /*
    *Reflection is a powerful feature that allows a program to examine and modify its own structure and behavior at runtime.
    * It provides the ability to inspect and manipulate the runtime behavior of objects, classes, methods, and fields.
    *
    * java.lang.reflect package
    *
    *  Class: This class provides methods to examine and manipulate class metadata at runtime.

Constructor: This class represents a constructor of a class and provides methods to examine and invoke the constructor.

Field: This class represents a field of a class and provides methods to examine and modify the value of the field.

Method: This class represents a method of a class and provides methods to examine and invoke the method.
* Using Reflection, you can create instances of classes, invoke methods, and access fields, even if they are not public. Reflection can also be used to dynamically generate code at runtime, making it a very powerful tool for advanced Java developers.

However, it is important to use Reflection with care, as it can have performance implications and can also lead to security vulnerabilities if not used properly.
    * */

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        // create a new instance of the MyClass class
        MyClass obj = new MyClass();

        // get the field named "name" from the MyClass class
        Field field = MyClass.class.getDeclaredField("name");

        // make the field accessible (even if it is private)
        field.setAccessible(true);

        // get the value of the field from the object
        String value = (String) field.get(obj);
        System.out.println("Value of name field: " + value);

        // set a new value for the field
        field.set(obj, "New Name");

        // get the updated value of the field from the object
        String newValue = (String) field.get(obj);
        System.out.println("New value of name field: " + newValue);
    }
}
class MyClass {

    private String name = "Original Name";
}
 class MyController {

   // @Autowired
    private MyService myService;

    // ... other methods ...

}

 class MyService {

  //  @Autowired
    private MyRepository myRepository;

    // ... other methods ...

}

 class MyRepository {

    // ... methods ...

}
/*
* In this example, we have three classes: MyController, MyService, and MyRepository.
* These classes are part of a typical Spring application, and they are all managed by the Spring container.

The @Autowired annotation is used to mark the myService and myRepository fields as dependencies that need to be injected by the Spring container at runtime.
* When the application starts up, Spring uses Reflection to inspect the classes and their annotations, and then dynamically creates and configures the required objects.

This allows the developer to focus on writing business logic, rather than worrying about object creation and configuration.
* It also makes the application highly flexible and configurable, as the dependencies can be easily swapped or updated without requiring changes to the source code.
* */

// Drawbacks of reflection
//Performance overhead -- requires a lot of inspection and object manipulation
// Complexity -- since it introduces dynamic behaviour
// security risks
//Code obfuscation: Reflection can make it harder to obfuscate code, as it exposes the internal structure and behavior of the application at runtime.
//        This can make it easier for attackers to reverse engineer and analyze the code, potentially leading to security vulnerabilities.
