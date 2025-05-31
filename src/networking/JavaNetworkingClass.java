package networking;

import java.io.IOException;
import java.net.ServerSocket;

public class JavaNetworkingClass {

    // client and servver connect thru different layers of protocol
    // lowest is the network layer for netwroking communication then comes the data transport layer
    // The data transfer layer has diff proytocols for transferring data
    //              tcp(trannsmission control protocol)      vs          udp(user datagram protocol)
     //    connection oreinted,slow,high overhead,stream of data.       connectionless,unreliable,datagram,no error checking fast low overhead,
    // web brwoisng email file transfer                                 online gaming,real time data feeds ,streaming

    // tcp ip is the protocal to transfer data over a nbetwrok of ip addresses

    // Networking packages in java

    // sockets -- when using low level apis we use sockets to establish connections request and recieve
    // the client and server will have a socket

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket();){

            // lets create a serv

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
