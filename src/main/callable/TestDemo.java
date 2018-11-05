package main.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 学习线程接口callable的使用
 * 1.可以让一个类实现Callable接口，变成线程类
 * 2.在call()方法中实现线程的处理逻辑
 * 3.call()方法是可以指定返回值类型，并且可以拿到call()的返回值
 * 4.call()方法可以抛异常
 * 5.callable线程类必须通过线程池来启动
 *
 * @author ysq
 */
public class TestDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<String> future = es.submit(new Call1());

        //--获取call()方法的返回值
        String result = future.get();
        System.out.println(result);
    }

}

class Call1 implements Callable<String> {

    @SuppressWarnings("RedundantThrows")
    @Override
    public String call() throws Exception {
        System.out.println("线程被处理");

        return "success";
    }

}
