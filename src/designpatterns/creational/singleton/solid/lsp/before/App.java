package designpatterns.creational.singleton.solid.lsp.before;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new InhouseProduct();

        List<Product> productList = new ArrayList<>();

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        // so if we do not follow liskov substitution principle then the subtype will not be able to substitute
        // create interfaces or absract class whcich can be replaced completely
      for(Product p :productList)
      {
          if(p instanceof InhouseProduct)
          {
              ((InhouseProduct)p).applyExtraDiscount();
          }
      }
    }
}
