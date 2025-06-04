package networking.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class EventDrivenExample2 {

    // Summary of steps
//    Open a ServerSocketChannel and bind to a port.
//    Configure it to non-blocking mode.
//    Create a Selector and register the server to accept events.
//    Run an infinite loop to listen for IO events:
//    If a new connection arrives (OP_ACCEPT), accept it and register for read.
//    If data arrives from a client (OP_READ), read and respond.
//    Use ByteBuffers to handle message content.
//    Handle disconnects gracefully (read == -1).

 //     | Concept                    | Description                                                        |
    //| -------------------------- | ------------------------------------------------------------------ |
    //| `ServerSocketChannel`      | Non-blocking server endpoint that accepts connections              |
    //| `SocketChannel`            | Non-blocking client connection used for reading/writing data       |
    //| `Selector`                 | Monitors multiple channels for events (accept, read, write, etc.)  |
    //| `SelectionKey`             | Represents the registration of a channel with the selector         |
    //| `OP_ACCEPT`                | Event type: new client wants to connect                            |
    //| `OP_READ`                  | Event type: client sent data                                       |
    //| `ByteBuffer`               | Used for non-blocking data transfer                                |
    //| `flip()`                   | Prepares buffer to be read from after writing to it                |
    //| `configureBlocking(false)` | Enables non-blocking mode for asynchronous event handling          |
    //| `event loop`               | Infinite loop that continuously checks and handles incoming events |

    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(1250));
            serverSocketChannel.configureBlocking(false);

            System.out.println("Event Driven Server started at port " + serverSocketChannel.getLocalAddress());

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select(); // blocks until at least one event occurs
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        System.out.println("Client connected: " + clientChannel.getRemoteAddress());

                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);

                    } else if (key.isReadable()) {
                        handleRequest(key);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void handleRequest(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int read = clientChannel.read(buffer);

        if (read > 0) {
            buffer.flip();
            byte[] message = new byte[buffer.remaining()];
            buffer.get(message);

            String messageString = "Echo from server: " + new String(message);
            clientChannel.write(ByteBuffer.wrap(messageString.getBytes()));

        } else if (read == -1) {
            System.out.println("Client disconnected");
            clientChannel.close();
            key.cancel();
        }
    }
}
