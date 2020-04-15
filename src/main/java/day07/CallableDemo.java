package day07;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lengwh on 2020-4-8.
 */
public class CallableDemo {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        new Thread(myThread1).start();
        new Thread(myThread1).start();
    }
    /*{
        FutureTask futureTask = new FutureTask<>(new MyThread2());
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();
        try {
            while(!futureTask.isDone()){

            }
            //建议futureTask.get()放在最后，因为会阻塞当前线程直至返回结果
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }*/

}

class MyThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"启动成功");

    }
}
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"启动成功");
        return 1024;
    }
}

class MyThread3 implements Runnable,Callable<Integer>{

    @Override
    public void run() {

    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}