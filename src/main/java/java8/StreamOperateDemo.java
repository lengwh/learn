package java8;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by lengwh on 2020-4-11.
 */
public class StreamOperateDemo {

    public static void main(String[] args) {
        /*Stream<Integer> stream = Stream.iterate(0, x -> x + 2);
        stream.limit(10).filter(x -> {
            System.out.println("====="+x);
            return x%4==0;}).forEach(System.out::println);*/
        /*new StreamOperateDemo().testFun(5, x ->{
            System.out.println(x>2);
            return x>2;});*/

        new StreamOperateDemo().testFun(5, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer>5;
            }
        });


    }

    void testFun(int a,Predicate<Integer> predicate){
        predicate.test(a);
    }
}
