package main;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestDemo {

    @Test
    public void testArrayQueue() throws InterruptedException {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
//        queue.addAll(11);
        queue.put(11);
        System.out.println(queue.offer(11));
    }

}
