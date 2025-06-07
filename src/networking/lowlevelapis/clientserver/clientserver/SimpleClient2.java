package networking.lowlevelapis.clientserver.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient2 {

    // Connect to server using localhost and port -- since this a TCP connection u have to first establish a connection

    public static void main(String[] args) {

        BufferedReader messageFromServer = null;
        PrintWriter messageToServer = null;
        Scanner scanner = new Scanner(System.in);
        String message;

        try {
            // this socket objec is used on the client side
            // they use input and outpstream to send and recieve messages from the client
            Socket clientSocket = new Socket("localhost", 2000); // Connect to server using localhost and port -- since this a TCP connection
            messageFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            messageToServer = new PrintWriter(clientSocket.getOutputStream(), true);// // autoflush on The message is immediately sent through the socket.
            System.out.println("Connected to server" + messageFromServer.readLine());

            while (true) {
                System.out.println("Enter a message to server");
                message = scanner.nextLine();
                messageToServer.println(message);

                if (message.equals("exit")) {
                    System.out.println("client wants to quit");
                    break;
                }

                System.out.println(messageFromServer.readLine());

            }


        } catch (IOException e) {
            System.out.println("client errorc" + e.getLocalizedMessage());
        } finally {
            System.out.println("client disconnected");
        }
    }
}
