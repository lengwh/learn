JVM + GC 解析

类加载器：
metaspace:是方法区在HotSpot中的实现，与永久代最大的区别在于是使用了本地内存，并不在虚拟机内存中
永久代：存放了以下信息， 加载的类信息，常量池，静态变量，即使编译后的代码

垃圾回收算法：引用计数法(循环引用问题)  复制法  标记清除   标记整理

垃圾确定： 可达性分析  从gc root出发，进行链路遍历访问
          哪些可以作为gc root:  1.栈中引用的对象  2.类静态属性  3.方法区常量  4.native方法

JVM参数类型：标配参数 -version -help
           x参数：-Xint 解释执行 -Xcomp  -Xmixed混合模式，编译执行
           xx参数：1.Boolean类型(+表示开启 -表示关闭)如是否打印+-GCDetails    2.KV值类型(-XX:属性key=属性值value)如MetaspaceSize,MaxTeuringThreshold
                    -Xms 等价于-XX:InitialHeapSize  -Xmx 等价于-XX:MaxHeapSize
                    -Xmn  设置年轻代大小  -XX:MetaSpaceSize  元空间与java7中永久代类似，不过元空间直接使用的物理内存
                    -XX:NewRatio=2 设置年轻代和老年代占比 new:old = 1:2 (默认1：2)
                    -XX:SurvivorRation=8 设置年轻代中比例eden:from:to =8:1:1 (默认8：1：1)
                    -XX:MaxTenuringThreshold：15 （0-15）设置垃圾最大年龄，默认为15，如果为0，则不进入survivo区，直接进入老年代
                    jps & jinfo -flag MetaspaceSize 22712  jifo -flags
                    java -XX:+PrintFlagsInitial
                    java -XX:+PrintFlagsFinal =    := jvm或人为修改过

引用：
强引用：日常中最常用到的，即使oom，也不会回收
软：softReference,当内存不够用时才回收  --|
弱：不管内存够不够用，始终回收             |---主要在高速缓存中用的比较多  mytabis
                                  ---|
虚:需要配合引用队列使用，在回收前做通知


OOM报错信息：
          oom之StackOverflowError
          Java heap space
          overhead limit exceeded  GC回收时间过长，超过98%时间回收到了不到2%的堆内存
          Direct buffer memory  写NIO程序时，堆内存使用空间少，本地内存使用过载，申请不到报oom
                                配置-XX:MaxDirectMemorySize=5m
          unable to create new native thread 应用程序创建线程过多，超过系统承载，linux默认的是1024
                                             解决：1.减少线程数量 2.修改linux配置
          Metaspace  类信息加载过多


GC7大类加载器：
           Serial
           ParNew
           Parallel
           ParallelOld
           CMS  初始标记GCRoot -> 并行标记  ->  Remark  ->  sweep 清除
           SerialOld
           G1 ：没有垃圾碎片，用户指定期望停顿时间




