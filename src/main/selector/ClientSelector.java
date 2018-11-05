package main.selector;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientSelector {

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    public static void main(String[] args) throws Exception {
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);

        Selector selector = Selector.open();
        client.register(selector, SelectionKey.OP_CONNECT);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isConnectable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                }
                if (key.isReadable()) {

                }
                if (key.isWritable()) {

                }
                it.remove();
            }
        }
    }

}
