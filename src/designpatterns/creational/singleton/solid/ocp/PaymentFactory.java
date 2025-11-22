package designpatterns.creational.singleton.solid.ocp;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {
    // map all the obecjts
    private static final Map<String,PaymentMethod> registry = new HashMap<>();
    // these will be the first things loaded
    static {
        register(new CreditCard());
        register(new DebitCard());
        // other insgest
    }

    ///  register all the types of payment methods
    public static void register(PaymentMethod paymentMethod) {
        registry.put(paymentMethod.getType().toLowerCase(), paymentMethod);
    }

    public static PaymentMethod getPayment(String type) {
        return registry.get(type.toLowerCase());
    }
}
