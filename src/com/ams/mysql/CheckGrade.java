package com.ams.mysql;

import com.ams.course.Course;
import com.ams.course.Grade;
import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.*;


public class CheckGrade {
    public Grade[] checkGrade(People p, Course course) throws SQLException {
        Grade[] grades = new Grade[50];
        int i = 0;
        Conn c = new Conn();
        String sql = "";
        if (p instanceof Student){
            sql = "select grade.* from grade,student where student.id = grade.Sid and sid = '" + p.id + "'";
        } else if (p instanceof Teacher) {
            sql = "select grade.* from student,Grade where Grade.Sid = Student.id and class = '" + course._class + "' and grade.Cname = '" + course.Cname + "'";
        }else{
            return null;
        }
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            grades[i] = new Grade(resultSet.getString(1),resultSet.getString(2),resultSet.getDouble(3));
            System.out.println(grades[i]);
            i++;
        }
//        5.释放连接
        resultSet.close();
        statement.close();
        connection.close();
        return grades;
    }
}

