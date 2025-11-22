package designpatterns.creational.singleton.solid.ocp;

import java.util.Scanner;

public class OCPExample {


    // open for extension closed for modification
    public static void main(String[] args) {

        OCPExample ocpExample = new OCPExample();

        System.out.println("Enter your payment method: Credit or Debit");
        Scanner scanner = new Scanner(System.in);
        String paymentType = scanner.nextLine();
        scanner.close();

        PaymentMethod thePaymentObject = PaymentFactory.getPayment(paymentType);
        if (thePaymentObject == null) {
            System.out.println("this is not valid payment method" + paymentType);
            System.out.println("Please enter a valid payment method Credit or Debit.  \n");
            return;
        }
        ocpExample.processPayment(thePaymentObject, 100);

    }

//    private static PaymentMethod getThePaymentObject(final String paymentMethod) {
//        // return a list of object based on my type
//        // this is violating the ocp principle now here we can create a factory method whicg will give us classes
//        // so baically we
//        if (paymentMethod.equals("Credit")) {
//            return new CreditCard();
//        }
//        if (paymentMethod.equals("Debit")) {
//            return new DebitCard();
//        }
//        return null;
//    }

    public void processPayment(PaymentMethod paymentMethod, int amount) {
        System.out.println("Processing payment method: " + paymentMethod.getClass().getName() + " with amount " + amount);

    }

}

interface PaymentMethod {
    void pay();

    String getType();
}

class CreditCard implements PaymentMethod {

    @Override
    public void pay() {
        // sme credit card
        System.out.println("Credit Card Payment Method");
    }

    @Override
    public String getType() {
        return "Credit";
    }
}

class DebitCard implements PaymentMethod {

    @Override
    public void pay() {
        System.out.println("DebitCard  Payment Method");
    }

    @Override
    public String getType() {
        return "Debit";
    }
}
