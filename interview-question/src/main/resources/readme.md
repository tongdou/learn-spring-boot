# 1、请写出你知道的垃圾回收器及回收算法，并写出该垃圾回收器适用什么样业务场景或系统类型。
## JVM内存模型： 

| |堆|栈|方法区|
|----|----|-----|-----|
|是否共享|线程共享|线程私有|线程共享|
|跑出的错误|----|StackOverflowError|-----|
|存放内容|存放对象实例|方法调用|类信息, 常量, 静态变量|
|--|----|-----|-----|


堆

1.1 新生代(朝生夕死)
- eden
- Survivor0,Survivor1

1.2 老年代(对象的存活率较高)

**注意**：

- jdk7 版本的改动是把字符串常量池移到了堆中
- 内存模型：http://www.h3399.cn/201804/565931.html 
- images/内存模型.png;images/堆：新生代（eden+survivor）+老年代.png

## 垃圾回收
1.哪些内存需要回收

1.1 引用计数算法
- 引用计数算法给对象中添加一个引用计数器，每当一个地方引用它时，计数器值加1；当引用失效时，计数器值减1
- 然而Java虚拟机中并**没有选用**计数算法来管理内存
- 因为引用计数算法难以解决对象之间相互循环引用的问题

1.2 可达性分析算法
- 可达性分析算法是将一系列称为“GC Roots”的对象作为起始节点，从这些节点开始向下搜索，搜索所走过的路径称为引用链，当一个对象到GC Roots没有任何引用链相连时，则证明此对象是不可用的（也死去了）
- GC Roots对象的有：虚拟机栈（栈帧中本地变量表）中引用的对象，方法去中静态属性引用的对象，方法区中常量引用的对象，本地方法栈中引用的对象。

2.垃圾收集算法

2.1 复制算法（标记-复制算法）
- 避免了内存碎片的额问题
- 内存空间的利用率不高

2.2 标记-清除算法（Mark-Sweep算法）
- 一个是效率问题，标记和清除两个过程的效率都不高；
- 一个是空间问题，标记清除后会产生大量的内存碎片。

2.3 标记-整理算法
- 首先也是标记所有需要回收的对象
- 将存活的对象都向一端移动
- 然后直接清理掉端边界以外的内存

2.4 分代收集算法
- 新生代:大批对象死去的特点，选择复制算法
- 老年代:对象存活率高的特点，使用“标记-清除”或“标记-整理”算法进行回收

3. 垃圾收集器(垃圾收集器就是垃圾回收算法的具体实现了)(JDK 1.7 HotSpot虚拟机)

|     |Serial(串行收集器)|ParNew收集器（ParNew收集器是Serial收集器的多线程版本）|CMS(Concurrent Mark Sweep)收集器|G1收集器|
|----|----|-----|-----|-----|
|新生代|Serial收集器-单线程-复制算法|ParNew收集器-多线程-复制算法|-----|不了解|
|老年代|Serial Old收集器-单线程-“标记-整理算法”|ParNew收集器-多线程-“标记-整理算法”|CMS-多线程-“标记-清除”|不了解|

注意：
- 垃圾回收：http://www.h3399.cn/201703/65023.html
- Full GC == Major GC指的是对老年代/永久代的stop the world的GC
- Full GC的次数 = 老年代GC时 stop the world的次数
- Full GC的时间 = 老年代GC时 stop the world的总时间

/export/servers/jdk1.6.0_25/bin/java -Djava.util.logging.config.file=/export/Instances/vc3_afs_vc/server1/conf/logging.properties -Djava.library.path=/usr/local/lib -server -Xms4096m -Xmx4096m -XX:MaxPermSize=1024m -XX:+UnlockExperimentalVMOptions -Djava.awt.headless=true -Dsun.net.client.defaultConnectTimeout=60000 -Dsun.net.client.defaultReadTimeout=60000 -Djmagick.systemclassloader=no -Dnetworkaddress.cache.ttl=300 -Dsun.net.inetaddr.ttl=300 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/export/Instances/vc3_afs_vc/server1/logs -XX:ErrorFile=/export/Instances/vc3_afs_vc/server1/logs/java_error_%p.log -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.endorsed.dirs=/export/servers/tomcat6.0.33/endorsed -classpath /export/servers/tomcat6.0.33/bin/bootstrap.jar -Dcatalina.base=/export/Instances/vc3_afs_vc/server1 -Dcatalina.home=/export/servers/tomcat6.0.33 -Djava.io.tmpdir=/export/Instances/vc3_afs_vc/server1/temp org.apache.catalina.startup.Bootstrap -config /export/Instances/vc3_afs_vc/server1/conf/server.xml start


###2.请写出你了解的并发模型模式(例如生产者费者模式)，并说明哪些模型在项目中使用了。

###3、一个主线程A创建了多个子线程C1、C2、C3...主线程A 等待所有的子线程C1、C2、C3..执行完成后才继续执行，请写出Java 都有哪些实现方式。

###4、请写出你了解的系统间的通信方式及其特点和在项目中应用了哪些通信方式。

###5、请写出HashMap 的数据结构及ConcurrentHashMap 相对HashMap 做了哪些改动。

###6、简述Java 的类加载机制，并回答一个JVM 中可否存在两个相同的类。

7、请写出你使用过的开源框架及对它们的掌握程度。

8、Java 类C有两个公有方法M1、M2，这两个方法都配置了切面，其中方法M1中调用了M2,请回答调用方法M1时，两个切面的执行情况。

9、请写出关系型数据库索引的类型及存储的数据结构。

10、关系型数据库中表T有4个字段A、B、C、D，两个索引，单列索引indx1(B字段)，组合索引idx2[A+B+C 字段)，查询语句Select * From T Where B=? and A=?and C=? 会使用哪个索引? 并说明理由。

11、关系型数据库锁的类型及粒度。

12、关系型数据库的事务隔离级别。

13、关系型数据库的优化方案。

14、Spring事务传播行为类型及其实现原理。

15、分片结构的数据库，动态增加分片的解决方案(提示:数据如何迁移)。

16、简述分布式事务原理。

17、1T的日志文件，在一台物理机上，用Java写个程序，统计文件中每条日志的重复次数。

18、用Java实现一个LRU(最近最少使用)策略的缓存。

19、linux 常用命令
(1)网络命令:
(2)文件\文本处理命令:
(3)定时:
(4)磁盘命令:

20.简述你了解的设计原则及其含义。

21、简述你了解的设计模式及在项目中那些地方应用了。

22、简述CAP定理、Base理论。

23、请设计一个抢票系统，能够做到先到先得，总数量可配置，抢购记录需要做领域对象模型、核心模块划分、部署模型。持久化。至少产出如下工件：领域对象模型、核心模块划分、部署模型。




