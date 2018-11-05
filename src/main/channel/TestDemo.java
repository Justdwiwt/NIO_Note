package main.channel;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestDemo {

    @Test
    public void serverChannel() throws Exception {
        //--获取服务端通道
        ServerSocketChannel server = ServerSocketChannel.open();
        //--false表示非阻塞策略，默认是true，即阻塞策略
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(8888));

        SocketChannel sc = null;
        //--确保sc对象非null。
        while (sc == null) {
            sc = server.accept();
        }
        //--将链接好胡客户端通道设置为非阻塞模式
        sc.configureBlocking(false);

        System.out.println("hello");

        ByteBuffer buffer = ByteBuffer.allocate(10);
        //--当设置为非阻塞之后，即使对端没有发送数据，也不会产生阻塞
        sc.read(buffer);
        System.out.println("read");

    }

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    @Test
    public void SocketChannel() throws Exception {
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);
        client.connect(new InetSocketAddress("127.0.0.1", 8888));
        System.out.println("hello");
        while (true) ;
    }
}
