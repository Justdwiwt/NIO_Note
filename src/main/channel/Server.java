package main.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(8888));
        SocketChannel sc = null;
        while (sc == null) {
            sc = server.accept();
        }
        sc.configureBlocking(false);
        ByteBuffer data = ByteBuffer.allocate(10);
        sc.read(data);
        System.out.println("服务端收到数据:" + new String(data.array()));
        while (true) ;//保持客户端一直开启
    }

}
