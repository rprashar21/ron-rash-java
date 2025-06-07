package networking.lowlevelapis.clientserver.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SimpleUdpClient2 {

    public static void main(String[] args) {

        try(DatagramSocket clientSocket = new DatagramSocket()) {

            byte[] data = "Hey bro".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(),9000);
            clientSocket.send(packet);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
