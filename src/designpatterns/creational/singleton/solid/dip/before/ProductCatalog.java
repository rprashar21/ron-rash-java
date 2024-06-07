package designpatterns.creational.singleton.solid.dip.before;

public class ProductCatalog {

    public void getAllProducts() {

        // the problem here is that High level module directly depnds on low level module
        // clear violation of dependency inversion principle
        // hence to avoid this create a mediator
//        High level modules should not depend on low level modules
//    * both should depend on abstractions
//                * Abstraction should not depend on details detaisl should depend upon abstarctions
// lets create abstarctions like an interface
        SqlProductRepository sqlProductRepository = new SqlProductRepository();
        sqlProductRepository.getAllProducts();
    }
//

}
