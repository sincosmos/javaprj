# 数据存储
## MySQL 数据库
### 物理文件组成
1. 日志文件：error log (hostname.err), binlog (mysql-bin.******, mysql-bin.index), slow query log (hostname-slow.log), redo log(Inndo 事务日志，配合表中 undo 信息，保证事务安全性）
2. 数据文件：table-name.frm (metadata of the table), table-name.MYD (MyISAM 表数据文件），table-name.MYI（MyISAM 表索引文件），table-name.ibd 或 .ibdata (innodb 独享或共享数据文件)
3. Replication 相关文件：master.info (保存在 slave 端数据目录下，存放 master 的主机地址/端口、连接用户/密码，当前日志位置，已读取到的日志位置信息)，mysql-relay-bin.xxxxxn （存放 slave I/O 线程从 Master 端读取的 binlog 信息，然后由 Slave 端的 SQL 线程解析并转化成 Master 端执行的 SQL，从而可以在 slave 同步执行），mysql-relay-bin.index (记录relay log 存放的绝对路径)，relay-log.info
4. 其它文件：my.cnf, pid file (linux), socket file (linux)
### 逻辑架构
总的来说，Mysql 可以看成是二层架构，第一层通常叫做 SQL layer，在 MySQL 数据库系统处理底层数据之前的所有工作都在这一层完成，包括权限判断、SQL 解析、执行计划优化、Query cache 的处理等；第二层是存储引擎层，也就是底层数据存取操作实现，由多种存储引擎组成。
还可以把 SQL layer 层拆分为服务层和核心层，服务层主要负责和用户的交互工作，例如连接处理、授权认证、安全等；核心层主要负责数据存取预处理，例如SQL 解析、查询缓存、执行计划优化等。
可以通过下图来了解，当一条 SQL 发送到 mysql 服务器后是怎样被执行的。
![sql 的执行过程](https://user-gold-cdn.xitu.io/2018/8/12/1652e56415e9a6f4?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
Mysql 缓存由`query_cache_type`参数控制打开，由 `query_cache_size` 控制缓存可用空间大小，`query_cache_limit` 控制每次能够缓存的最大结果集(`show variables like '%cache%'`)。可以通过 `show status like '%cache%'`中 `Qcache_hits` 和 `Qcache_inserts` 等来查看缓存的使用状态。MySQL将缓存存放在一个引用表（不要理解成table，可以认为是类似于HashMap的数据结构），通过一个哈希值索引，这个哈希值通过查询本身、当前要查询的数据库、客户端协议版本号等一些可能影响结果的信息计算得来。所以两个查询在任何字符上的不同（例如：空格、注释），都会导致缓存不会命中。如果查询中包含任何用户自定义函数、存储函数、用户变量、临时表、mysql库中的系统表，其查询结果都不会被缓存。比如函数NOW()或者CURRENT_DATE()会因为不同的查询时间，返回不同的查询结果，再比如包含CURRENT_USER或者CONNECION_ID()的查询语句会因为不同的用户而返回不同的结果，将这样的查询结果缓存起来没有任何的意义。既然是缓存，就会失效，那查询缓存何时失效呢？MySQL的查询缓存系统会跟踪查询中涉及的每个表，如果这些表（数据或结构）发生变化，那么和这张表相关的所有缓存数据都将失效。正因为如此，在任何的写操作时，MySQL必须将对应表的所有缓存都设置为失效。
[MySQL逻辑架构及性能优化原理](https://juejin.im/post/5c3ef9e051882525dc62de87)
### 存储引擎与索引原理
参考资料 [MySQL索引背后的数据结构及算法原理](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)  
1) MySQL 常用的存储引擎有 MyISAM 和 InnoDB。前者不支持事务，在更新时使用表级锁，查询速度很快，适合更新少，查询多的业务场景；后者支持事务和行级锁，因此更适合并发操作和更新较多的业务场景。另外，MyISAM 不支持外键关联。  
2) B-tree is a fat tree. The height of B-Trees is kept low by putting maximum possible keys in a B-Tree node. Generally, a B-Tree node size is kept equal to the disk block size. Since h is low for B-Tree, total disk accesses for most of the operations are reduced significantly compared to balanced Binary Search Trees like AVL Tree, Red-Black Tree, ..etc.  
3) In B Tree, Keys and records both can be stored in the internal as well as leaf nodes. Whereas, in B+ tree, records (data) can only be stored on the leaf nodes while internal nodes can only store the key values.
4) The leaf nodes of a B+ tree are linked together in the form of a singly linked lists to make the search queries more efficient.  
5) 为什么 Mysql 索引用 B+ 树而不是 B 树？B 树的节点需要存储数据，而 B+ 树只有叶子结点存储数据，这样在节点大小（通常是磁盘页的大小）一定的情况下，B 树内部节点能存储的索引数量就大大少于 B+ 树内部节点存储的索引的数量，导致同样的检索，B 树很可能要读更多的页，即磁盘 IO 次数回增多，检索效率下降。另外 B+ 树的叶子节点会顺序存储下一个叶子节点的地址指针，在区间访问时，能够进一步减少磁盘 IO 次数。
6) 假设在表 A 上经常用到的两个查询语句是 select colx from A where col2=? and col1=? 和 select coly from A where col1 like 'sample%'，应该怎么建立索引比较好？可以考虑建立一个联合索引，create index composite_index_cols on A (col1, col2)。对于前者来说，SQL 解析器会自动调整两个条件的顺序，这样两个查询都能用到这个索引，加快查询效率。
7) MyISAM 的主键索引和非主键索引都是非聚簇索引，叶子节点数据区存放的都是数据地址。一个 MyISAM 的表会被存放为三个以表名命名的物理文件，.frm 文件记录表结构定义信息，.MYD 文件存放表的数据，.MYI 文件存放表的索引。InnoDB 的主键索引是聚簇索引，叶子节点的数据就是数据本身，非主键索引是非聚簇索引，数据区存放的是主键。因此 MyISAM 表可以没有主键，而 InnoDB 的表如果不指定主键，则会由存储引擎生成一个全局的 rowid 序列作为主键，所有不指定主键的表共享同一个 rowid 序列，并发性能差，因此一般都需要指定主键。一个 InnoDB 的表会被存储为两个物理文件，.frm 文件记录表结构，.ibd (独享表)或 ibdata（共享表） 文件存储表数据和索引；另外，InnoDB 还有事务日志信息文件，可以通过 redo 日志和表中 undo 信息进行事务管理或数据库恢复(数据库恢复时，采用 checkpoint 机制，即 checkpoint 之前的操作已经持久化到磁盘，数据库恢复不用重做所有的 redo 日志，只需按照 checkpoint 之后的操作进行恢复即可)。
8) 索引是存储引擎级别的实现，不同的存储引擎可能采用不同的索引类型和实现方式。B+ 树是大多数 mysql 存储引擎默认的索引类型。
9) 联合索引实际上是按照联合索引的第一列建立 B+ 树，非叶子节点上的 key 是第一列的值，叶子节点上保存了联合索引中所有列的值并按照第一列、第二列...依次排序。
10) mysql 数据库的主从同步是通过 binlog 实现的。slave mysql 服务器数据目录下，存放的 master.info 文件保存 master 的主机地址/端口、连接用户/密码等，以便 slave 连接 master。master 和 slave 机器启动 mysql 服务时，显式的开启 binlog 服务，--log-bin[=file_name]。如果不指定路径，master 会在数据目录下生成 mysql-bin.******(* 代表 0～9 数字，表示日志的序号) 日志文件，同时生成 mysql-bin.index 文件，记录所有 binlog 的绝对路径，便于其它线程能顺利找到所需的 binlog。slave 端启动线程从 master 同步 binary log，并存放为 mysql-relay-bin.****** 文件，slave 端的 SQL 线程读取并解析 binlog，转化为可执行的 SQL 进行同步操作。
### 锁与事务
参考资料
	[CS-Notes 数据库原理](https://cyc2018.github.io/CS-Notes/#/notes/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%B3%BB%E7%BB%9F%E5%8E%9F%E7%90%86)
	[MySQL 加锁处理分析](http://hedengcheng.com/?p=771#_Toc374698322)
1) 不同的存储引擎各自实现其锁机制。常见的 MyISAM 采用表级锁，有读锁定、写锁定两种类型。
2) InnoDB 采用行级锁和表级锁共存的方式，分为共享锁和排他锁。另外除了对行或表的锁定，InnoDB 还有表级锁定的意向锁，即意向共享锁和意向排他锁作为行锁的辅助工具。事务要获取某些行的共享锁，则必须先获得表的意向共享锁( `SELECT column FROM table ... LOCK IN SHARE MODE;`)；事务要获取某些行的排他锁，则必须先获得表的意向排他锁(`SELECT column FROM table ... FOR UPDATE;`)。意向锁可以提高锁冲突时的检查效率。
3）行锁具体分为记录锁、间隙锁、临键锁，以上三种行锁都是实现在索引上的，如果加锁操作没有使用索引，那么该锁会退化为表锁。记录锁锁住索引对应的记录，记录锁加锁操作必须作用在唯一索引列或主键列上，否则将会变成临键锁。间隙锁锁定的是索引范围内的记录，可以是多个不连续的索引范围，事务隔离级别是 Repeatable Read 时自动开启，否则会失效。临键锁是记录锁和间隙锁的组合，临键锁在索引具有唯一性时（例如主键索引），将会降级为记录锁，以增加并发性。
4）MVCC 多版本并发控制，当某个事务更新表数据时，MVCC 系统不会立即覆盖相应记录，而是创建相应记录的一个新版本（Data）和日志（Undo log)。Mysql 的 InnoDB 中，每一行数据除了用户数据外，数据库会自动维护一些额外的字段，和 MVCC 相关的有 `DATA_TRX_ID` 和 `DATA_ROLL_PTR`，前者是最近更新该行的 Transaction ID，每开启一个事务，事务 ID 都会增加，每个事务拿到的 ID 都不一样；后者是指向 Undo Log 中旧版本数据指针，支持了事务回滚操作，同时也支持了多个事务之间并发读的版本冲突问题。MVCC 最大的好处是读不加锁，读写不冲突，极大增加了系统的并发性能。MVCC 的读操作分为快照读和当前读。在不同的事务隔离级别下，快照读有不同的表现。在 RC 级别下，每次读都会生成一个新的快照，所以每次快照读到的都是当前其它事务提交了的最新版本；在 RR 级别下，快照读借助 MVCC 和 undo log 来实现，会在事务中第一次 SELECT 语句执行时生成，只有在本事务中对数据进行更改时才会更新快照（其它事务的提交不会引发快照的更新）

