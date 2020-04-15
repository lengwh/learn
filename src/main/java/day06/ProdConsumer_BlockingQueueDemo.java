package day06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lengwh on 2020-4-8.
 */
public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(4));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myResource.myProd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myResource.myConsumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程BB").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.setFlag(false);
    }
}


class MyResource<T>{
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        while(flag){
            int i = atomicInteger.incrementAndGet();
            String data = i + "";
            boolean offer = blockingQueue.offer(data,2, TimeUnit.SECONDS);
            if(offer){
                System.out.println(Thread.currentThread().getName()+"\t插入"+ data +"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t插入"+ data +"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("=========生成结束=======");
    }


    public void myConsumer() throws Exception{
        while(flag){
            String take = blockingQueue.poll(2, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+"\t消费"+ take +"成功");
            TimeUnit.SECONDS.sleep(1);

        }
        System.out.println("=========消费结束=======");
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }
}
