package com.ams.mysql;

import com.ams.course.Course;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlterGrade {
    public AlterGrade(Teacher teacher, Course course, Student student) throws SQLException {
        //        1.创建连接对象
        Conn c = new Conn();
//        先查询这个老师有没有这个学生
        String sql1 = "select * from course,student where Cname = '" + course.Cname + "' and course.class = student.class and id = '" + student.id + "'";
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root", "money", "ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集

        ResultSet resultSet1 = statement1.executeQuery(sql1);
        if (resultSet1.next()) {
            System.out.println("#--请修改学生的成绩--#");
            Scanner scanner = new Scanner(System.in);
            double score;
            score = scanner.nextDouble();
            String sql2 = "update grade set score = " + score + " where Sid = '" + student.id + "' and cname = '" + course.Cname + "'";
            if (statement2.executeUpdate(sql2) >= 1){
                System.out.println("修改成功！");
            }else {
                System.out.println("修改失败！");
            }
        } else {
            System.out.println("查询不到这个学生！");
        }

//        5.释放连接
        if (!resultSet1.isClosed()) {
            resultSet1.close();
        }

        statement1.close();
        statement2.close();
        connection.close();
    }
}
