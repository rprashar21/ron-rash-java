package networking.multithreaded.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) {

        // connect to the server
        // create readers for input and output message

        // keep sending message unless user inuts exit

        try (Socket socket = new Socket("localhost", 2000);
             PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner consoleInput = new Scanner(System.in);
        ) {
            System.out.println("Starting Communicating with the server...: If you wnat to exit say write exit");
            while (true) {
                String message = consoleInput.nextLine();
                clientOutput.println(message);

                String response = clientInput.readLine();
                if(response==null){
                    System.out.println("Server closed");
                    break;
                }
                System.out.println(response);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited");
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Connection error"+e.getMessage());
        }
    }
}
