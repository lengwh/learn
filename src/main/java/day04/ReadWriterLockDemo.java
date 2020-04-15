package day04;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lengwh on 2020-4-6.
 * 读-读可共存
 * 读-写不能共存
 * 写-写不能共存
 * 写操作：原子+独占,整个过程必须完成,不允许被打断
 */
public class ReadWriterLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tmpInt = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.put(tmpInt +"",tmpInt+"");
                }
            },"线程"+i).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tmpInt = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myCache.get(tmpInt +"");
                }
            },"线程"+i).start();
        }
    }

}

class MyCache{
    private volatile Map<String,Object> map = new HashMap();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key,Object value){
        rwLock.writeLock().lock();


        System.out.println(Thread.currentThread().getName() +  "\t 正在写入" + key);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() +  "\t 写入完成" + key);
        rwLock.writeLock().unlock();
    }
    public void get(String key){
        rwLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() +  "\t 正在读取" + key);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() +  "\t 读取完成" + result);
        rwLock.readLock().unlock();
    }
    public void clear(){}


}
