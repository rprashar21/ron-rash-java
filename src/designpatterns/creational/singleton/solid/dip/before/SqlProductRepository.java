package designpatterns.creational.singleton.solid.dip.before;

import java.util.ArrayList;
import java.util.List;

public class SqlProductRepository {


    public List<ProductCatalog> getAllProducts()
    {
        // hits db and gets all the products

        return new ArrayList<>();
    }
}