5）事务隔离
   ```
   CREATE TABLE `student` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `uname` varchar(60) DEFAULT NULL,
	  `subject` varchar(60) DEFAULT NULL,
	  `score` int(11) DEFAULT NULL,
	  PRIMARY KEY (`id`),
	  INDEX `uname_idx` (`uname`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	insert into student(id, uname, subject, score) values(11, '小明', '语文', 80);
	insert into student(id, uname, subject, score) values(12, '小明', '数学', 90);
	insert into student(id, uname, subject, score) values(15, '小红', '语文', 82);
	insert into student(id, uname, subject, score) values(17, '小红', '数学', 88);
	insert into student(id, uname, subject, score) values(22, '小文', '语文', 68);
	insert into student(id, uname, subject, score) values(26, '小文', '数学', 96);
	insert into student(id, uname, subject, score) values(27, '小飞', '语文', 79);
	insert into student(id, uname, subject, score) values(28, '小飞', '数学', 83);
   ```

	| id   | uname      | subject   | score |
	| ---- |:----------:| ---------:| -----:|
	|11    | 小明        | 语文      | 80    |
	|12    | 小明        | 数学      | 90    |
	|15    | 小红        | 语文      | 82    |
	|17    | 小红        | 数学      | 88    |
	|22    | 小文        | 语文      | 68    |
	|26    | 小文        | 数学      | 96    |
	|27    | 小飞        | 语文      | 79    |
	|28    | 小飞        | 数学      | 83    |


   事务隔离级别为读未提交时出现脏读，不可重复读和幻读现象。
   其中脏读例如下面事务 T1 可以读到事务 T2 尚未提交的更改。
   读未提交的隔离级别下，事务过程中读操作(例如T1)不会加读锁，虽然事务过程写操作（例如T2）会加写锁，但是事务T1的读操作会忽略T2的写锁（该写锁将在T2提交时释放），读到T2未提交的数据。
	事务 T1
	```
	set autocommit = false;
	set session transaction isolation level read uncommitted;
	start transaction;
	select id, uname, subject, score from student where id=15;
	commit; 
	``` 
   事务 T2
   ```
	set autocommit = false;
	set session transaction isolation level read uncommitted
	start transaction;
	update student set score=92 where id=15;
	# sleep(5)
	rollback; 
	``` 
	事务隔离级别为读已提交时出现不可重复读和幻读现象，避免了脏读。
	在读已提交的隔离级别下，事务 T3 开启时，会生成事务版本号
	事务 T3
	```
	set autocommit = false;
	set session transaction isolation level read committed;
	start transaction;
	select id, uname, subject, score from student where id=15;
	commit; 
	``` 
   事务 T4
   ```
	set autocommit = false;
	set session transaction isolation level read committed;
	start transaction;
	update student set score=92 where id=15;
	# sleep(5)
	rollback; 
	``` 
	幻读
	
