package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by lengwh on 2020-4-11.
 */
public class StreamDemo {

    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.iterate(0, x -> x + 2);
        ArrayList arrayList = new ArrayList<String>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        int[] ints = {1,2,3};
        IntStream stream = Arrays.stream(ints);
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");
        Stream stream2 = arrayList.stream();
        stream3.forEach(System.out::println);
    }
}
