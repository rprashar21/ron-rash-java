package networking.lowlevelapis.clientserver.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SampleUdpServer {
    // upd there is no handshake between the client and server, u use when u dont need a reliable connection, when speed is essential

    // udp uses datagram which is a self conatined message, protocol depends upon ur application

    // communication between client and server is communciationless , they dont have to establish a particaulr connection to talk

    // Comparision vs TCP and UDP
    //              TCP server                  TCP client.                 UDP Server                 UPDCLient
    // class        ServerSocket                Socket                      DatagramSocket             DatagramSocket
    // method.      accept                      connect.                    send,recieve                send,recieve
    //Data trans.   InputStream/OutputStream.   InputStream/OutputStream.    DatagramPacket            DatagramPacket

    private static final int PORT =9000;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {

        // createas datagram server on port 9000
        try(DatagramSocket serverSocket = new DatagramSocket(PORT)) {

            byte[] buffer = new byte[BUFFER_SIZE]; // this is used to get data from the clinet
            System.out.println("Waiting for a Client connection on port " + PORT);

            // A server recieves a packet from the client in UDP
            DatagramPacket clientPacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(clientPacket);

            // lets get that packet // read the entire packet
            String message = new String(buffer,0,clientPacket.getLength());
            System.out.println("Client received: " + message);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