事务的四个隔离级别是 Read Uncommitted （发生脏读、不可重复读，幻读）, Read Committed（发生不可重复读，幻读）, Repeatable Read（发生幻读）, Serializable。不可重复读针对修改，指在同一个事务中，多次读取同一条记录，发现该记录中某些列值被修改过；幻读是不可重复读的特殊场景，针对新增或删除，指在同一个事务中，同一条查询语句返回的结果集行数不同。Mysql 利用 MVCC 和临键锁共同解决了默认隔离级别（Repeatable Read）下的幻读问题。
6）在高并发场景下，间隙锁有可能导致死锁现象。[mysql 死锁问题分](https://www.cnblogs.com/LBSer/p/5183300.html)
### 索引优化
参考资料 [mysql 优化](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)
1) mysql 查询时，如果索引列是表达式的一部分或函数的参数，将不会使用索引，例如 select * from tab where id+1=5, select * from tab where len(id) = 3
## Join 的实现原理
在 MySQL 中，只有一中 Join 算法，即 Nested Loop Join（其它数据库还提供的有 Hash Join 和 Sort Merge Join），实际上就是通过驱动表的结果集（通过 where 条件过滤）作为循环基础数据，然后一条一条的通过该结果集中的数据作为过滤条件到下一个表中查询数据，然后合并结果，如果还有第三个表参与 Join，则再通过前两个表的 Join 结果集作为循环基础数据，再一次通过循环查询条件到第三个表中查询数据，如此往复。
## order by 与 limit
如果根据索引字段进行 order by，mysql 可以不进行排序直接返回有序的结果。
```
# date_created 字段上有索引，
select * from sites order by date_created desc limit 100, 10;
# 如果 category_id 分布均匀，为了避免扫描大量数据，下面的情况下我们最好在 category_id 列上建立索引
select * from sites where category_id=5 order by date_created desc limit 10;
```
如果排序字段上无索引，mysql 提供两种排序实现方式。
1) 首先从表中取出满足过滤条件的用于排序的字段和可以直接定位到行数据的行指针，在 Sort Buffer 中进行实际的排序操作，然后利用排好序的行指针回表查询，取得其它字段，返回客户端
2) 根据过滤条件一次取出排序字段和客户端需要的所有其它字段，将不需要排序的字段数据放在一块内存区域中，然后在 Sort Buffer 中将排序字段和行指针进行排序，最后利用排序后的行指与存放在内存区域中的其它字段进行匹配，将结果按照顺序返回给客户端。这种方式减少了回表磁盘 IO 操作，但会占用更多的内存，是典型的以空间换时间的方式。
如果要对 Join 后的结果进行排序操作，如果排序字段仅在 Join 的驱动表中，那么可以在获得驱动表的结果集时在 Sort Buffer 中进行排序，利用排序后的结果集与第二个表进行 Nested Loop Join。但如果排序的多个字段出现在 join 的两个表中，那就不能利用 Sort Buffer 进行排序，而是必须先将 Join 的结果集存放到一个临时表中，再把临时表中的数据抽取到 Sort Buffer 中进行排序。
在无索引的字段上进行排序并加上 limit 条件限制时，MySQL 5.6 开始采用了堆排序的优化算法，虽然参与排序的元素不会受到影响，但是 sort buffer 所需的空间变小许多。

