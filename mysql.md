---
title: mysql
date: 2020-02-18 12:49:01
tags: MySQL
description: 对MySQL也没有系统的学习，在平时使用MySQL的过程中将遇到的一些知识进行简单的总结，并对其用具体的小例子进行说，并不断完善对MySQL的认识，希望在以后的工作中，对MySQL的使用也会越来越多，包括数据库管理和数据库调优等。

---

<!-- toc -->

# 1.case..when用法

## 1.1 使用格式

- 简单函数
  CASE [col_name] WHEN [value1] THEN [result1]…ELSE [default] END
- 搜索函数
  CASE WHEN [expr] THEN [result1]…ELSE [default] END

## 1.2 使用实例

**预制数据**-创建表

```
mysql> create table person(
    -> id int(5) primary key,
    -> country varchar(20),
    -> name varchar(20),
    -> age int(3),
    -> gender varchar(2));
Query OK, 0 rows affected (0.08 sec)

```

**预制数据**-插入数据

```
INSERT INTO person(id, country, name, age, gender) VALUES(3,'china','tom',12,'1');
INSERT INTO person(id, country, name, age, gender) VALUES(7,'usa','kobe',10,'1');
INSERT INTO person(id, country, name, age, gender) VALUES(8,'usa','james',20,'1');
INSERT INTO person(id, country, name, age, gender) VALUES(9,'usa','grace',30,'0');
INSERT INTO person(id, country, name, age, gender) VALUES(10,'japan','monica',40,'2');
```

**预制数据**-完成表数据

```
mysql> select * from person;
+----+---------+--------+------+--------+
| id | country | name   | age  | gender |
+----+---------+--------+------+--------+
|  3 | china   | tom    |   12 | 1      |
|  7 | usa     | kobe   |   10 | 1      |
|  8 | usa     | james  |   20 | 1      |
|  9 | usa     | grace  |   30 | 0      |
| 10 | japan   | monica |   40 | 2      |
+----+---------+--------+------+--------+
5 rows in set (0.00 sec)

```

### 1.2.1 根据查询结果值重新定义值

从上面的表中，我们看到性别这一列其实可读性并不强，有时候为了数据库范式，会出现这种可读性不强的数据，那么我们在查询的时候可以使用CASE..WHEN重新使查询结果强可读。

```
mysql> SELECT id,`name`,CASE gender WHEN 1 THEN '男' WHEN 0 THEN '女' ELSE '其他' END AS '性别' FRO
M person;
+----+--------+--------+
| id | name   | 性别   |
+----+--------+--------+
|  3 | tom    | 男     |
|  7 | kobe   | 男     |
|  8 | james  | 男     |
|  9 | grace  | 女     |
| 10 | monica | 其他   |
+----+--------+--------+
5 rows in set (0.00 sec)

```

### 1.2.2 根据查询结果进行判断（搜索）

有时候我们需要在查询结果中对值进行简单逻辑处理，比如  
小于18岁我们称少年  
19-35称为青年  
大于36称中年

```
mysql> SELECT id,`name`,CASE WHEN age<18 THEN '少年'
    -> WHEN age<=35 THEN '青年'
    -> WHEN age>36 THEN '中年'
    -> END '状态'
    -> FROM person;
+----+--------+--------+
| id | name   | 状态   |
+----+--------+--------+
|  3 | tom    | 少年   |
|  7 | kobe   | 少年   |
|  8 | james  | 青年   |
|  9 | grace  | 青年   |
| 10 | monica | 中年   |
+----+--------+--------+
5 rows in set (0.00 sec)

```

**总结**：从上面的SQL可以看出，我们根据年龄列的值，凭空判断出了一列“状态”，这就是使用CASE进行判断(搜索)的例子
**注意**：搜索函数可以写判断，并且搜索函数只会返回**第一个**符合条件的值，其他case被忽略

### 1.2.3 高级用法-分组统计

在上面的person表中，我们要分别统计出每个国家的性别人数。

