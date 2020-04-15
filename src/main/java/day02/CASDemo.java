package day02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lengwh on 2020-4-4.
 *
 * 1. CAS是什么？ 比较并交换 == compareAndSet
 * 期望值和主物理内存值进行比较，一致则覆盖（成功）,否则失败
 * 2.底层原理，和synchronized区别，unsafe类,中有unsafe.compareAndSwapInt(this, valueOffset, expect, update)
 *   自旋锁，当且仅当预期值与内存值相同时，将值修改(该操作是cpu原语，是原子操作，不允许被中断）
 * 3.CAS缺点：1.循环时间长，开销大  2. 只能保证一个共享变量的原子操作 3. 会带来ABA问题
 *
 * CAS-->  UnSafe ---> CAS底层思想 ---->  ABA ---> 原子引用更新 ---> 如何规避ABA问题
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.compareAndSet(0,2020)+"\tcurrent data:"+atomicInteger.get());
    }
}
