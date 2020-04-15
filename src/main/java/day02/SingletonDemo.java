package day02;

/**
 * Created by lengwh on 2020-4-4.
 */
public class SingletonDemo {

    private static SingletonDemo singletonDemo;

    public SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+ "SingletonDemo 实例化");
    }

    public static SingletonDemo getInstance(){
        if(singletonDemo == null){
            singletonDemo = new SingletonDemo();
        }
        return singletonDemo;
    }

    //DCL double check lock 双端检索机制
    /**
     * 注意：DCL 不一定是线程安全，原因是有指令重排
     * singletonDemo = new SingletonDemo() 分为三步
     * 1. memory = allocate() 分配对象内存空间
     * 2. instance(memory) 初始化对象
     * 3. instance  = memory 设置singletonDemo指向分配地址，此时 singletonDemo = !null
     * 步骤2，3不存在数据依赖，可能发生对调
     * 但是指令重排只会保证串行语义执行的一致性(单线程），但并不会关心多线程间的语义一致性
     * 需要加volatile
     * @return
     */
    public static SingletonDemo getInstance2(){
        if(singletonDemo == null){
            synchronized (SingletonDemo.class) {
                if(singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
    public static void main(String[] args) {
        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
*/
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonDemo.getInstance2();
                }
            },"线程"+i).start();
        }
    }
}



