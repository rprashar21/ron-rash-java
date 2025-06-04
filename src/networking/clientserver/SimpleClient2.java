package networking.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient2 {

    // this is the client trying to connect to the server
    public static void main(String[] args) throws IOException {
        // Socket object create to connect to the server

        Socket socket = null; // CONNECTED THE SEVER
        BufferedReader input = null;
        PrintWriter output = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 1250);
            // input and output                                             the sockte will recive message from the server
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("We are connected to the server --> server responds " + input.readLine());

            // i will keep this communcation ongong until i press exit
            // socket will send message to the server
            output = new PrintWriter(socket.getOutputStream(), true);

            String messageTosend;
            System.out.println("Please enter your message: ");
            while (true) {

                messageTosend = scanner.nextLine();
                output.println(messageTosend);

                if (messageTosend.equals("exit")) {
                    System.out.println("User want to close the connection");
                    break;
                }
                if (input.readLine().equals("exit")) {
                    System.out.println("Server wants to close the connection");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            input.close();
            output.close();
            scanner.close();
            socket.close();
        }


    }
}
