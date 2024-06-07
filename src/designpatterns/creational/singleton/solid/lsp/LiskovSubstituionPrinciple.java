package designpatterns.creational.singleton.solid.lsp;

public class LiskovSubstituionPrinciple {

    //Objects should be replaced with their subtypes without affecting the correctness of the program

    // bird --> ostrich ,, ostrich and bird is a relationship but
    //

}
class Bird{
    void fly(){
        // implementation to fly
    }
}

class Ostrich extends Bird{

    void fly()
    {
        // or use it as an unimpleneted method --> liskovs principle says subtypes should completely be replacebale
        throw new RuntimeException("Ostrich cannot fly");
    }
}

// another problem is that

