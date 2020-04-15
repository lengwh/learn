package day04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lengwh on 2020-4-6.
 */
public class SpintockDemo {

    //原子引用线程
    AtomicReference<Thread> reference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() +"\t come in");

        while (!reference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() +"\t invoke myUnlock()");
    }
    public static void main(String[] args) {
        SpintockDemo spintockDemo = new SpintockDemo();
        synchronized (new Object()){}

        new Thread(new Runnable() {
            @Override
            public void run() {
                spintockDemo.myLock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spintockDemo.myUnlock();
            }
        },"AAA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spintockDemo.myLock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spintockDemo.myUnlock();
            }
        },"BBB").start();
    }
}
