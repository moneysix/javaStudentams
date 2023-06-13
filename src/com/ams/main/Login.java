package com.ams.main;

import com.ams.course.Course;
import com.ams.mysql.LoginPeople;
import com.ams.people.Admin;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public Login() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.println("登陆的身份：");
        System.out.println("1.学生");
        System.out.println("2.教师");
        System.out.println("3.管理员");
        int n = s.nextInt();
        switch (n) {
            case 1 -> {
                Student student = new Student();
                System.out.print("请输入学号：");
                student.id = s.next();
                System.out.print("请输入密码：");
                student.password = s.next();
                student = (Student) new LoginPeople().LoginPeo(student);
                if (student != null) {
                    System.out.println("yes");
                    System.out.println(student.toString());
                    System.out.println("1.查询全部成绩");
                    System.out.println("2.查询单科成绩");
                    System.out.println("3.修改密码");
                    System.out.println("0.退出");
                    n = s.nextInt();
                    while (n != 0) {
                        if (n == 1) {
                            student.checkGrade(new Course());
                        } else if (n == 2) {
                            student.checkGrade(new Course());
                        } else if (n == 3) {
                            student.alterPassword(new Student());   //此处传入的为null，main包运行需要修改这里给一个非空的实例对象
                        }
                        System.out.println("请选择：");
                        System.out.println("1.查询全部成绩");
                        System.out.println("2.查询单科成绩");
                        System.out.println("3.修改密码");
                        System.out.println("0.退出");
                        n = s.nextInt();
                    }
                } else {
                    System.out.println("no");
                }
            }
            case 2 -> {
                Teacher teacher = new Teacher();
                System.out.print("请输入教工号：");
                teacher.id = s.next();
                System.out.print("请输入密码：");
                teacher.password = s.next();
                teacher = (Teacher) new LoginPeople().LoginPeo(teacher);
                if (teacher != null) {
                    System.out.println("yes");
                    System.out.println(teacher.toString());
                    System.out.println("请选择：");
                    System.out.println("1.添加课程");
                    System.out.println("2.删除课程");
                    System.out.println("3.查询负责的课程");
                    System.out.println("4.查询学生成绩");
                    System.out.println("5.录入学生成绩");
                    System.out.println("6.修改学生成绩");
                    System.out.println("7.修改密码");
                    System.out.println("0.退出");
                    n = s.nextInt();
                    while (n != 0) {
                        if (n == 1) {
                            teacher.add(new Course());
                        } else if (n == 2) {
                            teacher.delete(new Course());
                        } else if (n == 3) {
                            teacher.find();
                        } else if (n == 4) {
                            teacher.checkGrade(new Course());
                        } else if (n == 5) {
                            teacher.enterGrade(new Course());
                        } else if (n == 6) {
                            teacher.alterGrade(new Course(), new Student());
                        } else if (n == 7) {
                            teacher.alterPassword(new Teacher());   //此处传入的为null，main包运行需要修改这里给一个非空的实例对象
                        }
                        System.out.println("请选择：");
                        System.out.println("1.添加课程");
                        System.out.println("2.删除课程");
                        System.out.println("3.查询负责的课程");
                        System.out.println("4.查询学生成绩");
                        System.out.println("5.录入学生成绩");
                        System.out.println("6.修改学生成绩");
                        System.out.println("7.修改密码");
                        System.out.println("0.退出");
                        n = s.nextInt();
                    }
                } else {
                    System.out.println("no");
                }
            }
            case 3 -> {
                Admin admin = new Admin();
                System.out.print("请输入管理员账号：");
                admin.id = s.next();
                System.out.print("请输入密码：");
                admin.password = s.next();
                admin = (Admin) new LoginPeople().LoginPeo(admin);
                if (admin != null) {
                    System.out.println(admin.toString());
                    System.out.println("请选择：");
                    System.out.println("1.添加用户");
                    System.out.println("2.删除用户");
                    System.out.println("3.查询用户");
                    System.out.println("0.退出");
                    n = s.nextInt();
                    while (n != 0) {
                        if (n == 1) {
                            System.out.println("请选择：");
                            System.out.println("1.添加学生用户");
                            System.out.println("2.添加教师用户");
                            n = s.nextInt();
                            if (n == 1) {
                                admin.addPeople(new Student());
                            } else if (n == 2) {
                                admin.addPeople(new Teacher());
                            }
                        } else if (n == 2) {
                            System.out.println("请选择：");
                            System.out.println("1.删除学生用户");
                            System.out.println("2.删除教师用户");
                            n = s.nextInt();
                            if (n == 1) {
                                admin.deletePeople(new Student());
                            } else if (n == 2) {
                                admin.deletePeople(new Teacher());
                            }
                        } else if (n == 3) {
                            System.out.println("请选择：");
                            System.out.println("1.查询学生用户");
                            System.out.println("2.查询教师用户");
                            n = s.nextInt();
                            if (n == 1) {
                                admin.findPeople(new Student());
                            } else if (n == 2) {
                                admin.findPeople(new Teacher());
                            }
                        }
                        System.out.println("请选择：");
                        System.out.println("1.添加用户");
                        System.out.println("2.删除用户");
                        System.out.println("3.查询用户");
                        System.out.println("0.退出");
                        n = s.nextInt();
                    }
                } else {
                    System.out.println("no");
                }
            }
        }
    }
}
