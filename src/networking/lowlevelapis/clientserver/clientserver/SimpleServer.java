package networking.lowlevelapis.clientserver.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    // What is a socket -- a piece  of software which allows communication between devices -- it is an abstraction of the os that allows process
    // to send and recieve data over a netwrok using standardized protocols like TCP or UDP /

   //  ServerSocket is bascailly used on the server side to accept connections

  //    Term	        Purpose	                            Used By	        Blocking?	                Key Use
    // Socket	        Connect to server and communicate	Client	        Yes (traditional IO)	    Send/receive data with server
    // ServerSocket	    Wait for and accept connections	    Server	        Yes	                        Accept new client connections
    // Channel	     Non-blocking data transfer	            Client/Server	Can be non-blocking	        Efficient high-performance networking (NIO)

    // sockets -- when using low level apis we use sockets to establish connections request and recieve
    // the client and server will have a socket

    // A socket is like a door between two computers (or programs) so they can talk to each other over a network.
    // In Java, a socket is an object that lets your program send and receive data over TCP/IP

//    Class	                                Role
//    Socket	                        Client-side socket – connects to a server
//    ServerSocket	                    Server-side socket – listens for incoming connections
//    InputStream / OutputStream	 Used to send and receive data through the socket

    //InputStreamReader → to turn bytes into characters (text)
    //
    //BufferedReader → to read efficiently, one line at a time
    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(2000); // server started on port 200 // any request on localhost:200 will be recieved by this program
            System.out.println("Server started :: "+serverSocket.getInetAddress());

            Socket socket = serverSocket.accept(); // // Wait for client connection // this is alos a blocking call
            System.out.println("Client connected :: "+socket.getInetAddress());
            // server recives any message from the client
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // server sends message back to the client
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
            serverOutput.println("Hello Client connected is  "+socket.getInetAddress());

           // this is an infinte loop to make sure ur server is running unless said to close
            while(true){
                                           // this is a blocking call and this will wait until we recive a message from the client
                String messageFromClient = serverInput.readLine();
                System.out.println("Message "+messageFromClient);
                if(messageFromClient.equalsIgnoreCase("exit")){
                    break;
                }
                // send a response from sever
                serverOutput.println("server responds -->"+messageFromClient);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Server stopped");
        }

    }
}
