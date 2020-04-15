package java8;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lengwh on 2020-4-10.
 *
 * Lambda 表达式基础语法： 新操作符 ->
 * 左侧：参数列表
 * 右侧：实现方法，即实现功能
 * 语法格式一：无参数，无返回值  ()->
 * 语法格式二：有一个参数，x -> 小括号可以省略
 * 语法格式三：有多个参数，并且多条语句,有返回值  (x,y)->{return}
 * 只有一条执行语句，return 和{}可省略
 *
 * Lambda需要”函数式接口"的支持
 * 若抽象方法中只有一个抽象方法的接口，称为函数式接口 用@FunctionalInterface注解检测
 *
 * java8四大内置核心函数式接口
 * 1.Consumer<T> 消费型接口    Supplier<T>: 供给型   Function<T,R> :函数型  Predicate<T> 断言型
 *
 * 方法引用：三种语法格式
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 *
 * 构造器引用：
 * 格式： ClassName::new  有参构造时 用apply(参数列表)
 * 需要调用的构造器参数列表与函数式接口中抽象方法参数列表保持一致！
 *
 * 数组引用：Type[]::new;
 */
public class LambdaDemo {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.stream().filter((it) -> it>5).limit(1).forEach(System.out::println);
        list.stream().filter((it) -> it>5).map(integer -> integer.hashCode()).forEach(System.out::println);
    }
}