```
mysql> SELECT country,
    -> SUM(CASE WHEN gender=1 THEN 1 ELSE 0 END) AS '男',
    -> SUM(CASE WHEN gender=0 THEN 1 ELSE 0 END) AS '女',
    -> SUM(CASE WHEN gender>1 THEN 1 ELSE 0 END) AS '未知'
    -> FROM person
    -> GROUP BY country;
+---------+------+------+--------+
| country | 男   | 女   | 未知   |
+---------+------+------+--------+
| china   |    1 |    0 |      0 |
| japan   |    0 |    0 |      1 |
| usa     |    2 |    1 |      0 |
+---------+------+------+--------+
3 rows in set (0.01 sec)

```

### 1.2.4 高级用法-批量更新

由于person表中的性别可读性不强，我们批量把性别字段更新成汉字

```
mysql> UPDATE person SET gender=CASE gender
    -> WHEN 1 THEN '男'
    -> WHEN 0 THEN '女'
    -> WHEN 2 THEN '未知'
    -> END
    -> WHERE gender IN (0,1,2);
Query OK, 5 rows affected (0.01 sec)
Rows matched: 5  Changed: 5  Warnings: 0

mysql> SELECT * FROM person;
+----+---------+--------+------+--------+
| id | country | name   | age  | gender |
+----+---------+--------+------+--------+
|  3 | china   | tom    |   12 | 男     |
|  7 | usa     | kobe   |   10 | 男     |
|  8 | usa     | james  |   20 | 男     |
|  9 | usa     | grace  |   30 | 女     |
| 10 | japan   | monica |   40 | 未知   |
+----+---------+--------+------+--------+
5 rows in set (0.00 sec)

```

**注意事项：一定要有WHERE gender IN的限制，否则不在WHEN中的记录就会被置为NULL**
CASE..WHEN还有其他一些高级用法，等用到的时候再慢慢研究，比如可以用它做行转列操作。

### 1.2.4 复合条件使用

比如上面定义青年的时候，我们把条件修改一下，我们把大于等于12岁，并且小于等于32岁的叫做青年

```
mysql> SELECT * FROM person;
+----+---------+--------+------+--------+
| id | country | name   | age  | gender |
+----+---------+--------+------+--------+
|  3 | china   | tom    |   12 | 男     |
|  7 | usa     | kobe   |   10 | 男     |
|  8 | usa     | james  |   20 | 男     |
|  9 | usa     | grace  |   30 | 女     |
| 10 | japan   | monica |   40 | 未知   |
+----+---------+--------+------+--------+
5 rows in set (0.00 sec)

mysql> SELECT id,`name`,CASE
    -> WHEN age>=12 AND age<=32 THEN '青年'
    -> ELSE '忽略'
    -> END '状态'
    -> FROM person;
+----+--------+--------+
| id | name   | 状态   |
+----+--------+--------+
|  3 | tom    | 青年   |
|  7 | kobe   | 忽略   |
|  8 | james  | 青年   |
|  9 | grace  | 青年   |
| 10 | monica | 忽略   |
+----+--------+--------+
5 rows in set (0.00 sec)

```

**总结**:在WHEN条件使用时可以使用AND进行连接，进行复合条件的判断



