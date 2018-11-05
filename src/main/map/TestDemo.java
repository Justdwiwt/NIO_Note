package main.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 学习并发Map的使用。
 * 知识点：
 * 1.HashMap   性能高，但是线程非并发安全
 * 2.HashTable 性能低，但是线程并发安全
 * 3.ConcurrentHashMap,引入了分段锁(桶)机制。好处是不会锁整表。
 * 所以好处是：即可以确保线程的并发安全问题，又可以提高性能。
 * 补充：默认分了16个段(segment),所以理论上，并发性能比HashTable高16倍
 * 4.并发map的操作方法同普通map一致，比如put(k,v),get(k)
 * 5.此外，其他的概念，如load factor(负载因子)都一致。默认是0.75
 * 比如：最初->4个，达到3个时，扩容到8个。
 * 6.在jdk1.8之后，ConcurrentHashMap舍弃了分段锁机制，转而用CAS来实现
 * 极大提高了并发性能。
 * 7.在jdk1.8之后，ConcurrentHashMap为了解决Hash冲突的问题，
 * 把链表结构变为红黑树结构，使得查询性能更快更稳定。查询的时间复杂度O(logn)
 *
 * @author ysq
 */
public class TestDemo {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void create() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        //--如果存储的是1000个，平均每个segment存储62.5个元素，
        //--根据负载因子的计算，则每个segment开辟128个空间
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }
        System.out.println("hello");
    }

}
