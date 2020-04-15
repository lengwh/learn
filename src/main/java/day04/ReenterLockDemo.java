package day04;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lengwh on 2020-4-6.
 * 可重入锁(递归锁):同一线程外层函数获得锁之后，内层函数仍然能获取该锁的代码;
 *               在同一个线程在外层方法获得锁的时候，在进入内层方法会自动获取锁
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Phone2 phone2 = new Phone2();
        Phone2 phone3 = new Phone2();

        new Thread(phone2).start();
        new Thread(phone3).start();
    }
}

class Phone {
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getName() +"\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail()throws Exception{
        System.out.println(Thread.currentThread().getName() +"\t invoked sendEmail()");
    }
}

class Phone2 implements Runnable{

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() +"\t invoked get()");
            set();
        }finally {
           lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() +"\t invoked set()");
        }finally {
            lock.unlock();
        }
    }
}
