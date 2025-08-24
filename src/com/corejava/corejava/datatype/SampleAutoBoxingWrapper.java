package com.corejava.corejava.datatype;

public class SampleAutoBoxingWrapper {

    public static void main(String[] args) {

        // every primitive type have a wrapper class an it converts the primitive type to Object using the static method valueOf
          int myint=10;
        Integer integer = Integer.valueOf(myint); // autoboxing expilicitly // this is unnecessary
         int x = integer;
        int sum = x+10;
         Integer integer1 = myint; // autoboxing primtive to Object type






        Integer xx = 1 ; // behind the scenes: Integer.valueOf(1)
        Integer yy = Integer.valueOf(1);
        System.out.println(xx == yy); // true (from cache)

        Integer z = Integer.valueOf(200);
        Integer w = Integer.valueOf(200);
        System.out.println(z == w); // false (outside cache range)
        System.out.println("COntent checking "+z.equals(w));

        //Why valueOf() is Preferred:
        //Uses object pooling (Integer cache from -128 to 127)

        //Saves memory and improves performance

        //Used by the compiler during autoboxing

        // internal usasge is this

//        public static Integer valueOf(int i) {
//            if (i >= -128 && i <= 127) // default cache range
//                return IntegerCache.cache[i + 128];
//            else
//                return new Integer(i);
//        }


    }
}
