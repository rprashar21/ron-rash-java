package datastructures.slidingWindow.variable;

public class SampleReturn {
    public static void main(String[] args) {
        System.out.println( Hello());

    }

    private static String Hello() {

        for(int i=0;i<10;i++)
        {
            if(i==50)
            {
                System.out.println("hello if"+i);
                return "chal be";
            }
           else {
                System.out.println("world" +i);
            }
        }
        System.out.println("bye");
        return "no";
    }
}
