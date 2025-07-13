package threads;

public class ThreadInterviewQuestions {

    public static void main(String[] args) {


        // create 2 threads and i want them to run synchronously
        // Suppose you have 2 threads. One of them prints (1,2,3…) and the other one prints (A,B,C,..).
        // How will you ensure that they run in a sequence, so that it prints (1,A,2,B…)?

        Display display = new Display();
        Thread thread1 = new Thread(()->{display.displayNUmbers();});

        Thread thread2 = new Thread(()->{display.displayAlphabets();});
          thread1.start();
          thread2.start();

    }
}
class Display{

    public void displayNUmbers(){

        for(int i=1; i<=3; i++){
            System.out.print(i);
        }
    }

    public void displayAlphabets(){
        String[] alpahbets = new String[]{"a","b","c"};
        for(int i=0; i<3; i++){
            System.out.print(alpahbets[i]);
        }
    }
}