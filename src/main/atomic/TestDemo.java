package main.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo {

    //--创建原子性整形，并分配初始值为0
    //--原子型类型底层用到CAS无锁算法，即可以确保线程的并发安全，而且没有锁的开销，所以性能较高
    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);

        new Thread(new Add1Runner(cdl)).start();
        new Thread(new Add2Runner(cdl)).start();
        cdl.await();

        System.out.println(sum);
    }
}

class Add1Runner implements Runnable {

    private CountDownLatch cdl;

    Add1Runner(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {

            TestDemo.sum.getAndAdd(1);
        }
        cdl.countDown();

    }

}

class Add2Runner implements Runnable {
    private CountDownLatch cdl;

    Add2Runner(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            TestDemo.sum.getAndAdd(1);
        }
        cdl.countDown();
    }

}
