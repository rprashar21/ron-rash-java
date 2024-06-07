package designpatterns.creational.singleton.solid.lsp.before;

public class Product {

    protected double discount =20;

    public double getDiscount() {
        return discount;
    }
}

class InhouseProduct extends Product{

    public void applyExtraDiscount()
    {
        discount = discount*1.5;
    }
}

