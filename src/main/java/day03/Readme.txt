集合不安全问题
ArrayList 不安全  例子？
-- 故障现象
java.util.ConcurrentModificationException  并发修改异常
-- 导致原因

-- 解决方案 list ,set ,map
1.改为Vector
2.Collections.synchronizedList() 使用集合工具类
3.CopyOnWriteArrayList 写时 类似于读写分离 (list,set);  map可以使用ConcurrentHashMap

额外：hashSet 底层 HashMap ,只使用了它的Key

hashMap hashTable concurrenthashmap
hashtable synchronized 修饰 全部加锁
concurrenthashmap 静态内部类segment，实现了ReentryLock