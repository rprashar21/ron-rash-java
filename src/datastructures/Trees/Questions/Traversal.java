package datastructures.Trees.Questions;

public enum Traversal {
    INORDER("inorder"),
    PREODER("pre"),
    POSTORDER("post"),
    LEVELORDER("level");

    // enums are fixed set of constants when we dont want extra objects to be create out of a class
    // they are public and static --> stati is belongs to class

    String name;

    Traversal(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
