package day08;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by lengwh on 2020-4-12.
 *
 *
 */
public class ReferenceDemo {

    public static void main(String[] args) {
        /*//强引用
        Object object = new Object();
        try {
            byte[] bytes = new byte[1024 * 1024 * 10];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(object);
        }*/

      /*  //弱引用
        Object obj = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj);
        System.out.println(reference.get());
        obj = null;
        try {
           //System.gc();
           byte[] bytes = new byte[1024 * 1024 * 10];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println("=======垃圾回收后");
            System.out.println(reference.get());
        }


        //弱引用
        Object obj2 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj2);
        obj2=null;
        System.out.println(obj2);
        System.out.println(weakReference.get());

        try {
            byte[] bytes = new byte[1024 * 1024 * 10];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println("=======垃圾回收后");
            System.out.println(obj2);
            System.out.println(weakReference.get());
        }
*/
        //虚引用
        Object obj3 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference phantomReference = new PhantomReference(obj3, referenceQueue);
        obj3=null;
        try {
            byte[] bytes = new byte[1024 * 1024 * 10];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println("=======垃圾回收后");
            System.out.println(referenceQueue.poll());

        }
    }
}
