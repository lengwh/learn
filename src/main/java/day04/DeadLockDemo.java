package day04;

import java.util.concurrent.TimeUnit;

/**
 * Created by lengwh on 2020-4-9.
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new Resource(lockA,lockB),"ThreadAA").start();
        new Thread(new Resource(lockB,lockA),"ThreadBB").start();
    }
}

class Resource implements Runnable{

    private String lockA;
    private String lockB;

    public Resource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"当前拥有" + lockA+"\t 尝试获取"+lockA);
            try {
                synchronized (lockB){}
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
