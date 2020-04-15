package day07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengwh on 2020-4-9.
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"\t 办理业务");
            });
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
