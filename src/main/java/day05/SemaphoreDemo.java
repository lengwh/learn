package day05;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengwh on 2020-4-7.
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread().getName()+"\t停车3s后离开");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            },"线程"+i).start();
        }

    }
}
