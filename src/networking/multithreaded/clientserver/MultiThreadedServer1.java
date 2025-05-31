package networking.multithreaded.clientserver;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadedServer1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            System.out.println("Waiting for connections on port 2000...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accepted connection from " + socket.getRemoteSocketAddress());
                socket.setSoTimeout(20_000); // 20 seconds timeout
                executorService.submit(() -> handleClientRequests(socket));
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClientRequests(Socket socket) {
        try (
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String message;
            while ((message = serverInput.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                serverOutput.println("Echoed from server: " + message);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Client timed out: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Client IO error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}

