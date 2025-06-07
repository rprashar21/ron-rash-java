package networking.lowlevelapis.clientserver.multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer {

    public static void main(String[] args) {

        // create a ServerSocket -- bascaill server runni g on a port

        // let create an executor service which a moder framework to handle a a pool of thread, lifecycle of those threads
        ExecutorService executorService = Executors.newCachedThreadPool(); // why cachectthread pool becoz there is queue to hold the tasks and there
        // is no fixed number of threads as in when the the tasks come they are alloted a thread;

        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("Waiting for connections... on port 2000");
            // accecpt clieny connections
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accepted connection from " + socket.getRemoteSocketAddress());
                // when there is no interaction with tany client for say 2 mins close any connections
                socket.setSoTimeout(20_0000);
                // here basically i have created a executo service and wheneever i recivec a new connection that will be handled by a new thread
                executorService.submit(()->handleClientRequests(socket));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void handleClientRequests(Socket socket) {

        try(socket;
            // when we recieve any message from the client
            BufferedReader serverINput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // when we want to send a message
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
        ){
            while (true) {
                String message = serverINput.readLine();
                if (message != null) {
                    serverOutput.println("echoed back from the server "+message);
                }
                if (message!=null && !message.equals("exit")) {
                    serverOutput.println("echoed back from the server "+message);
                    break;
                }
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
