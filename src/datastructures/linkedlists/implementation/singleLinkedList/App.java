package datastructures.linkedlists.implementation.singleLinkedList;

public class App {
    public static void main(String[] args) {
        CustomLinkedList<Integer> linkedList = new CustomLinkedList<>();
        linkedList.insert(10);
        linkedList.insert(20);
        linkedList.insert(30);
//        linkedList.traverse();
//        System.out.println(linkedList.size());
//
//        CustomLinkedList<String> linkedList1 = new CustomLinkedList<>();
//        linkedList1.insert("rohit");
//        linkedList1.insert("swati");
//        linkedList1.insert("prashar");
//        linkedList1.traverse();
//        System.out.println(linkedList1.size());

        linkedList.delete(20);
        linkedList.traverse();

        CustomLinkedList<Person> personList = new CustomLinkedList<>();
        final Person person = new Person("rohit", 97);
        personList.insert(person);
        personList.insert(new Person("swati",99));
        personList.insert(new Person("pooja",96));

      //   personList.traverse();

         // remove the person
        personList.delete(person);
        personList.traverse();

    }
}
