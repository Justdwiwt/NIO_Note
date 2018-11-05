package main.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    public static void main(String[] args) throws Exception {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("127.0.0.1", 8888));
        ByteBuffer data = ByteBuffer.wrap("helloworld".getBytes());
        ByteBuffer buffer = ByteBuffer.allocate(9);
        client.write(data);
        client.read(buffer);
        System.out.println("客户端收到数据:" + new String(buffer.array()));

        while (true) ;//保持客户端一直开启
    }
}
