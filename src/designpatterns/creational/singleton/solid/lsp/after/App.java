package designpatterns.creational.singleton.solid.lsp.after;

import java.util.ArrayList;
import java.util.List;

import designpatterns.creational.singleton.solid.lsp.after.product;


public class App {
    public static void main(String[] args) {

        product p1 = new product();
        product p2 = new product();
        product p3 = new InhouseProduct();

        List<product> productList = new ArrayList<>();

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        // so if we do not follow liskov substitution principle then the subtype will not be able to substitute
        // create interfaces or absract class whcich can be replaced completely
        // now the inhouse product will get extra discount and also obeys lsikov sub principle
        for (product p : productList) {
            System.out.println(p.getDiscount());
        }
    }
}