package main.lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestDemo {

    static String name = "李雷";
    static String gender = "男";

    public static void main(String[] args) {
        //--创建重入锁
        //--重入锁底层支持两种锁策略
        //--①公平锁策略
        //--②非公平锁策略
        //--创建重入锁时，默认用的是非公平锁的策略。优势在于吞吐量更高。
        //--补充：非公平锁的缺点可能会使得某些线程一直处于饥饿状态
        //--如果想使用公平锁策略，传入true。反之传false(默认是false)
        //--注意：使用重入锁，一定要注意锁释放的问题。
        //--要在finally代码块里释放，否则会造成死锁。
        //--使用同步代码块，锁的释放是由JVM自动来完成的。
        //--此外，同步代码块底层只有一种非公平锁策略
        ReentrantLock lock = new ReentrantLock();

        new Thread(new WriteRunner(lock)).start();
        new Thread(new ReadRunner(lock)).start();
    }
}

class WriteRunner implements Runnable {
    private ReentrantLock lock;

    WriteRunner(ReentrantLock lock) {
        this.lock = lock;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            //--上锁
            lock.lock();
            if (TestDemo.name.equals("李雷")) {
                TestDemo.name = "韩梅梅";
                TestDemo.gender = "女";
            } else {
                TestDemo.name = "李雷";
                TestDemo.gender = "男";
            }
            //--释放锁
            lock.unlock();

        }

    }

}

class ReadRunner implements Runnable {
    private ReentrantLock lock;

    ReadRunner(ReentrantLock lock) {
        this.lock = lock;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            lock.lock();
            System.out.println(TestDemo.name + ":" + TestDemo.gender);
            lock.unlock();

        }

    }

}
