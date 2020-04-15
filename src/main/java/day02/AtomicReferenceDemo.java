package day02;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by lengwh on 2020-4-6.
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {


    }

}
@Getter
@Setter
@ToString
class Student{
    String userName;
    int age;

    public Student(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
