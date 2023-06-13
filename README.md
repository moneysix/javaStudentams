# jcdb+mysql简易学生成绩管理系统



## 用户：

管理员（Admin）

教师（Teacher）

学生（Student）



## 功能：登陆界面



***ps：每个功能输入数据都只做了简单筛选，尽量避免乱输数据或不输数据，这种操作会导致页面提示成功操作，但是数据库并没有更新数据***

![image-20230613105817583](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105817583.png)

### 管理员登录：

添加用户
删除用户
查询用户

![image-20230613105837764](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105837764.png)



### 教师登录：

添加课程

删除课程
查询课程
录入学生成绩
修改学生成绩
查询全班成绩
修改密码

![image-20230613105944885](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105944885.png)

### 学生登录：

查询成绩
修改密码

![image-20230613105906171](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105906171.png)





## 数据库设置



### 导入jcdb依赖，右键->添加到库

![image-20230613105126786](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105126786.png)

### mysql建表语句：

```mysql
create database ams

use ams

create table Course(
    Tid char(20) not null,
    Cname char(20) not null ,
    class char(20) not null
)

create table Teacher(
    id char(20) not null primary key ,
    password char(20) not null ,
    name char(20) not null,
    age int not null ,
    gender char(8) not null ,
    college char(20) not null
)

create table student(
    id char(20) not null primary key ,
    password char(20) not null ,
    name char(20) not null,
    age int not null ,
    gender char(8) not null ,
    college char(20) not null ,
    class char(20) not null
)

create table admin(
    id char(20) not null primary key ,
    password char(20) not null
)

insert into admin values ('admin','123456');

create table Grade(
    Sid char(20) not null primary key ,
    Cname char(20) not null,
    score double
)
```

### 连接电脑本地数据库类：（localhost:3306）

![image-20230613104750949](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613104750949.png)

### 参数分别为数据库用户名、密码、数据库名

![image-20230613104820885](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613104820885.png)



### 程序入口



##### 命令行测试类Test

![image-20230613105332058](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105332058.png)

##### Main类Java简易图形化界面

![image-20230613105659519](C:\Users\钱运通\AppData\Roaming\Typora\typora-user-images\image-20230613105659519.png)



