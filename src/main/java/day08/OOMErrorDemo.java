package day08;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengwh on 2020-4-12.
 */
public class OOMErrorDemo {

    public static void main(String[] args) {
        //stackOverflow();
        //java_heap_space();
        //overhead_limit_exceeded();
        direct_buffer_memory();    }

    //方法栈溢出
    public static void stackOverflow(){
        stackOverflow();
    }


    public static void java_heap_space(){
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    public static void overhead_limit_exceeded(){
        int i=0;
        ArrayList<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        }catch (Throwable e){
           e.printStackTrace();
            System.out.println("==========="+i);
        }finally {

        }
    }

    public static void direct_buffer_memory(){
        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/1024/1024+"MB"));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }


    //spring 类加载  反射技术
    public void metaSpaceError(){
        int i = 0;
        while (true){
            i++;
            //Enhancer enhancer = new Enhancer();
        }
    }

    static class OOMTest{}
}


