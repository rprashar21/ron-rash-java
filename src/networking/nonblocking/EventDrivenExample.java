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

public class EventDrivenExample {

    // Event Driven architecture -- evenst react to certaiin events like button clicks or rest endpoints call or messages in the queue
    // componets are generaly independent and loosely coupled ,commucnication occurs thru events , they usuualy trigger async actions

    // Selectable Channels -- represents i/o operations like sockets ,pipe, file transfer  , they are assocaited with a Selector and
    // are key to event driven NIO programming in java -- ServerSocketChannel and SocketChannel are 2 example of these


    // What does a Selector do -- it monitors certain channels -- apps can regiter that a channel  listens  to sepecifi events--
    // so when that event occurs on that specific channel can respond and take car of that event


    //Steps
    // Create a Server Socket Channel --- why cnannel -- fast,that serversocket ,non blocking  can handle multiple connections
    // Channels have a register method that lets us register which events we want to be notified about, linking it to the current channel
    // accept a connection

    // createa a selector -- register it to listen to particaulr events -- .

    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress(1250));
            serverSocketChannel.configureBlocking(false); // making this nonblocking
            System.out.println("Event Driven Server started at port " + serverSocketChannel.getLocalAddress());
            // Create a selector -- S
            // selectionKey serve as a bridge between the channel and the program ,, when a channel necomes ready for any of its registeered events then
            // the selector  will wake up the program  and provides the corresponding selection key which has the information about the event
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // we want the main thread to reamin active so we have the while loop
            while (true) {
                selector.select(); // this method selects a set of keys whose channels are ready for IO operations. this is a blocking method by default
                Set<SelectionKey> selectionKeys = selector.selectedKeys(); // this will invoke all the selected keys

                // iterate on the selected keys
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove(); // i will remove the keys while im processing it and hence im using an iterator

                    if (key.isAcceptable()) {
                        // event that a client is waiting to be accepted was registered
                        // event is when there is a request from the client the event needs to be registered
                        SocketChannel clientChannel = serverSocketChannel.accept(); // accepted a connection with the client
                        System.out.println("Client connected " + clientChannel.getRemoteAddress());

                        // the cliet channel needs to be unblocing for other clients to be connected
                        clientChannel.configureBlocking(false);
                        // register this cleint with the selector
                        clientChannel.register(selector, SelectionKey.OP_READ);

                        //In addition to registering, I then have to react when this key gets returned as one of the selected keys
                    } else if (key.isReadable()) {
                        handleRequest(key);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to handle client requests
    private static void handleRequest(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();

        // create teh ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = clientChannel.read(buffer);

        if (read > 0) {
            buffer.flip(); // becoz the buffer was in read mode -- now changed to write mode
            byte[] message = new byte[buffer.remaining()];// read  bytes from where it left
            buffer.get(message);
            // respond to the clien that their message has beenn delivered
            String messageString = "Echo from server" + Arrays.toString(message);
            clientChannel.write(ByteBuffer.wrap(messageString.getBytes()));
        }
        if (read ==-1) {
            System.out.println("Client disconnected");
            clientChannel.close();
            key.cancel();
        }

    }
}
