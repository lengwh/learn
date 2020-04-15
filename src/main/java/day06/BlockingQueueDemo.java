package day06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengwh on 2020-4-7.
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

        for (int i = 0; i < 5; i++) {
            try {
                boolean a = queue.offer("a", 3, TimeUnit.SECONDS);
                boolean b = queue.offer("a");
                System.out.println(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
