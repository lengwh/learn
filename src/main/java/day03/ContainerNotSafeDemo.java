package day03;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lengwh on 2020-4-6.
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {

        /*List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);*/

        ArrayList<String> list1 = new ArrayList<>();
        //Vector<String> list = new Vector<String>(); 1.使用Vector
        //List<String> list = Collections.synchronizedList(list1);//2.使用集合工具类
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,4));
                    System.out.println(list);

                }
            },"线程"+i).start();

        }

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("a");

    }

}
