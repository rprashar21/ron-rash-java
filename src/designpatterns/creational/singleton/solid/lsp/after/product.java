package designpatterns.creational.singleton.solid.lsp.after;

public class product {

    protected double discount =20;

    public double getDiscount() {
        return discount;
    }
}

class InhouseProduct extends product {

    public double getDiscount() {
        this.applyExtraDiscount();
        return discount;
    }
    public void applyExtraDiscount()
    {
        discount = discount*1.5;
    }
}