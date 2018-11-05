package main.queue;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 学习阻塞队列的使用
 * 队列的作用1：消峰限流
 * 作用2：实现生成者和消费者的解耦
 * <p>
 * 知识点：
 * 1.BlockingQueue是一个接口，常用的子类：ArrayBlockingQueue
 * LinkedBlockingQueue
 * 2.ArrayBlockingQueue底层的内部实现是数组结构，而且是有界的。
 * 3.LinkedBlockingQueue底层的内部实现是链表，增删快。
 * 而且在创建时，不需要指定队列的上限，默认大小Integer.MaxValue
 * 注:生产和消费方法都是一致的。
 * <p>
 * 4.使用阻塞队列最大的好处是：程序员不需要担心会带来线程并发的安全问题。
 * 因为底层所有重要的方法(生产方法和消费方法)都用到了锁机制
 *
 * @author ysq
 */
public class TestDemo {

    /*
     * add方法：当队列已满时，调用此方法会抛出queuefull异常。
     * offer方法：当队列已满时，抛出false。反之为true
     * put方法：当队列已满时，此方法会产生阻塞。重点记忆
     * offer超时超时：会产生阻塞，当指定的超时时间到达，阻塞放开。
     * 补充：put和offer超时，阻塞放开的条件是队列未满
     */
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unchecked"})
    @Test
    public void testArrayQueue_producer() throws InterruptedException {
        //--创建一个数组阻塞队列，并指定存储上限
        BlockingQueue queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        //queue.add(11);
        //System.out.println(queue.offer(11));
        //queue.put(11);
        queue.offer(11, 3000, TimeUnit.MILLISECONDS);

        System.out.println("hello");
    }

    /*
     * remove方法：当队列空时，会抛noSuchElement的异常
     * poll方法：当队列空时，会抛出null的特定值。
     * 如果队列不为空，则按FIFO（先进先出）的原则将数据取出
     * take方法：当队列空时，会产生阻塞。当队列有数据可消费时，阻塞放开
     * poll超时：当队列空时，会产生阻塞，直到超时时间到达或有数据可以消费，阻塞放开
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testArrayQueue_consumer() throws Exception {
        BlockingQueue queue = new ArrayBlockingQueue<>(10);

        //queue.remove();
        //System.out.println(queue.poll());
        //queue.take();
        queue.poll(5000, TimeUnit.MILLISECONDS);
        System.out.println("hello");
    }

    @SuppressWarnings("unused")
    public void testLinkedQueue() {
        BlockingQueue queue = new LinkedBlockingQueue<>();
    }

    @Test
    public void testPriorityQueue() throws InterruptedException {
        BlockingQueue<Student> queue = new PriorityBlockingQueue<>();
        Student s1 = new Student("tom", 100);
        Student s2 = new Student("rose", 150);
        Student s3 = new Student("jary", 80);

        queue.add(s1);
        queue.add(s2);
        queue.add(s3);

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.take());
        }
    }

}