>[参考博客](https://www.jianshu.com/p/c2b086fb7b48)  
>[行转列参考博客](https://www.cnblogs.com/chenduzizhong/p/9590741.html)  
>[复合条件参考博客](https://blog.csdn.net/qb170217/article/details/81504578)  


# 2.MySql中的IFNULL、NULLIF和ISNULL用法详解

## 2.1 isnull用法

**用法**：isnull(expr)
**解析**：如expr 为null，那么isnull() 的返回值为 1，否则返回值为 0。
**实例**：这里还使用上面的person表

```
mysql> SELECT * FROM person;
+----+---------+--------+------+--------+
| id | country | name   | age  | gender |
+----+---------+--------+------+--------+
|  3 | china   | tom    |   12 | 男     |
|  7 | usa     | kobe   |   10 | 男     |
|  8 | usa     | james  |   20 | 男     |
|  9 | usa     | grace  |   30 | 女     |
| 10 | japan   | monica | NULL | 未知   |
+----+---------+--------+------+--------+
5 rows in set (0.00 sec)

mysql> SELECT id,name,ISNULL(age) FROM person;
+----+--------+-------------+
| id | name   | ISNULL(age) |
+----+--------+-------------+
|  3 | tom    |           0 |
|  7 | kobe   |           0 |
|  8 | james  |           0 |
|  9 | grace  |           0 |
| 10 | monica |           1 |
+----+--------+-------------+
5 rows in set (0.00 sec)

```

## 2.2 ifnull用法

**用法**:IFNULL(expr1,expr2)
**解析**：假如expr1 不为 NULL，则 IFNULL() 的返回值为 expr1;
否则其返回值为 expr2。IFNULL()的返回值是数字或是字符串，具体情况取决于其所使用的语境。
**实例**：

```
mysql> SELECT * FROM person;
+----+---------+--------+------+--------+
| id | country | name   | age  | gender |
+----+---------+--------+------+--------+
|  3 | china   | tom    |   12 | 男     |
|  7 | usa     | kobe   |   10 | 男     |
|  8 | usa     | james  |   20 | 男     |
|  9 | usa     | grace  |   30 | 女     |
| 10 | japan   | monica | NULL | 未知   |
+----+---------+--------+------+--------+
5 rows in set (0.00 sec)

mysql> SELECT id,name,IFNULL(age,'无') FROM person;
+----+--------+-------------------+
| id | name   | IFNULL(age,'无')  |
+----+--------+-------------------+
|  3 | tom    | 12                |
|  7 | kobe   | 10                |
|  8 | james  | 20                |
|  9 | grace  | 30                |
| 10 | monica | 无                |
+----+--------+-------------------+
5 rows in set (0.00 sec)
```

## 2.3 nullif用法

**用法**：NULLIF(expr1,expr2) 
**解析**：如果expr1= expr2 成立，那么返回值为NULL，否则返回值为 expr1。这和CASE WHEN expr1 = expr2  THEN NULL ELSE expr1 END相同。
**实例**：

```
mysql> SELECT NULLIF('男','女');
+---------------------+
| NULLIF('男','女')   |
+---------------------+
| 男                  |
+---------------------+
1 row in set (0.00 sec)

mysql> SELECT NULLIF('男','男');
+---------------------+
| NULLIF('男','男')   |
+---------------------+
| NULL                |
+---------------------+
1 row in set (0.00 sec)

结果：当expr1和expr2不相等的时候，返回expr1，也就是‘男’，第二个例子相等的时候，返回的是NULL.
```

>[nullif,isnull,ifnull参考博客](https://www.jb51.net/article/93101.htm)  



# 3.连结查询

**构造数据**-创建表

```
创建作者author表
mysql> CREATE TABLE author(
    -> author_id INT(2) PRIMARY KEY,
    -> name VARCHAR(20));
    
    
创建图书bookinfo表
mysql> CREATE TABLE bookinfo(
    -> id INT(2) PRIMARY KEY,
    -> name VARCHAR(10),
    -> publisher_id INT(2), #这里是指出版商的id
    -> author_id INT(2), #这里是指作者表的ID
    -> price INT(2));


创建出版商publisher表
mysql> CREATE TABLE publisher(
    -> publisher_id INT(2) PRIMARY KEY,
    -> name VARCHAR(10));


```



**构造数据**-添加数据

```
插入author表数据
INSERT INTO `author`(`author_id`, `name`) VALUES (1, '修昔底德');
INSERT INTO `author`(`author_id`, `name`) VALUES (2, '小仲马');
INSERT INTO `author`(`author_id`, `name`) VALUES (3, '柏拉图');
INSERT INTO `author`(`author_id`, `name`) VALUES (4, '亚里士多德');
INSERT INTO `author`(`author_id`, `name`) VALUES (5, '维吉尔');
INSERT INTO `author`(`author_id`, `name`) VALUES (6, '普鲁塔克');
INSERT INTO `author`(`author_id`, `name`) VALUES (7, '徐焰');
INSERT INTO `author`(`author_id`, `name`) VALUES (8, '老舍');

插入bookinfo表数据
对应字段顺序(`id`, `name`, `publisher_id`, `author_id`, `price`)
INSERT INTO `bookinfo` VALUES (1, '《伯罗奔尼撒战争史》', 1, 1, 11);
INSERT INTO `bookinfo` VALUES (2, '《茶花女》', 2, 2, 22);
INSERT INTO `bookinfo` VALUES (3, '《对话录》 ', 3, 3, 22);
INSERT INTO `bookinfo` VALUES (4, '《伦理学》', 4, 4, 44);
INSERT INTO `bookinfo` VALUES (5, '《埃涅阿斯记》', 5, 5, 55);
INSERT INTO `bookinfo` VALUES (6, '《希腊罗马英雄史》 ', 6, 6, 66);
INSERT INTO `bookinfo` VALUES (7, '《共产党人的故事》', 7, 7, 77);
INSERT INTO `bookinfo` VALUES (8, '《四世同堂》', 8, 8, 88);

插入publisher表数据
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (1, '经济日报出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (2, '外语教学与研究出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (3, '中国纺织出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (4, '编译出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (5, '中国言实出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (6, '东方出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (7, '北京工艺美术出版社');
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (8, '文津出版社');

```

**构造数据**-数据

```
mysql> SELECT * FROM author;
+-----------+-----------------+
| author_id | name            |
+-----------+-----------------+
|         1 | 修昔底德        |
|         2 | 小仲马          |
|         3 | 柏拉图          |
|         4 | 亚里士多德      |
|         5 | 维吉尔          |
|         6 | 普鲁塔克        |
|         7 | 徐焰            |
|         8 | 老舍            |
+-----------+-----------------+
8 rows in set (0.00 sec)

mysql> SELECT * FROM bookinfo;
+----+--------------------------------+--------------+-----------+-------+
| id | name                           | publisher_id | author_id | price |
+----+--------------------------------+--------------+-----------+-------+
|  1 | 《伯罗奔尼撒战争史》           |            1 |         1 |    11 |
|  2 | 《茶花女》                     |            2 |         2 |    22 |
|  3 | 《对话录》                     |            3 |         3 |    22 |
|  4 | 《伦理学》                     |            4 |         4 |    44 |
|  5 | 《埃涅阿斯记》                 |            5 |         5 |    55 |
|  6 | 《希腊罗马英雄史》             |            6 |         6 |    66 |
|  7 | 《共产党人的故事》             |            7 |         7 |    77 |
|  8 | 《四世同堂》                   |            8 |         8 |    88 |
+----+--------------------------------+--------------+-----------+-------+
8 rows in set (0.00 sec)

mysql> SELECT * FROM publisher;
+--------------+--------------------------------+
| publisher_id | name                           |
+--------------+--------------------------------+
|            1 | 经济日报出版社                 |
|            2 | 外语教学与研究出版社           |
|            3 | 中国纺织出版社                 |
|            4 | 编译出版社                     |
|            5 | 中国言实出版社                 |
|            6 | 东方出版社                     |
|            7 | 北京工艺美术出版社             |
|            8 | 文津出版社                     |
+--------------+--------------------------------+
8 rows in set (0.00 sec)

```





## 3.1 LEFT JOIN 左连结查询

**用法**：SELECT XXX  FROM left_table **LEFT JOIN** right_table **ON** left_table.key=right_table.key

**解析**：LEFT JOIN ... ON   

LEFT JOIN 关键字会从左表 (left_table) 那里返回所有的行，即使在右表 (right_table ) 中没有匹配的行。

情况一：如果左表中的数据多，而右表中的数据少，那么没查询到的数据会显示NULL,但是左表的数据会全部返回；

情况二：如果左表中的数据少，而由表中的数据多（这里的多是指有重复数据，非重复数据见情况一），那么在返回左表数据时也会有重复；

### 3.1.1 基本使用示例

**情况一**：为了满足情况一，这里多添加一条出版商记录

```
INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (9, '新华出版社');
```

测试结果1：

```
mysql> INSERT INTO `publisher`(`publisher_id`, `name`) VALUES (9, '新华出版社');
Query OK, 1 row affected (0.00 sec)

mysql> SELECT * FROM publisher pub LEFT JOIN bookinfo book ON book.publisher_id=pub.publisher_id;
+-------+---------------------+----+--------------------+-------+---------+-----+
| publis| name                |id  | name               |publi  |author_id|price|
| her_id|                     |    |                    |sher_id|         |     |   
+-------+---------------------+----+--------------------+-------+---------+-----+
|  1    | 经济日报出版社        |   1|《伯罗奔尼撒战争史》    |    1  |        1|   11|
|  2    | 外语教学与研究出版社   |   2| 《茶花女》           |    2  |        2|   22|
|  3    | 中国纺织出版社        |   3| 《对话录》           |    3  |        3|   22|
|  4    | 编译出版社           |   4| 《伦理学》           |    4  |        4|   44|
|  5    | 中国言实出版社        |   5| 《埃涅阿斯记》        |    5  |        5|   55|
|  6    | 东方出版社           |   6| 《希腊罗马英雄史》     |    6  |        6|   66|
|  7    | 北京工艺美术出版社     |   7| 《共产党人的故事》     |    7  |        7|   77|
|  8    | 文津出版社           |   8| 《四世同堂》           |    8  |        8|   88|
|  9    | 新华出版社           |NULL| NULL                 | NULL  |     NULL| NULL|
+-------+---------------------+----+--------------------+-------+---------+-----+
```

可以看出，由于左表中有9条数据，而右表bookinfo中有8条数据，所以左表中的第9条数据，对应的bookinfo显示为NULL;

**情况一**:右表有重复数据的情况

为了满足条件，需要插入一条重复bookinfo数据

```
INSERT INTO `bookinfo` VALUES (9, '《四世同堂》', 8, 8, 88);
```

测试结果2：

```
mysql> SELECT * FROM publisher pub LEFT JOIN bookinfo book ON book.publisher_id=pub.publisher_id;
+-------------+---------+------+--------------+--------------+-----------+-------+
|publisher_id | name    | id   | name         | publisher_id | author_id | price |
+-------------+---------+------+--------------+--------------+-----------+-------+
|  1 | 经济日报出版社     |1     | 《伯罗奔尼撒战争史》 |    1     |     1 |    11 |
|  2 | 外语教学与研究出版社| 2    | 《茶花女》         |    2     |     2 |    22 |
|  3 | 中国纺织出版社     | 3    | 《对话录》         |    3      |     3 |    22 |
|  4 | 编译出版社        | 4    | 《伦理学》         |    4      |     4 |    44 |
|  5 | 中国言实出版社     | 5    | 《埃涅阿斯记》      |    5     |     5 |    55 |
|  6 | 东方出版社         | 6   | 《希腊罗马英雄史》   |    6     |     6 |    66 |
|  7 | 北京工艺美术出版社 | 7    | 《共产党人的故事》   |    7      |     7 |    77 |
|  8 | 文津出版社         | 8   | 《四世同堂》        |    8      |     8 |    88 |
|  8 | 文津出版社         | 9   | 《四世同堂》        |    8      |     8 |    88 |
|  9 | 新华出版社         | NULL | NULL             | NULL      |  NULL |  NULL |
+-------------+----------+------+-----------------+------------+-----------+-------+
```

从上面可以看出，在查询时，由于bookinfo表中有重复的两条，即根据ON条件，publisher_id有2个为8的，所以在返回结果中会出现两条重复的数据，要注意这个问题。

### 3.1.2 多表连结查询

为了能够看出多表连结时ON的选用条件不一样，导致的查询结果也不一致的情况，在author表中添加一条数据

```
INSERT INTO `author`(`author_id`, `name`) VALUES (9, '钱钟书');
```

测试结果3：

```
mysql> SELECT * FROM publisher pub LEFT JOIN bookinfo book ON book.publisher_id=pub.publisher_id
    -> LEFT JOIN author auth ON auth.author_id=book.author_id;
+--------------+--------------------------------+------+--------------------------------+--------------+-----------+-------+-----------+-----------------+
| publisher_id | name                           | id   | name                           | publisher_id | author_id | price | author_id | name            |
+--------------+--------------------------------+------+--------------------------------+--------------+-----------+-------+-----------+-----------------+
|            1 | 经济日报出版社                 |    1 | 《伯罗奔尼撒战争史》           |            1 |         1 |    11 |         1 | 修昔底德        |
|            2 | 外语教学与研究出版社           |    2 | 《茶花女》                     |            2 |         2 |    22 |         2 | 小仲马          |
|            3 | 中国纺织出版社                 |    3 | 《对话录》                     |            3 |         3 |    22 |         3 | 柏拉图          |
|            4 | 编译出版社                     |    4 | 《伦理学》                     |            4 |         4 |    44 |         4 | 亚里士多德      |
|            5 | 中国言实出版社                 |    5 | 《埃涅阿斯记》                 |            5 |         5 |    55 |         5 | 维吉尔          |
|            6 | 东方出版社                     |    6 | 《希腊罗马英雄史》             |            6 |         6 |    66 |         6 | 普鲁塔克        |
|            7 | 北京工艺美术出版社             |    7 | 《共产党人的故事》             |            7 |         7 |    77 |         7 | 徐焰            |
|            8 | 文津出版社                     |    8 | 《四世同堂》                   |            8 |         8 |    88 |         8 | 老舍            |
|            8 | 文津出版社                     |    9 | 《四世同堂》                   |            8 |         8 |    88 |         8 | 老舍            |
|            9 | 新华出版社                     | NULL | NULL                           |         NULL |      NULL |  NULL |      NULL | NULL            |
+--------------+--------------------------------+------+--------------------------------+--------------+-----------+-------+-----------+-----------------+
10 rows in set (0.00 sec)
```

上面示例的连结条件是：

以publisher为主表，LEFT JOIN首先连结bookinfo，连结条件book.publisher_id=pub.publisher_id，此时得到的一个表（假使有这个表，实际不会有这个步骤存在）

![image](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/9859ED717ACE4EA68F9608D605E974AA/13930)

然后再用这个中间的大表作为主表，再LEFT JOIN author 表，ON auth.author_id=book.author_id，因为中间表此时是主表，再以这个表的book.author_id去查找，本身自己就是NULL,所以查找的结果还是NULL,见下图

![最终左连结结果](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/54C6D4F680A745918BAD054B4410DC01/13933)

对应的模型如下：

![模型图](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/5EB39E524BBD41BA9F594B4EAC81D670/13935)

测试结果4：

```
mysql> SELECT * FROM publisher pub LEFT JOIN bookinfo book ON book.publisher_id=pub.publisher_id
    -> LEFT JOIN author auth ON auth.author_id=pub.publisher_id;
+--------------+--------------------------------+------+--------------------------------+--------------+-----------+-------+-----------+-----------------+
| publisher_id | name                           | id   | name                           | publisher_id | author_id | price | author_id | name            |
+--------------+--------------------------------+------+--------------------------------+--------------+-----------+-------+-----------+-----------------+
|            1 | 经济日报出版社                 |    1 | 《伯罗奔尼撒战争史》           |            1 |         1 |    11 |         1 | 修昔底德        |
|            2 | 外语教学与研究出版社           |    2 | 《茶花女》                     |            2 |         2 |    22 |         2 | 小仲马          |
|            3 | 中国纺织出版社                 |    3 | 《对话录》                     |            3 |         3 |    22 |         3 | 柏拉图          |
|            4 | 编译出版社                     |    4 | 《伦理学》                     |            4 |         4 |    44 |         4 | 亚里士多德      |
|            5 | 中国言实出版社                 |    5 | 《埃涅阿斯记》                 |            5 |         5 |    55 |         5 | 维吉尔          |
|            6 | 东方出版社                     |    6 | 《希腊罗马英雄史》             |            6 |         6 |    66 |         6 | 普鲁塔克        |
|            7 | 北京工艺美术出版社             |    7 | 《共产党人的故事》             |            7 |         7 |    77 |         7 | 徐焰            |
|            8 | 文津出版社                     |    8 | 《四世同堂》                   |            8 |         8 |    88 |         8 | 老舍            |
|            8 | 文津出版社                     |    9 | 《四世同堂》                   |            8 |         8 |    88 |         8 | 老舍            |
|            9 | 新华出版社                     | NULL | NULL                           |         NULL |      NULL |  NULL |         9 | 钱钟书          |
+--------------+--------------------------------+------+--------------------------------+--------------+-----------+-------+-----------+-----------------+
10 rows in set (0.00 sec)
```

上面示例的连结条件是：

以publisher为主表，LEFT JOIN首先连结bookinfo，连结条件book.publisher_id=pub.publisher_id，此时得到的一个表，如下

![image](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/9859ED717ACE4EA68F9608D605E974AA/13930)

但是再第二次连结author的时候，继续以publisher表为主表，进行ON auth.author_id=pub.publisher_id，这时候得到的结果如下：

![结果图片2](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/E3AF61753F0E45249E70C02238B248B4/13938)

注意此时连结后的author信息不为空了。

对应的连结模型如下：

![模型2](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/35D85B4E70464AF89699C7572B538B0B/13941)

**总结**：这说明在连结的时候，不同的表作为连结条件，获得的结果会不一样，同时主表的数据会全部展示，但是也可能会多，有可能连结表有重复数据，需要注意。

## 3.2 RIGHT JOIN 右连结查询

**用法**：SELECT XXX  FROM left_table  **RIGHT JOIN** main_table **ON** left_table.key=main_table.key

**解析**：RIGHT JOIN ... ON   

RIGHT JOIN 关键字会右表 (main_table) 那里返回所有的行，即使在左表 ( left_table) 中没有匹配的行。

**注意**：在LEFT JOIN中，左边的表为主表，但是在RIGHT JOIN中，RIGHT JOIN哪个表，哪个表就作为主表，会返回所有的行

### 3.2.1 基本使用示例

这个操作还是在上面表的基础上进行的

```
mysql> SELECT * FROM publisher;
+--------------+--------------------------------+
| publisher_id | name                           |
+--------------+--------------------------------+
|            1 | 经济日报出版社                 |
|            2 | 外语教学与研究出版社           |
|            3 | 中国纺织出版社                 |
|            4 | 编译出版社                     |
|            5 | 中国言实出版社                 |
|            6 | 东方出版社                     |
|            7 | 北京工艺美术出版社             |
|            8 | 文津出版社                     |
|            9 | 新华出版社                     |
+--------------+--------------------------------+
9 rows in set (0.00 sec)


mysql> SELECT * FROM bookinfo;
+----+--------------------------------+--------------+-----------+-------+
| id | name                           | publisher_id | author_id | price |
+----+--------------------------------+--------------+-----------+-------+
|  1 | 《伯罗奔尼撒战争史》           |            1 |         1 |    11 |
|  2 | 《茶花女》                     |            2 |         2 |    22 |
|  3 | 《对话录》                     |            3 |         3 |    22 |
|  4 | 《伦理学》                     |            4 |         4 |    44 |
|  5 | 《埃涅阿斯记》                 |            5 |         5 |    55 |
|  6 | 《希腊罗马英雄史》             |            6 |         6 |    66 |
|  7 | 《共产党人的故事》             |            7 |         7 |    77 |
|  8 | 《四世同堂》                   |            8 |         8 |    88 |
|  9 | 《四世同堂》                   |            8 |         8 |    88 |
+----+--------------------------------+--------------+-----------+-------+
9 rows in set (0.00 sec)


```

示例使用的SQL如下：

```
SELECT * FROM bookinfo book RIGHT JOIN publisher pub ON book.publisher_id=pub.publisher_id;
```

查询结果如下：

![查询结果2](https://note.youdao.com/yws/public/resource/08f2d93624c3197b2b490e255cfcf723/xmlnote/A98AB8B45D04473BBB11B36E9224F4E5/13943)

**总结**：RIGHT JOIN查询的时候用被连结的表publisher作为主表，返回所有数据，RIGHT JOIN左边的就是从表，如果没有匹配到的就展示为NULL.

**其他**：RIGHT JOIN的多表查询情况跟LEFT JOIN类似，这里不再赘述

> [参考博客](https://www.w3school.com.cn/sql/sql_join_right.asp)  
>
> [参考博客](https://blog.csdn.net/lp_cq242/article/details/79942457)  
>
> [参考博客](https://www.cnblogs.com/bad-robot/p/9788959.html)  