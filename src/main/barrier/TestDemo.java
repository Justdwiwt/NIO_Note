package main.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟赛马的场景。
 * 有两匹赛马（两个线程）
 * 要求是必须所有的赛马都达到栅栏前，才能一起跑
 *
 * @author ysq
 */
public class TestDemo {

    public static void main(String[] args) {
        //--创建栅栏，并分配初始计数器
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(new Horse1(barrier)).start();
        new Thread(new Horse2(barrier)).start();
    }

}

class Horse1 implements Runnable {
    private CyclicBarrier barrier;

    Horse1(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("赛马1来到栅栏前");
        try {
            //--await()方法会产生阻塞，阻塞放开条件是初始计数器变为0
            //--此外，此方法每调用一次，计数器就会-1
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {

            e.printStackTrace();
        }
        System.out.println("赛马1开始跑");

    }

}

class Horse2 implements Runnable {
    private CyclicBarrier barrier;

    Horse2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("赛马2正在拉肚子");
        try {
            Thread.sleep(3000);
            System.out.println("赛马2到达栅栏前");

            barrier.await();

            System.out.println("赛马2开始跑");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
