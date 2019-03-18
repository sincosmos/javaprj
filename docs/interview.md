# Java

## Java 基础

### 1. Java 常用的数据结构
1) 数组
2) Collection -> List (ArrayList, LinkedList), Set(HashSet 基于 HashMap 实现, TreeSet 基于 TreeMap),
   Queue (LinkedBlockingQueue, Executors 中使用 LinkedBlockingQueue 实现 FixedThreadPool)
3) Map -> HashMap, TreeMap, LinkedHashMap (LRU cache), WeakHashMap, ConcurrentHashMap

### 2. JVM 
1) JVM 内存模型：方法区（存放类信息、常量、静态变量，运行时的常量池也是位于方法区，Hotspot虚拟机在java 8之前，以
   永久代的形式实现方法区，在 full GC 时进行垃圾回收；java 8 开始，使用元数据取代了永久代）、堆区（存放类实例，是
   GC 的主要对象）、栈区(线程私有，存放一个个的栈帧，当线程调用一个 java 方法时，jvm 将存放相应方法信息的栈帧 push 
   到线程的方法栈)、程序计数器（线程私有，当前线程所执行的字节码指针）、本地方法栈
2) 类加载过程：一个类从被加载到虚拟机内存中开始，到卸载出内存为止，它的生命周期包括加载（找到类，并将类二进制码加载到方法区）、
   验证（验证类的正确性）、准备（为类变量分配内存并初始化）、解析（将符号引用转变为直接引用）、初始化（执行类提供的初始化代码，
   初始化类变量）、使用和卸载。加载、验证、准备、解析和初始化过程由类加载器负责。JVM 有四种类加载器，即启动类加载器、扩展类
   加载器、应用程序类加载器和用户自定义类加载器。类加载器之间应用双亲委派机制加载类。
3) GC：通过引用计数和可达性分析确定哪些是待回收的垃圾对象，采用标记-清除算法（实现简单，但会产生内存碎片）、复制算法、标记-整理算法（解
   决内存碎片问题，但是垃圾回收代价大）或分代收集算法（目前主流的 GC 策略）中的一种。
   示例：java -Xmx3550m -Xms3550m -Xmn2g -Xss128k
   解析：-Xmx 设置 jvm 最大堆内存；-Xms 设置初始堆内存，一般和最大可用内存一样；-Xmn 设置年轻代大小；-Xss 设置每个线程的栈大小

### 3. Integer Cache
为了节省内存和提高性能，部分整型对象（-128 ～ +127）在内部实现中通过使用相同的对象引用实现了缓存和重用。这种整型对象仅在自动装箱的时候才会重用，使用构造器创建的 Integer 对象会在 heap 区域新创建一个对象。

### 4. ThreadPoolExecutor
构造函数 ThreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)

### 5. HashMap put 的返回结果
如果 key 已经存在，则返回原来的 value；如果 key 不存在，返回 null

### 6.范型的原理

### 7. java.util.concurrent 包下面的集合类
ConcurrentHashMap, ArrayBlockingQueue (A bounded blocking queue backed by an array), LinkedBlockingQueue (An optionally-bounded blocking queue based on linked nodes), SynchronousQueue (A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa)

### 8. java.util.concurrent 包 CountDownLatch
A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes.

### 9. switch(String) 的原理
switch 只支持整型，类如 int, short, byte, char 将被转化为相应的 ascii 码，而 String 则用的是 hashCode() 方法返回 int 型的 hash 值，然后再使用 equals 方法进行进一步判断。

### 10. private 修饰的方法可以通过反射访问，那么 private 的意义是什么？
private 关键字并非是为了保证变量或方法的安全性，更多是为了实现面向对象编程中的 封装 特性，使得与对外功能无关的细节隐藏起来，让使用者只关注对外公布的接口，从而降低使用成本。

### 11. Java 类初始化的顺序
基类静态代码块 -> 基类静态成员变量 -> 派生类静态代码块 -> 派生类静态成员变量，基类的构造代码块 -> 基类普通成员变量 -> 基类构造函数 -> 派生类构造代码块 -> 派生类普通成员变量 -> 派生类构造函数
静态代码块和静态变量只在类第一次加载时执行或初始化，多个静态代码块按照代码出现的顺序

## 常见框架

### 1. Java 常用的数据结构
1) 数组

## 网络基础

