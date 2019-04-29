# Java
## Java 基础
### Integer Cache
为了节省内存和提高性能，部分整型对象（-128 ～ +127）在内部实现中通过使用相同的对象引用实现了缓存和重用。这种整型对象仅在自动装箱的时候才会重用，使用构造器创建的 Integer 对象会在 heap 区域新创建一个对象。
### 范型的原理
### switch(String) 的原理
switch 只支持整型，类如 int, short, byte, char 将被转化为相应的 ascii 码，而 String 则用的是 hashCode() 方法返回 int 型的 hash 值，然后再使用 equals 方法进行进一步判断。
### private 修饰的方法可以通过反射访问，那么 private 的意义是什么？
private 关键字并非是为了保证变量或方法的安全性，更多是为了实现面向对象编程中的 封装 特性，使得与对外功能无关的细节隐藏起来，让使用者只关注对外公布的接口，从而降低使用成本。
### Java 类初始化的顺序
基类静态代码块 -> 基类静态成员变量 -> 派生类静态代码块 -> 派生类静态成员变量，基类的构造代码块 -> 基类普通成员变量 -> 基类构造函数 -> 派生类构造代码块 -> 派生类普通成员变量 -> 派生类构造函数
静态代码块和静态变量只在类第一次加载时执行或初始化，多个静态代码块按照代码出现的顺序
## Java 数据结构/容器
### Java 常用的数据结构
1) 数组  
2) Collection -> List (ArrayList, LinkedList), Set(HashSet 基于 HashMap 实现, TreeSet 基于 TreeMap),
   Queue (LinkedBlockingQueue, Executors 中使用 LinkedBlockingQueue 实现 FixedThreadPool, ArrayDeque 常常比 LinkedList 效率高).  
3) Map -> HashMap, TreeMap, LinkedHashMap (LRU cache), WeakHashMap, ConcurrentHashMap
### HashMap put 的返回结果
如果 key 已经存在，则返回原来的 value；如果 key 不存在，返回 null

## JVM 
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
   
## Java 并发与多线程  
### ThreadPoolExecutor
构造函数 ThreadPoolExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
### java.util.concurrent 包下面的集合类
ConcurrentHashMap, ArrayBlockingQueue (A bounded blocking queue backed by an array), LinkedBlockingQueue (An optionally-bounded blocking queue based on linked nodes), SynchronousQueue (A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa)
### java.util.concurrent 包 CountDownLatch
A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes.

# 常见框架
### Spring

# 网络基础
## HTTP和TCP协议
1) HTTP/S 超级文本传输协议，定义OSI七层网络模型中应用层的数据传输标准。理论上POST请求常用于向服务器发送信息，GET 请求常用于从服务器获取信息。GET请求把参数包含在 URL 中，POST通过 request body 传递参数；GET请求在 URL 中传递的参数是有长度限制的（URL 的最大长度是 2048 个字符），而POST请求的参数长度则不被限制；URL中不能包含任何非 ASCII 字符，因此 GET 请求的参数常常需要进行编码后传输。 
2) TCP/IP 协议定义在OSI网络模型中的传输层，是一种提供可靠通信的网络传输协议。TCP 协议为了保证服务端能收接受到客户端的信息并能做出正确的应答而进行前两次(第一次和第二次)握手，为了保证客户端能够接收到服务端的信息并能做出正确的应答而进行后两次(第二次和第三次)握手。TCP三次握手是"在不可靠的信道上可靠地传输信息"这一需求导致的。套接字（socket）是通信的基石，是支持TCP/IP协议的网络通信的基本操作单元。它是网络通信过程中端点的抽象表示，包含进行网络通信必须的五种信息：连接使用的协议，本地主机的IP地址，本地进程的协议端口，远地主机的IP地址，远地进程的协议端口。TCP四次挥手：由于TCP连接是全双工的，因此每个方向都必须单独进行关闭。客户端A发送一个FIN，用来关闭客户A到服务器B的数据传送。服务器B收到这个FIN，它发回一个ACK，确认序号为收到的序号加1。服务器B关闭与客户端A的连接，发送一个FIN给客户端A。客户端A发回ACK报文确认，并将确认序号设置为收到序号加1。

