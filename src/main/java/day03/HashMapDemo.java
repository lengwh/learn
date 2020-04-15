package day03;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lengwh on 2020-4-6.
 */
public class HashMapDemo {

    public static void main(String[] args) {
        //HashMap<String, String> hashMap = new HashMap<>();
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<String,String>();


        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    hashMap.put(UUID.randomUUID().toString().substring(0,8),"aaa");
                    System.out.println(hashMap);
                }
            },"线程"+ i).start();

        }
    }
}
