package networking.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient2 {

    //Here's a simple and complete client-side implementation using SocketChannel to connect to your NIO event-driven server.

    //This client:
    //Connects to the server at localhost:1250
    //Sends messages entered by the user via console
    //Waits for server responses (like echoed messages)
    //Closes the connection when the user types exit

    // SocketChannel.open() Opens a non-blocking client connection to a server
    public static void main(String[] args) {

        try (SocketChannel clientChannel = SocketChannel.open();) {

            // connects to the server using ip and port
            clientChannel.connect(new InetSocketAddress("localhost", 1250));

            // lets take userImput
            Scanner scanner = new Scanner(System.in);
            // byteBuffer NIO buffer to  send/recive data
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // create an infineite loop to keep the connecctio open unless exited explictly
            while (true) {
                System.out.print("Enter message (type 'exit' to quit): ");
                String message = scanner.nextLine();

                // lets send message to the server
                buffer.clear();
                buffer.put(message.getBytes());
                buffer.flip(); // change the mode from read to write
                clientChannel.write(buffer); // send the message to the server

                if (message.equals("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                // read response from the server
                buffer.clear();
                int responseFromServer = clientChannel.read(buffer);

                if (responseFromServer > 0) {
                    buffer.flip(); // change back to read mode
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String response = new String(bytes);
                    System.out.println("Server replied " + response);
                }
            }
        } catch (IOException exception) {
            System.out.println("Error ::" + exception.getMessage());
        }
    }
}
