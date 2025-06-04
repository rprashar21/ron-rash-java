package networking.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient3 {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost",1250); // Connect to server on port

            // recievng message from server
            BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream()));

            // sending message from cleint to sever
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);// autoflush on The message is immediately sent through the socket.
            Scanner sc = new Scanner(System.in);
            String request;
            String response;

            do{
                System.out.println("enter message to be sent to the server ...");
                request = sc.nextLine();
                out.println(request);

                if(!request.equals("exit")){
                    System.out.println("response from server"+br.readLine());
                }
            }while(!request.equalsIgnoreCase("exit"));


        } catch (IOException e) {
            System.out.println("client errorc"+e.getLocalizedMessage());
        }finally {
            System.out.println("client disconnected");
        }
    }
}
