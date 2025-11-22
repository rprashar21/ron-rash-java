package designpatterns.creational.singleton.solid.dip;

public class ShoppingMall {

    private BankCard bankCard; // depends on abstarction -- here as we disccussed u can have any no of implementation

    public ShoppingMall(BankCard bankCard) { // inject dependency which is abstrated
        this.bankCard = bankCard;
    }

    public void doPurchaseSomething(long amount){
        bankCard.doTransaction(amount);
    }

    public static void main(String[] args) {
       // DebitCard debitCard=new DebitCard();
       // CreditCard creditCard=new CreditCard();

        BankCard bankCard=new CreditCard(); // if u wnat to pay via credit or debit at runtime polymorphism u decide
        ShoppingMall shoppingMall=new ShoppingMall(bankCard);
        shoppingMall.doPurchaseSomething(5000);
    }
}
