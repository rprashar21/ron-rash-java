package com.corejava.Generics.methods;

import java.util.ArrayList;
import java.util.List;

public class TypeInference {
   /* Type inference is a Java compiler's ability to look at each method invocation and corresponding declaration to determine the type argument
        (or arguments) that make the invocation applicable.
    The inference algorithm determines the types of the arguments and, if available, the type that the result is being assigned, or returned.
            Finally, the inference algorithm tries to find the most specific type that works with all of the arguments.
            */

    static <T> List<T> emptyList(){
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        final List<Object> objects = TypeInference.emptyList();
    }
}
