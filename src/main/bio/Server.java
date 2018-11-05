package main.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(7777));

        ExecutorService es = Executors.newFixedThreadPool(10);

        while (true) {
            //--通过服务端的accept方法，监听是否有客户端接入
            //--需要注意的是：accept是一个阻塞方法。
            //--阻塞放开的条件是有客户端接入
            Socket socket = server.accept();
            //--通过线程池来启动线程
            es.execute(new ClientRunner(socket));
        }
    }

}

class ClientRunner implements Runnable {
    private Socket socket;

    ClientRunner(Socket socket) {
        this.socket = socket;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void run() {
        try {
            //--获取socket的输入流，用于读取客户端发来的数据
            InputStream in = socket.getInputStream();
            byte[] data = new byte[10];
            in.read(data);

            System.out.println("服务端收到数据:" + new String(data));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
