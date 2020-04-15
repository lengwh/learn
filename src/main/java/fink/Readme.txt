JobManager:
    1.每个应用程序对应一个不同的JobManager
    2.jobmanager接收要执行的应用程序,包括作业图，逻辑数据流图，打包的所有类

TaskManager:每个taskmanager对应一个jvm,
            启动之后，会向资源管理器注册他的插槽，收到回复指令后，将插槽分配给jobmanager

Dispatcher:分发器  包含rest接口和web ui,用来接收应用


app  ->  dispatcher  ->  jobmanager
                               |
                               |
                          向ResourceManager申请资源  --->向 taskmanager注册slots --> 返回给jobmanager



org.apache.flink.streaming.util.serialization

        KeyedSerializationSchema The serialization schema describes how to turn a data object into a different serialized representation. Most data sinks (for example Apache Kafka) require the data to be handed to them in a specific format (for example as byte strings).
        SerializationSchema      The serialization schema describes how to turn a data object into a different serialized representation. Most data sinks (for example Apache Kafka) require the data to be handed to them in a specific format (for example as byte strings).


FLink的TypeInformation类
TypeInformation类是所有类型描述类的基类。它包括了类型的一些基本属性，并可以通过它来生成序列化器（serializer），特殊情况下还可以生成类型的比较器
基本类型：所有Java基本数据类型和对应装箱类型，加上void, String, Date, BigDecimal和 BigInteger.

基本数组和对象数组

复合类型：

Flink Java Tuples (Flink Java API的一部分): 最多25个成员，不支持null成员

Scala case 类 (包括 Scala tuples): 最多25个成员, 不支持null成员

Row: 包含任意多个字段的元组并且支持null成员

POJOs: 遵循类bean模式的类

辅助类型 (Option, Either, Lists, Maps, …)

泛型: Flink自身不会序列化泛型，而是借助Kryo进行序列化.



