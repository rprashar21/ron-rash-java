package designpatterns.creational.singleton.solid.lsp;

public class LiskovSubstituionPrinciple {

    //Objects should be replaced with their subtypes without affecting the correctness of the program

    // bird --> ostrich ,, ostrich and bird is a relationship but
    //

}

class Bird {
    void fly() {
        // implementation to fly
    }
}

class Ostrich extends Bird {

    void fly() {
        // or use it as an unimpleneted method --> liskovs principle says subtypes should completely be replacebale
        throw new RuntimeException("Ostrich cannot fly");
    }
}

// another problem is that

class ApiResponse {
    int statusCode;
    String message;
}

// now extend this class
class SuccessResponse extends ApiResponse {

    Object data;
}

// now lets say u get a response of 500
//ApiResponse response = new SuccessResponse();
//response.statusCode = 500;  // logically invalid — success response shouldn’t be 500

// your sublcass does not behave like the base class hence it cannot be completelt replaced
//make the base class abstract and define valid contracts for each type.

// naother example i s
//class Payment {
//    public void pay(double amount) { /* logic */ }
//    public void refund(double amount) { /* logic */ }
//}

/// / then someone adds ths
//class CashPayment extends Payment {
//    @Override
//    public void refund(double amount) {
//        throw new UnsupportedOperationException("Cash cannot be refunded online!");
//    }
//}

//List<Payment> payments = List.of(new CardPayment(), new CashPayment());
//for (Payment p : payments) {
//        p.refund(100); // Boom 💥 for CashPayment
//}
// uo have broken lsp You’ve broken LSP — because CashPayment cannot behave like a Payment in all contexts.

// what is the fix
// redsign using avstraction
interface PaymentMethod {
    void pay(double amount);

}

interface RefundablePayment extends PaymentMethod {
    void refund(double amount);
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(final double amount) {

    } /* no refund */
}

class CardPayment implements RefundablePayment {
    @Override
    public void refund(final double amount) {

    }

    @Override
    public void pay(final double amount) {

    } /* can refund */
}