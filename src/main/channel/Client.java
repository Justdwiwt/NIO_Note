package main.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    public static void main(String[] args) throws Exception {
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);
        client.connect(new InetSocketAddress("127.0.0.1", 8888));

        //--确保客户端连接成功后，再写出数据。避免空指针异常
        while (!client.isConnected()) {
            client.finishConnect();
        }

        ByteBuffer buffer = ByteBuffer.wrap("helloworld".getBytes());
        //--通过通道写出数据到服务端
        client.write(buffer);
        while (true) ;//保持客户端一直开启
    }

}
