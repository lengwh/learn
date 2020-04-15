多线程实现：
1.继承Thread
2.实现Runnable  无返回值
3.实现Callable 带返回值
4.线程池，池化技术

线程池
1.优势：减少资源消耗，便于管理
2.如何使用
3.几种？常用三个
        Executors.newFixedThreadPool 一池固定多个  执行长期的任务，性能好
        Executors.newSingleThreadExecutor 一池一个  一个任务一个任务执行场景
        Executors.newCachedThreadPool  一池n个(不够用自动扩容)  执行很多短期异步的小程序或者负载较轻的服务器
底层都是：
ThreadPoolExecutor+BlockingQueue
4.工作原理，几个重要参数  5个参数，实际7个参数
int corePoolSize,  常驻核心线程数量
int maximumPoolSize,  最大线程数目
long keepAliveTime,  多余空闲线程存活时间
TimeUnit unit,  存活时间单位
BlockingQueue<Runnable> workQueue, 任务队列，被提交但尚未被执行的任务
ThreadFactory threadFactory, 表示生成线程池中工作线程的线程工厂,用于创建线程，用默认即可
RejectedExecutionHandler handler  拒接策略，表示当队列已满，并且工作线程大于最大线程数量
当队列满时，未进入队列的任务会直接进入新开启的线程

拒绝策略（最大：最大线程数目+队列数)
1.AbortPolicy 默认拒绝策略  抛异常阻止系统正常运行
2.CallerRunsPolicy:"调用者运行机制",不抛弃任务,不抛异常,而是将任务回退给调用者线程 main
3.DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入队列尝试再次提交
4.DiscardPolicy:直接丢弃任务，不予抛出异常，如果允许丢弃，这是组好的策略