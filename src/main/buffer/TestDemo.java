package main.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 学习NIO的缓冲区概念
 * 主要学习ByteBuffer(字节缓冲区)
 * 知识点：
 * 1.缓冲区的capacity: 容量属性，此属性决定最多容纳的数据。
 * 2.缓冲区的limit:限定位属性，默认的位置=capacity
 * 3.缓冲区的position:位置属性，默认的位置=0
 * 4.如果是字节缓冲区，每插入一个字节，position就会向后移动一位
 * 5.get()是通过当前的position位置来取值的
 * 6.get()每调用一次，position位置也会向后移动
 *
 * @author ysq
 */
public class TestDemo {

    @Test
    public void allocate() {
        //--表示此缓冲区最多能存储10个字节的数据
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println();
    }

    @Test
    public void put() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte b1 = 1;
        byte b2 = 2;
        buffer.put(b1);
        buffer.put(b2);
        buffer.putInt(9);
        System.out.println();
    }

    @Test
    public void get() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte b1 = 1;
        byte b2 = 2;
        buffer.put(b1);
        buffer.put(b2);

        //--将限定位放到当前的位置上
        buffer.limit(buffer.position());

        //--将position重置为0
        buffer.position(0);

        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());

    }

    @Test
    public void flip() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte b1 = 1;
        byte b2 = 2;
        buffer.put(b1);
        buffer.put(b2);

        //--flip()等价于：buffer.limit(buffer.position())+buffer.position(0);
        buffer.flip();

        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }

    @Test
    public void wrap() {
        //--wrap方法可以分配传入数据大小的容量。并且数据存储完毕之后，
        //--会自动调用flip方法
        ByteBuffer buffer = ByteBuffer.wrap("helloworld".getBytes());
        System.out.println();
    }

    @Test
    public void hasRemaining() {
        ByteBuffer buffer = ByteBuffer.wrap("helloworld".getBytes());
        //--hasRemaining()此方法会判断：
        //--当前position和limit位置之间是否还有元素可读
        //--如果有就返回true,没有就返回false
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

    @Test
    public void clear() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        byte b1 = 1;
        byte b2 = 2;
        byte b3 = 3;
        buffer.put(b1);
        buffer.put(b2);
        buffer.put(b3);
        //--注意，clear方法并不会真正清空缓冲区数据，
        //--而是把position置为0，达到写覆盖的效果
        //--补充：使用clear，可能会读到历史的脏数据
        //--所以为了避免此情况产生，需要调用flip方法
        buffer.clear();
        byte b4 = 4;
        buffer.put(b4);
        buffer.flip();
        System.out.println();
    }
}
