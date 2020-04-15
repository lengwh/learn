package day02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by lengwh on 2020-4-6.
 * Integer -128 ~ 127 equals 是相等的
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("==========ABA问题产生");

        new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet(100,101);
                atomicReference.compareAndSet(101,100);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean b = atomicReference.compareAndSet(100, 101);
                System.out.println(b);
            }
        },"T2").start();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("==========ABA问题解决");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() +"第一次版本号" + atomicStampedReference.getStamp());
                boolean bb = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() +"第一次版本更新" + bb);
                System.out.println("第二次版本号" + atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(101,100,2,atomicStampedReference.getStamp()+1);
                System.out.println("第三次版本号" + atomicStampedReference.getStamp());

            }
        },"T3").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() + "第一次版本号" + atomicStampedReference.getStamp());
                boolean b = atomicStampedReference.compareAndSet(100, 101, 1, atomicStampedReference.getStamp()+1);
                System.out.println(b);
            }
        },"T4").start();


    }
}
