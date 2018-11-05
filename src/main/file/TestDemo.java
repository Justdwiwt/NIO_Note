package main.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 学习文件通道的使用。
 * 文件通道的创建要基于某种流来创建，比如文件输出流或输入流
 * 文件通道的优势之一：可以根据位置(position)灵活的操作文件
 * 优势之二：可以使用操作系统底层的 零拷贝(zero-copy)技术
 * 课后扩展：FileChannel的transferTo 和 transferFrom 底层用到零拷贝技术
 * 零拷贝技术可以减少 ：当通过网络网络传输文件时，OS底层数据的拷贝次数。
 * 还可以减少内核态和用户态的切换次数，节省cpu性能。
 * 比如Kafka框架底层传输数据时用到了这个技术
 *
 * @author admin
 */
public class TestDemo {

    @Test
    public void write() throws Exception {
        //--通过文件输出流获取文件通道，可以写出数据
        FileChannel fc = new FileOutputStream(new File("1.txt"))
                .getChannel();
        ByteBuffer data = ByteBuffer.wrap("helloworld".getBytes());
        //--写出数据到文件
        fc.write(data);

        fc.close();
    }

    @Test
    public void read() throws Exception {
        FileChannel fc = new FileInputStream(new File("1.txt"))
                .getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        fc.position(5);
        fc.read(buffer);

        System.out.println(new String(buffer.array()));

        fc.close();
    }
}