## 主从数据库
1)  随着用户量的增多，我们可以将数据库的读写分离，使用主库（Master)负责写，若干个从库与主库同步更新数据，用户从从库读数据。写操作发生后，从库同步主库会有一定的延迟。在程序实现方面，可以借助 Spring AOP 组件实现写主库，读请求读从库。当只有读操作的时候，直接操作读库（从库），当在写事务（即写主库）中读时，强制走从库，即先暂停写事务，开启读（读从库），然后恢复写事务。此方案其实是使用事务传播行为为：NOT_SUPPORTS解决的。
## 分库分表
1）随着用户量的大幅度增多和历史数据的积累，一个 master 不能满足高并发的写需求，而全量的数据放在一个表/数据库里也会因为数据量过大导致查询性能下降，这时候我们就要考虑分库分表了。
分库主要解决单个数据库性能问题。垂直分库在微服务盛行的今天非常受欢迎。基本的思路就是按照业务模块划分出来不同的数据库，而不是像早期那样将所有的数据表都放在同一个数据库中。例如电商网站中，订单表、用户表、商品表等都分别放在不同的数据库里。不同的数据库可能位于不同的机器上。
分表一般有水平拆分和垂直拆分两种分法。水平分表就是将某个表的数据按行拆分，分别保存到不同的表中，例如 id（主键） 是 1 ～ 1000万的行在一个表，id 是 1000万 ～ 2000万的行在下一个表，依次拆分。根据表数据的大小，这些表都在同一个数据库（不建议使用），也可以位于不同的数据库中（即分库分表同时使用）。当然，实际水平分表的策略可能是通过主键或者时间等字段进行 hash 和取模后进行拆分。垂直拆分指将数据表的列拆分到不同的表中，使拆分后的每个表拥有较少的列，通常可以把较大的字段单独保存到一个表中，常用的字段和不常用的字段也分开到不同的表中。
如果是因为表多而数据多，使用垂直切分，根据业务切分成不同的库。如果是因为单张表的数据量太大，这时要用水平切分，即把表的数据按某种规则切分成多张表，甚至多个库上的多张表。 分库分表的顺序应该是先垂直分，后水平分。 因为垂直分更简单，更符合我们处理现实世界问题的方式。
分库分表后，应当尽量避免跨库 join，可以采用增加数据冗余的方式来解决该问题。