package main.pool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 学习线程池的使用。线程池的作用：可以避免线程的频繁创建和销毁。
 * 提高线程的复用率，从而提高性能
 *
 * @author ysq
 */
public class TestDemo {

    /*1.corePoolSize:核心线程数。最初创建线程池时，没有任何线程的。
     * 此外如果池子中的核心线程数未达到指定数量，会一直创建，直到满足数量要求为止
     * 在此过程中，即使有闲置线程，也不会复用。
     *
     *2.maximumPoolSize:最大线程数=核心线程数+临时线程数
     *补充：当核心线程满了+队列也满时，才会创建临时的线程
     *
     *3.keepAliveTime:线程的存活时间指的是临时线程的闲置的最大超时时间
     *时间一过，临时线程被销毁。
     *注：核心线程会一直存在，直到整个池子被销毁
     *
     *4.unit:时间单位
     *
     *5.workQuque:存储线程的阻塞队列。当核心线程没有闲置线程时，
     *会把请求先放到队列汇总，后续按FIFO的原则处理请求
     *6.RejectedExecutionHandler 拒绝服务器助手，用于接收处理不了的请求
     */
    @Test
    public void create() {
        ExecutorService es = new ThreadPoolExecutor(
                5,
                10,
                3000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                (r, executor) -> System.out.println("服务器已满"));
        //--通过线程池启动线程
        for (int i = 0; i < 21; i++)
            es.execute(new RunClient());
        //--关闭线程池
        //--当执行此方法之后，不允许创建新的线程以及接收新的请求了
        //--注意：此时线程池内部的线程不是马上销毁的，而是等到线程工作完成之后，才会销毁。
        es.shutdown();
    }

    /*CachedThreadPool:大池子，小队列。特点是：没有核心线程，都是临时线程
     *应用场景：可以更好的响应和满足用户的请求。不会让用户等待很久。所以适合于
     *并发请求量很大的场景。注意：适用于大量的短请求场景。
     *如果都是长请求的话，可能会导致临时线程一直不能销毁，而造成内存溢出
     *
     *FixedThreadPool：小池子，大队列。
     *特点是：都是核心线程，没有临时线程，而且内部是一个无界队列
     *应用场景：当服务器的负载较高时，为了消峰限流，可以用此线程池。
     *将处理不了的请求存放到队列里。
     */
    @SuppressWarnings("unused")
    public void testCreateFixedPool_Cached() {
        ExecutorService es = Executors.newFixedThreadPool(10);

        ExecutorService es1 = Executors.newCachedThreadPool();
    }

}

class RunClient implements Runnable {

    @Override
    public void run() {
        System.out.println("线程被处理");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

}
