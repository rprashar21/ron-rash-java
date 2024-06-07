package designpatterns.creational.singleton.solid.dip;

public class CreditCard implements BankCard{

    public void doTransaction(long amount){
        System.out.println("payment using Credit card");
    }
}
