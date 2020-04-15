package day07;

import java.util.concurrent.*;

/**
 * Created by lengwh on 2020-4-9.
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 1; i <10 ; i++) {
            final int tempInt = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"\t"+tempInt+"\t 办理业务" );
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
