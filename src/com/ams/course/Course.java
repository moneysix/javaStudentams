package com.ams.course;

import com.ams.people.People;
import com.ams.people.Teacher;

import java.util.Scanner;

public class Course {
    public String Tid;
    public String Cname;
    public String _class;

    public Course() {
    }

    public Course(String tid, String cname, String _class) {
        Tid = tid;
        Cname = cname;
        this._class = _class;
    }

    public void Course(Teacher t){
        Scanner s = new Scanner(System.in);
        System.out.println("#--请输入课程信息--#");
        System.out.print("课程名称：");
        Cname = s.next();
        System.out.print("请输入选修该门课程的班级：");
        _class = s.next();
        Tid = t.id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Tid='" + Tid + '\'' +
                ", Cname='" + Cname + '\'' +
                ", _class='" + _class + '\'' +
                '}';
    }
}
