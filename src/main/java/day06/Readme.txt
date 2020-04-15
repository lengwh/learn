阻塞队列：
Interface BlockingQueue
实现类：
ArrayBlockingQueue 由数组结构组成的有界阻塞队列

LinkedBlockingQueue 由链表结构组成的有界阻塞队列(默认值为Inter.MAX_VALUE)

SynchronousQueue  不存储元素的阻塞队列，也叫单个元素的队列

add remove 报错
offer poll 返回布尔值,offer(值,等待时间)，超过时间不等待
put take  阻塞版 一直阻塞


使用场景：生成者 消费者模式    线程池   消息中间件