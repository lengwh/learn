package day01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lengwh on 2020-4-2.
 * Volatile：轻量级同步机制，1.保证可见性，2.不保证原子性 3.禁止指令重排
 * 场景：1.单例模式 2. 读写锁  手写缓存  3.CAS底层源码
 */
public class Volatile {
    public static void main(String[] args) {
        /*Data data = new Data();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "=======start");
            try {
                TimeUnit.SECONDS.sleep(3);
                data.setNum(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "AAA").start();
        while (data.num == 0){

        }

        System.out.println(Thread.currentThread().getName() + "===over");*/

        new Volatile().countData();
    }


    public void countData(){
        Data data = new Data();
        for (int i = 0; i < 20; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        data.plusOne();
                        data.num2.getAndIncrement();
                    }
                }
            },"线程"+i).start();

        }
        if(Thread.activeCount()> 2){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("num====" + data.num);
        System.out.println("num2====" + data.num2);

    }
}

class Data{

    int num = 0;
    AtomicInteger num2 = new AtomicInteger();

    public void setNum(int num) {
        this.num = 20;
    }

    public void plusOne(){
        num++;
    }
}
