package main.bio;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    @SuppressWarnings({"InfiniteLoopStatement", "StatementWithEmptyBody"})
    public static void main(String[] args) throws Exception {
        Socket client = new Socket();
        //--客户端连接服务端
        client.connect(new InetSocketAddress("127.0.0.1", 7777));
        OutputStream out = client.getOutputStream();
        //--通过socket的输出流，将数据发送给服务端
        out.write("helloworld".getBytes());

        while (true) ;//保持客户端一直开启
    }
}