# 数据存储
## MySQL 数据库
### 存储引擎与索引原理
参考资料 [MySQL索引背后的数据结构及算法原理](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)  
1) MySQL 常用的存储引擎有 MyISAM 和 InnoDB。前者不支持事务，在更新时使用表级锁，查询速度很快，适合更新少，查询多的业务场景；后者支持事务和行级锁，因此更适合并发操作和更新较多的业务场景。另外，MyISAM 不支持外键关联。  
2) B-tree is a fat tree. The height of B-Trees is kept low by putting maximum possible keys in a B-Tree node. Generally, a B-Tree node size is kept equal to the disk block size. Since h is low for B-Tree, total disk accesses for most of the operations are reduced significantly compared to balanced Binary Search Trees like AVL Tree, Red-Black Tree, ..etc.  
3) In B Tree, Keys and records both can be stored in the internal as well as leaf nodes. Whereas, in B+ tree, records (data) can only be stored on the leaf nodes while internal nodes can only store the key values.
4) The leaf nodes of a B+ tree are linked together in the form of a singly linked lists to make the search queries more efficient.  
5) 为什么 Mysql 索引用 B+ 树而不是 B 树？B 树的节点需要存储数据，而 B+ 树只有叶子结点存储数据，这样在节点大小（通常是磁盘页的大小）一定的情况下，B 树内部节点能存储的索引数量就大大少于 B+ 树内部节点存储的索引的数量，导致同样的检索，B 树很可能要读更多的页，即磁盘 IO 次数回增多，检索效率下降。另外 B+ 树的叶子节点会顺序存储下一个叶子节点的地址指针，在区间访问时，能够进一步减少磁盘 IO 次数。
6) 假设在表 A 上经常用到的两个查询语句是 select colx from A where col2=? and col1=? 和 select coly from A where col1 like 'sample%'，应该怎么建立索引比较好？可以考虑建立一个联合索引，create index composite_index_cols on A (col1, col2)。对于前者来说，SQL 解析器会自动调整两个条件的顺序，这样两个查询都能用到这个索引，加快查询效率。
7) MyISAM 的主键索引和非主键索引都是非聚簇索引，叶子节点数据区存放的都是数据地址；InnoDB 的主键索引是聚簇索引，叶子节点的数据就是数据本身，非主键索引是非聚簇索引，数据区存放的是主键。因此 MyISAM 表可以没有主键，而 InnoDB 的表如果不指定主键，则会由存储引擎生成一个全局的 rowid 序列作为主键，所有不指定主键的表共享同一个 rowid 序列，并发性能差，因此一般都需要指定主键。
8) 索引是存储引擎级别的实现，不同的存储引擎可能采用不同的索引类型和实现方式。B+ 树是大多数 mysql 存储引擎默认的索引类型。
9) 联合索引实际上是按照联合索引的第一列建立 B+ 树，非叶子节点上的 key 是第一列的值，叶子节点上保存了联合索引中所有列的值并按照第一列、第二列...依次排序。
### 事务
参考资料[CS-Notes 数据库原理](https://cyc2018.github.io/CS-Notes/#/notes/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%B3%BB%E7%BB%9F%E5%8E%9F%E7%90%86)
### 索引优化
参考资料 [mysql 优化](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)
1) mysql 查询时，如果索引列是表达式的一部分或函数的参数，将不会使用索引，例如 select * from tab where id+1=5, select * from tab where len(id) = 3
## 主从数据库
1)  随着用户量的增多，我们可以将数据库的读写分离，使用主库（Master)负责写，若干个从库与主库同步更新数据，用户从从库读数据。写操作发生后，从库同步主库会有一定的延迟。在程序实现方面，可以借助 Spring AOP 组件实现写主库，读请求读从库。当只有读操作的时候，直接操作读库（从库），当在写事务（即写主库）中读时，强制走从库，即先暂停写事务，开启读（读从库），然后恢复写事务。此方案其实是使用事务传播行为为：NOT_SUPPORTS解决的。
## 分库分表
1）随着用户量的大幅度增多和历史数据的积累，一个 master 不能满足高并发的写需求，而全量的数据放在一个表/数据库里也会因为数据量过大导致查询性能下降，这时候我们就要考虑分库分表了。
分库主要解决单个数据库性能问题。垂直分库在微服务盛行的今天非常受欢迎。基本的思路就是按照业务模块划分出来不同的数据库，而不是像早期那样将所有的数据表都放在同一个数据库中。例如电商网站中，订单表、用户表、商品表等都分别放在不同的数据库里。不同的数据库可能位于不同的机器上。
分表一般有水平拆分和垂直拆分两种分法。水平分表就是将某个表的数据按行拆分，分别保存到不同的表中，例如 id（主键） 是 1 ～ 1000万的行在一个表，id 是 1000万 ～ 2000万的行在下一个表，依次拆分。根据表数据的大小，这些表都在同一个数据库（不建议使用），也可以位于不同的数据库中（即分库分表同时使用）。当然，实际水平分表的策略可能是通过主键或者时间等字段进行 hash 和取模后进行拆分。垂直拆分指将数据表的列拆分到不同的表中，使拆分后的每个表拥有较少的列，通常可以把较大的字段单独保存到一个表中，常用的字段和不常用的字段也分开到不同的表中。
如果是因为表多而数据多，使用垂直切分，根据业务切分成不同的库。如果是因为单张表的数据量太大，这时要用水平切分，即把表的数据按某种规则切分成多张表，甚至多个库上的多张表。 分库分表的顺序应该是先垂直分，后水平分。 因为垂直分更简单，更符合我们处理现实世界问题的方式。
分库分表后，应当尽量避免跨库 join，可以采用增加数据冗余的方式来解决该问题。
## ElasticSearch
## Redis
### 基本数据结构
参考资料 [Redis 数据结构基础教程](https://juejin.im/post/5b53ee7e5188251aaa2d2e16)
1) string 是一个可变的字节数组，常见的操作有 set, get, strlen, getrange, setrange, append, ttl, expire, del. 如果存入的是整数，还可以作为计数器使用，incrby, decrby, incr, decr. 使用 setbit, getbit, bitcount, bitop (bitop and/or/xor destkey key [key...]) 命令可以将 string 类型作为 bit map 使用。
2) redis 使用双向链表结构保存列表，是以列表结构为 list。因为是双链表，list 即可作为队列(lpush/rpop, rpush/lpop)使用，也可以作为堆栈(rpush/rpop, lpush/lpop)使用。用 list 可以实现 redis 消息队列（ redis 另外提供了发布/订阅机制实现消息队列)。list 的 push 支持一次操作多个元素（实际 push 是依次一个一个进行的），pop 操作一次只能弹出一个元素。list 常见的其它操作有 llen (list length), lindex (get element by index), lrange (get elements by range of indices), lset (set element by index), linsert before/after (insert element before/after sepcified element), lrem count (if count > 0, search from head, remove count of elements; if count < 0, search from tail, remove count of elements; if count = 0, remove all elements), ltrim (维护定长列表，除了范围内的元素外，其它元素将被删除)
3) hash 等价于 java 中的 HashMap，存储的是键值对。使用 hset (hset v_name key1 value 一次加入一个键值对)或 hmset（hmset v_name key2 value2 key3 value3... 一次加入多个键值对）。hdel 支持一次删除一个或多个键。hget 获得键对应的值，hexists 判断键是否存在于 hash 中。如果键对应的值是整数，我们也可以将整数作为计数器使用，相关的命令有 hincrby, hdecrby。hgetall 得到 hash 中所有的键值对。
4) 类似 java, redis set 的内部实现也是基于上面的 hash，所有的键指向同一个内部值。常见的命令有 sadd (增加一个或多个元素)，members (获得所有元素)，scard (获得元素个数)，srandmember v_name [count]（随机获取 count 个元素，如果省略 count，随机取一个元素），srem v_name key1 key2 (删除一个或多个元素)，spop（随机删除一个元素），sismember （判断元素是否在 set 中）
5) zset 是 redis 提供的有序集合，可以将其理解为 map<key, score>，除了 hash 结构，为了提高增删查改的效率，底层还使用了 skiplist 结构，skiplist 按照 score 进行排序，score 可以是整数或浮点数。常用的命令有 zadd v_name score key (增加一个或多个元素)，zcard (获取元素个数)，zrem (删除一个或者多个元素)，zincrby v_name 1.2 key1 (作为计数器使用)，zscore (获取对应 key 的权重)，zrank (获得元素的正向排名)，zrevrank（获得元素的倒数排名），zrange v_name start end [withscores]（获得正向排名从 start 到 end 的所有元素，可选是否同时获得元素对应的 scores），zrevrange 和 zrange 类似，只不过它按负向排名输出元素。zrangebyscore v_name score_start score_ed [withscores] 和 zrevrangebyscore 获取指定 score 范围内的元素。zremrangebyrank 和 zremrangebyscore 分别删除指定排名范围内和权重范围内的元素。
### 发布/订阅模式
1) subscribe channel [channel ...]，订阅给定的频道列表
2) publish channel message ，将消息发送到指定的频道
3) unsubscribe channel [channel ...]，退订给定的频道列表
4) psubscribe pattern [pattern ...]，订阅符合给定模式的频道列表
5) punsubscribe pattern [pattern ...]，退订符合给定模式的频道列表
6) pubsub <subcommand> [argument [argument ...]] 查看订阅与发布系统状态，它由数个不同格式的子命令组成。
### 分布式锁
1) setex key timeout(s) value，设置 key 并并设置过期时间，整个过程是原子操作
2) setnx key value, 当指定的 key 不存在时，为 key 设置指定的值
3）getset key value, 将 value 赋值给 key，并返回旧值，如果旧值不存在返回 nil，如果旧值不是字符串类型，返回错误

### 配置文件


