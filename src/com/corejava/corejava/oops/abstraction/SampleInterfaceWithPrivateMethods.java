package com.corejava.corejava.oops.abstraction;

//Why Private Methods in Interfaces?
//Code Reusability and Encapsulation: Private methods in interfaces allow for code that is common to the default
// methods of the interface to be encapsulated within the interface itself. This avoids duplication and enhances maintainability.
//
//Support for Default Methods: Since Java 8, interfaces can have default methods with a body.
// Sometimes, these default methods might share common code, which can be refactored into a private method to avoid redundancy.
//
//Enhanced Interface Design: Private methods enable more sophisticated designs in interfaces,
// allowing for a clear separation between the exposed API methods and internal utility methods.



public class SampleInterfaceWithPrivateMethods {
    public static void main(String[] args) {
    ABCDE obj = new ABCDE();
    int[] evenNUmbers = {2,3,4};
        int evenNumbers = obj.addEvenNumbers(evenNUmbers);
        System.out.println(evenNumbers);
    }
}
class ABCDE implements MathOperations{

}
 interface MathOperations {

    default int addEvenNumbers(int[] numbers) {
        return add(numbers, true);
    }

    default int addOddNumbers(int[] numbers) {
        return add(numbers, false);
    }

    private int add(int[] numbers, boolean even) {
        int sum = 0;
        for (int number : numbers) {
            if (even && number % 2 == 0) {
                sum += number;
            } else if (!even && number % 2 != 0) {
                sum += number;
            }
        }
        return sum;
    }
}

//In this example, the MathOperations interface has two default methods:
// addEvenNumbers and addOddNumbers. Both of these methods utilize a common private method add that does the actual calculation. This private method is not accessible outside the interface, thus adhering to encapsulation principles. The add method takes an array of integers and a boolean flag to determine whether to sum even or odd numbers.
//
//        This design makes the interface more maintainable and the code reusable, as the common functionality is centralized in the private method.
