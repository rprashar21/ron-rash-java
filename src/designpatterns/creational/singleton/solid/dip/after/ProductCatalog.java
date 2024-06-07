package designpatterns.creational.singleton.solid.dip.after;

public class ProductCatalog {

    private ProductRepository productRepository;

    public ProductCatalog(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void getAllProducts() {

        // the problem here is that High level module directly depnds on low level module
        // clear violation of dependency inversion principle
        // hence to avoid this create a mediator
//        High level modules should not depend on low level modules
//    * both should depend on abstractions
//                * Abstraction should not depend on details detaisl should depend upon abstarctions
// lets create abstarctions like an interface for ProductRepository and remove the direct dependency an dals


        // instead of direct dependency or initialization we will do this
//        SqlProductRepository sqlProductRepository = new SqlProductRepository();
//        sqlProductRepository.getAllProducts();

        // now instead of this we
//        ProductRepository productRepository = ProductFactory.create();
//        productRepository.getAllProducts();

        // if we use spring --> spring framework will take care of injecting these dependecies
        // like below  this is called inversion of control
        // the spring framework will loook into all the dependecies and will inject it

        productRepository.getAllProducts();
    }


}
