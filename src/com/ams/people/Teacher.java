package com.ams.people;

import com.ams.course.Grade;
import com.ams.mysql.*;
import com.ams.course.Course;
import com.ams.port.AlterPassword;
import com.ams.port.CheckGrade;

import java.sql.SQLException;
import java.util.Scanner;

public class Teacher extends People implements CheckGrade, AlterPassword {
    public String add(Course course) throws SQLException {
//        course.Course(this);
        new AddCourse(course);
        return "success";
    }
    public String delete(Course course) throws SQLException {
        new DeleteCourse(this,course);
        return "success";
    }
    public Course[] find() throws SQLException {
        return new FindCourse().FindCou(this);
    }
    //    只在main方式运行时调用
    public String enterGrade(Course course) throws SQLException {
        System.out.println("#--请输入需要录入成绩的班级以及课程名--#");
        Scanner s = new Scanner(System.in);
        System.out.print("课程名：");
        course.Cname = s.next();
        System.out.print("班级：");
        course._class = s.next();
        new EnterGrade(this,course);
        return "success";
    }
    //    只在main方式运行时调用
    public String alterGrade(Course course,Student student) throws SQLException {
        System.out.println("#--请输入需要修改成绩的学生的学号以及课程名--#");
        Scanner s = new Scanner(System.in);
        System.out.print("学号：");
        student.id = s.next();
        System.out.print("课程名：");
        course.Cname = s.next();
        new AlterGrade(this,course,student);
        return "success";
    }
    @Override
    public Grade[] checkGrade(Course course) throws SQLException {
        return new com.ams.mysql.CheckGrade().checkGrade(this,course);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", college='" + college + '\'' +
                '}';
    }

    @Override
    public People alterPassword(People people) throws SQLException {
        return (Teacher) new AlterPass().alterPass(people);
    }
}