### 1. HTTP和TCP协议
1) HTTP/S 超级文本传输协议，定义OSI七层网络模型中应用层的数据传输标准。理论上POST请求常用于向服务器发送信息，GET 请求常用于从服务器获取信息。GET请求把参数包含在 URL 中，POST通过 request body 传递参数；GET请求在 URL 中传递的参数是有长度限制的（URL 的最大长度是 2048 个字符），而POST请求的参数长度则不被限制；URL中不能包含任何非 ASCII 字符，因此 GET 请求的参数常常需要进行编码后传输。
   
2) TCP/IP 协议定义在OSI网络模型中的传输层，是一种提供可靠通信的网络传输协议。TCP 协议为了保证服务端能收接受到客户端的信息并能做出
   正确的应答而进行前两次(第一次和第二次)握手，为了保证客户端能够接收到服务端的信息并能做出正确的应答而进行后两次(第二次和第三次)握手。
   TCP三次握手是"在不可靠的信道上可靠地传输信息"这一需求导致的。
   套接字（socket）是通信的基石，是支持TCP/IP协议的网络通信的基本操作单元。它是网络通信过程中端点的抽象表示，包含进行网络通信必须的
   五种信息：连接使用的协议，本地主机的IP地址，本地进程的协议端口，远地主机的IP地址，远地进程的协议端口。
   TCP四次挥手：由于TCP连接是全双工的，因此每个方向都必须单独进行关闭。客户端A发送一个FIN，用来关闭客户A到服务器B的数据传送。服务器B收到
   这个FIN，它发回一个ACK，确认序号为收到的序号加1。服务器B关闭与客户端A的连接，发送一个FIN给客户端A。客户端A发回ACK报文确认，并将确认
   序号设置为收到序号加1。

## 数据库基础

### 1. MySQL 数据库
1) MySQL 常用的存储引擎有 MyISAM 和 InnoDB。前者不支持事务，在更新时使用表级锁，查询速度很快，适合更新少，查询多的业务场景；后者支持事务和行级锁，因此更适合并发操作和更新较多的业务场景。另外，MyISAM 不支持外键关联。
### 2. 主从数据库
1)  随着用户量的增多，我们可以将数据库的读写分离，使用主库（Master)负责写，若干个从库与主库同步更新数据，用户从从库读数据。写操作发生后，从库同步主库会有一定的延迟。在程序实现方面，可以借助 Spring AOP 组件实现写主库，读请求读从库。当只有读操作的时候，直接操作读库（从库），当在写事务（即写主库）中读时，强制走从库，即先暂停写事务，开启读（读从库），然后恢复写事务。此方案其实是使用事务传播行为为：NOT_SUPPORTS解决的。
### 3.分库分表
1）随着用户量的大幅度增多和历史数据的积累，一个 master 不能满足高并发的写需求，而全量的数据放在一个表/数据库里也会因为数据量过大导致查询性能下降，这时候我们就要考虑分库分表了。
分库主要解决单个数据库性能问题。垂直分库在微服务盛行的今天非常受欢迎。基本的思路就是按照业务模块划分出来不同的数据库，而不是像早期那样将所有的数据表都放在同一个数据库中。例如电商网站中，订单表、用户表、商品表等都分别放在不同的数据库里。不同的数据库可能位于不同的机器上。
分表一般有水平拆分和垂直拆分两种分法。水平分表就是将某个表的数据按行拆分，分别保存到不同的表中，例如 id（主键） 是 1 ～ 1000万的行在一个表，id 是 1000万 ～ 2000万的行在下一个表，依次拆分。根据表数据的大小，这些表都在同一个数据库（不建议使用），也可以位于不同的数据库中（即分库分表同时使用）。当然，实际水平分表的策略可能是通过主键或者时间等字段进行 hash 和取模后进行拆分。垂直拆分指将数据表的列拆分到不同的表中，使拆分后的每个表拥有较少的列，通常可以把较大的字段单独保存到一个表中，常用的字段和不常用的字段也分开到不同的表中。
如果是因为表多而数据多，使用垂直切分，根据业务切分成不同的库。如果是因为单张表的数据量太大，这时要用水平切分，即把表的数据按某种规则切分成多张表，甚至多个库上的多张表。 分库分表的顺序应该是先垂直分，后水平分。 因为垂直分更简单，更符合我们处理现实世界问题的方式。
分库分表后，应当尽量避免跨库 join，可以采用增加数据冗余的方式来解决该问题。
### 4.数据库缓存


