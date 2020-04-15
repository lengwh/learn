package day04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lengwh on 2020-4-8.
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    shareResource.print5();
                }
            },"线程AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                shareResource.print10();
            }
        },"线程BB").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                shareResource.print15();
            }
        },"线程CC").start();
        }


    }

class ShareResource{
    private int number = 1;
    private ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
          while(number != 1){
              c1.await();
          }
          for (int i = 0; i < 5; i++) {
              System.out.println(Thread.currentThread().getName()+"\t"+i);
          }
          number=2;
            c2.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public void print10(){
        lock.lock();
        try{
            while(number != 2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void print15(){
        lock.lock();
        try{
            while(number != 3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
