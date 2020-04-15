package day06;

import day04.ReenterLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lengwh on 2020-4-7.
 * 传统生成消费者模式
 *题目：一个初始值为0的变量，两个线程对其交替操作
 * 1. 线程    操作   资源类
 * 2. 判断  执行  通知
 * 3. 防止虚假唤醒机制 要用while,不能用if
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.decrement();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"CC").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.decrement();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"DD").start();
    }
}

class ShareData {
    private int number=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws Exception{

        lock.lock();
        try {
            //1.判断
            while(number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception{

        lock.lock();
        try {
            //1.判断
            while(number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
