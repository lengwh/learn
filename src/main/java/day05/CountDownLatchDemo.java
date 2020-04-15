package day05;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lengwh on 2020-4-6.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"\t 结束");
                    countDownLatch.countDown();
                }
            },"线程"+i).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"结束");


    }
}
