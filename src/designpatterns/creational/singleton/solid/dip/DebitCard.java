package designpatterns.creational.singleton.solid.dip;

public class DebitCard implements BankCard{

    public void doTransaction(long amount){
        System.out.println("payment using Debit card");
    }
}
