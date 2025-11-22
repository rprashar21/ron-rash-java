package designpatterns.creational.singleton.solid.isp;

public class InterfaceSegregationPrinciple {

    //NO client should be forced to depend on methods it does not use
    // Simple when we create interface do not overload it with many methods/functions keep it simple
    // Fat interfaces
    // low cohesion
    // empty methods

    // basically interface We break the big interface into smaller, more specific ones:


}

interface CashbackPayemnt{
    void cashbackPayWithScan();
    void cashbackPayWithScratch();
}

class Credit implements CashbackPayemnt{

    @Override
    public void cashbackPayWithScan() {

    }

    @Override
    public void cashbackPayWithScratch() {
// this credit does not have the option or abilty to pay via a scratch this is uncescesay w
        // we should create specific payment methods

    }
}

