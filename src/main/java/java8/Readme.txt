java8新特性
1.Lambda表达式


2.Stream Api 操作  可以做一些流水线式的操作，数据源不会发生改变
                   ，产生一个新流，stream不存储对象

  操作：1.创建  4种方式  Collection 集合获取
                      Arrays 中静态方法stream()
                      Stream类中静态方法 of()
                      无限流 1.Stream.iterate(起始值，一元运算操作）
                            2.Stream.gernate(供给函数)

       2.流水线式操作
       3.终止操作