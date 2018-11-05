package main.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 启动两个线程：
 * 一个线程买菜
 * 另一个线程买锅
 * 等到菜和锅都买回来之后，做饭
 *
 * @author ysq
 */
public class TestDemo {

    public static void main(String[] args) throws Exception {
        //--创建一个闭锁(线程递减锁),并指定初始计数器
        CountDownLatch cdl = new CountDownLatch(2);

        new Thread(new BuyGuo(cdl)).start();
        new Thread(new BuyCai(cdl)).start();

        //--此方法会产生阻塞，阻塞放开的条件是初始计数器变为0
        cdl.await();
        System.out.println("开始做饭");
    }

}

class BuyGuo implements Runnable {
    private CountDownLatch cdl;

    BuyGuo(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("锅买回来了");
        //--此方法每调用一次，初始的计数器-1
        cdl.countDown();

    }

}

class BuyCai implements Runnable {
    private CountDownLatch cdl;

    BuyCai(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("菜买回来了");
        cdl.countDown();

    }

}
