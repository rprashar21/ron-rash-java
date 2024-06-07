package designpatterns.creational.singleton.solid.dip.after;

import java.util.ArrayList;
import java.util.List;

public class SqlProductRepository implements ProductRepository{


    public List<ProductCatalog> getAllProducts()
    {
        // hits db and gets all the products

        return new ArrayList<>();
    }
}
