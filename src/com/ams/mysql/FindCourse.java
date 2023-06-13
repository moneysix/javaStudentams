package com.ams.mysql;

import com.ams.course.Course;
import com.ams.course.Grade;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindCourse {
    public Course[] FindCou(Teacher teacher) throws SQLException {
        int i = 0;
        Course[] courses = new Course[50];
        //        1.创建连接对象
        Conn c = new Conn();
        String sql = "select * from Course where Tid = '" + teacher.id + "'";

//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet == null){
            System.out.println("查询失败！您暂无教学任务！");
            resultSet.close();
            statement.close();
            connection.close();
            return null;
        }
        while(resultSet.next()){
            courses[i] = new Course(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            System.out.println(courses[i]);
            i++;
        }
//        5.释放连接
        resultSet.close();
        statement.close();
        connection.close();
        return courses;
    }
}
