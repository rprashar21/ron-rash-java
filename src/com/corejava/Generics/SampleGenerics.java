package com.corejava.Generics;

public class SampleGenerics<T extends Number> {

    // Generics in Java are fundamental for writing type-safe, reusable, and maintainable code
    // way to paremertize classes , methods and interfaces

    //Why Were Generics Introduced?
    // Before Java 5, collections could hold any Object, so you had to cast everything and hope it didn’t fail.

    // without generics
    // List names = new ArrayList();
    //names.add("John");
    //String name = (String) names.get(0); // Manual cast

    // with generics
    // List<String> names = new ArrayList<>();
    //String name = names.get(0); // No cast, safe


    // real time use case in collections
    // in collection su cannot pass prmitive ??

//    Map<String, Integer> scores = new HashMap<>();
//    List<User> users = new ArrayList<>();

 // Utility Methods (Generic Functions)
//Can print any type of array — String[], Integer[], User[], etc
    public <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

  // can be used in Api Response
  public class ApiResponse<T> {
      private T data;
      private String status;
      // Getters/setters
  }
  //So you can return:
    //
    //ApiResponse<User>
    //
    //ApiResponse<List<Product>>
    //
    //ApiResponse<Void>

  // public interface CrudRepository<T, ID> {
    //    T save(T entity);
    //    Optional<T> findById(ID id);
    //    List<T> findAll();
    //}

  //  Spring Data JPA uses this pattern to create generic data access layers.

  //public interface EventHandler<T extends Event> {
    //    void handle(T event);
    //}




//| Question                      | Answer                                                |
//| ----------------------------- | ----------------------------------------------------- |
//| Why use generics?             | Type safety, code reuse, cleaner APIs                 |
//| What are real-world examples? | Collections, DTOs, services, repositories             |
//| Does it improve performance?  | No runtime cost (compile-time only)                   |
//| When to avoid?                | One-off code, extremely complex chains                |
//| Trade-offs?                   | Type erasure, syntax complexity, no runtime type info |





    T value;
    SampleGenerics(T value)
    {
        this.value=value;
    }

    public void print()
    {
        System.out.println(this.value);
    }

    @Override
    public String toString() {
        return "" +
                "value=" + value ;
    }

    public static void main(String[] args) {
        SampleGenerics<Integer> generics = new SampleGenerics<>(20);
    }
}


