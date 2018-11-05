package main.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress("127.0.0.1", 8888));

        //--获取多路复用选择器
        Selector selector = Selector.open();
        //--在服务端通道上注册 accept事件。注意：最初需要注册此事件，后续才能监听到事件
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //--此方法会产生阻塞，阻塞放开的条件是客户端有某个事件发生
            selector.select();
            //--代码走到这一行，说明有事件发生，获取监听的事件集合
            Set<SelectionKey> keys = selector.selectedKeys();
            //--获取键集的迭代器
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                //--获取一个具体事件
                SelectionKey key = it.next();
                //--客户端接入事件
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    //--获取客户端通道
                    SocketChannel sc = ssc.accept();
                    //--将客户端通道设置非阻塞策略
                    sc.configureBlocking(false);
                    //--在客户端通道上注册读事件和写事件，目的是为了后续监听到相关事件的发生
                    sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    System.out.println("有客户端接入，当前的线程编号:" + Thread.currentThread().getId());
                }
                //--读就绪事件，表示服务端可以读取客户端发来的数据
                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    sc.read(buffer);
                    System.out.println("服务段收到数据:" + new String(buffer.array()) + "线程编号:" + Thread.currentThread().getId());

                }
                //--写就绪事件，表示服务端可以向客户端写数据
                if (key.isWritable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer data = ByteBuffer.wrap("hello1806".getBytes());
                    sc.write(data);
                }
                //--当此事件处理完之后，移除此事件，避免重复处理
                it.remove();
            }
        }
    }
}
