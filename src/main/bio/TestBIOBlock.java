package main.bio;

import org.junit.Test;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 学习BIO网路通信会产生阻塞的方法
 *
 * @author ysq
 */
public class TestBIOBlock {

    /*
     * ①accept:The method blocks until a connection is made
     *
     * ②read:This method
     * blocks until input data is available
     *
     * ③write:此方法也会产生阻塞，当一端发送数据，而另一端不接受数据，
     * 当数据发送到一定量的时候也会产生阻塞。
     */
    @Test
    public void testAccept() throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(8888));

        Socket socket = server.accept();
        System.out.println("accept");
//		InputStream in=socket.getInputStream();
//		in.read();
//		System.out.println("read");
        OutputStream out = socket.getOutputStream();

        for (int i = 0; i < 100000; i++) {
            out.write("helloworld".getBytes());
            System.out.println(i);

        }


    }

    /*
     * ④The connection
     * will then block until established or an error occurs
     */
    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    @Test
    public void testConnect() throws Exception {
        Socket client = new Socket();
        client.connect(new InetSocketAddress("127.0.0.1", 8888));
        System.out.println("hello");
        while (true) ;
    }

}
