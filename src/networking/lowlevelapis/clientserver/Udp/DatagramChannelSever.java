package networking.lowlevelapis.clientserver.Udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class DatagramChannelSever {

    public static final int PORT = 9000;

    // to handle asyn evenst for usdp use channesl
    // data gram channels need not be conected to send data
    public static void main(String[] args) {

        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.bind(new InetSocketAddress(PORT));

            System.out.println("Waiting for cleint to send data on port: " + PORT);

            // selector will monitp all the channels
            Selector selector = Selector.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.register(selector, SelectionKey.OP_READ);

            // ByteBuffer class will be used to read and write data thru this channel into the server
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // infinte loop to read and wirte back
            while(true){
                selector.select(); // which is a blockig call
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next(); // get the keys from teh set and then also remove the key that is why we are suing iterator
                    iterator.remove();

                    if(selectionKey.isReadable()){
                        var selectableChannel = (DatagramChannel)selectionKey.channel();
                         selectableChannel.configureBlocking(false);
                         buffer.clear();

                         selectableChannel.receive(buffer); // now i have read the datagram packet from the server
                        buffer.flip(); // flip the bufferbyte to write
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String message = new String(bytes);
                        System.out.println("Received: " + message);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
