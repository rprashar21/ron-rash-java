package networking.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SeverSocketChannelSample {

    //   serversocket                   vs.                               serversocketchannel
    //  blokcing i/o                                                     nonblocking
    // requires one thread per client for communication            one thread can handle multiple client connection
    // less scalable                                                more scalable
    // tcp                                                      tcp or any nio capable protocalo for communcation
    // stream based ,slower ideal for 2 apps.                stream or datagram based vs much faster vs high performance


    // Servercocket channel is more complex

    // What is a Channel ?
    // A Channel is an interface , capabale of input and output communication ,
    // implenetcation are ServerSocketChannel ScoketChannel -- its is opened when we call the static open()
    // data is transferred thru these channels using Buffers and u can control the data segnentation and optimization of the I/o operations

    // BUffer type is an Abstract class, kind of data container .. temp storage for datat to push -- handles byte characters primitive type of data
    // different states if buffer are ready to ready,ready to write as well as empty and full , more memory efficient

    // Channels interact with the buffer for data transfer its read method takes the data from the connected entity and write method write back to the
    // connected entity -- using the flip method we change its state properties of buffer are   capacity limit positiin
    // scokets operate at byte level ,,raw data not character encoding ---- bytebuffer aligns with the model --best choice
    // charbuffere adds an extra encoding and that is not useful.....


    public static void main(String[] args) {

        // create a serversocket channel
        // accept connection
        // open Bytebuffer to read incoming data and send respnse from the serevr
        // use serversocketchannesl read/write methods to read and write data to and from severr

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
            serverSocketChannel.socket().bind(new InetSocketAddress(1250)); // Creates a socket address where the IP address is the wildcard address and the port number a specified value
            System.out.printf("Server is Listening on port %d\n", serverSocketChannel.socket().getLocalPort());

            while (true) {
                // keep accting client connections
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("Clinet connected %d\n" + serverSocketChannel.getLocalAddress()); // this return the address of the client server is connected to

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(buffer);
                System.out.printf("Received bytes %d\n", readBytes);

                // check if the bytes is > 0 read the bytes
                if (readBytes > 0) {
                    buffer.flip();
                    socketChannel.write(ByteBuffer.wrap("echo from the server ".getBytes()));
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);
                    }
                }
                if (readBytes == -1) {
                    socketChannel.close();
                    break;
                }

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
