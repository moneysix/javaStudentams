package com.ams.people;

import java.util.Scanner;

abstract public class People {
    public String id;
    public String password;
    public String name;
    public int age;
    public String gender;
    public String college;
//    abstract int check();
    public void createPeople(People p){
        Scanner s = new Scanner(System.in);
        System.out.println("请输入新建账号的身份信息：");
        if (p instanceof Student){
            System.out.print("学号：");
        }else {
            System.out.print("教师号：");
        }
        p.id = s.next();
        System.out.print("姓名：");
        p.name = s.next();
        System.out.print("年龄：");
        p.age = s.nextInt();
        System.out.print("性别:");
        p.gender = s.next();
        System.out.print("所在学院:");
        p.college = s.next();
        p.password = "123456";
        if(p instanceof Student){
            System.out.print("所在班级:");
            ((Student) p)._class = s.next();
        }
        System.out.println("登陆密码默认为123456！");
    }
}
