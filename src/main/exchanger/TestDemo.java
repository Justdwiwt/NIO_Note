package main.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 学习交换机的使用，用于两个线程间的信息的交换
 * <p>
 * 模拟场景：
 * 两个间谍通过交换机交换各自的暗号。
 * 间谍1的暗号：回眸一笑
 * 间谍2的暗号：寸草不生
 *
 * @author ysq
 */
public class TestDemo {

    public static void main(String[] args) {
        //--创建交换机对象
        Exchanger<String> ex = new Exchanger<>();

        new Thread(new Spy1(ex)).start();
        new Thread(new Spy2(ex)).start();
    }

}

class Spy1 implements Runnable {
    private Exchanger<String> ex;

    Spy1(Exchanger<String> ex) {
        this.ex = ex;
    }

    @Override
    public void run() {
        String spy1Info = "回眸一笑";
        try {
            //--通过exchange()方法将数据传给对方线程（间谍2）
            //--此外，此方法的返回值是对方线程发来的数据
            String spy2Info = ex.exchange(spy1Info);
            System.out.println("1收到2的暗号:" + spy2Info);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}

class Spy2 implements Runnable {

    private Exchanger<String> ex;

    Spy2(Exchanger<String> ex) {
        this.ex = ex;
    }

    @Override
    public void run() {
        String spy2Info = "寸草不生";
        try {
            String spy1Info = ex.exchange(spy2Info);
            System.out.println("2收到1的暗号:" + spy1Info);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}