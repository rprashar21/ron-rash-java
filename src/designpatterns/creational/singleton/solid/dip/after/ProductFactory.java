package designpatterns.creational.singleton.solid.dip.after;

public class ProductFactory {

   public static ProductRepository create()
   {
      return new SqlProductRepository();
   }
}
