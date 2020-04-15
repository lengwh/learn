package day06;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengwh on 2020-4-7.
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<String>();

        for (int i = 0; i < 3; i++) {
            final int tmpInt = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(tmpInt*2);
                        queue.put(tmpInt+"");
                        System.out.println(Thread.currentThread().getName()+"\t 插入" + tmpInt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"生成线程"+i).start();
        }


        for (int i = 0; i < 3; i++) {
            final int tmpInt = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String take = queue.take();
                        System.out.println(Thread.currentThread().getName()+"\t 取出" + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"消费线程"+i).start();
        }
    }
}
