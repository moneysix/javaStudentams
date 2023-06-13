package com.ams.mysql;

import com.ams.course.Course;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteCourse {
    public DeleteCourse(Teacher teacher, Course course) throws SQLException {
        //        1.创建连接对象
        Conn c = new Conn();
        String s = "','";
        String l = course.Tid + s + course.Cname + s + course._class;
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集

        String sql = "delete from Course where Cname = '" + course.Cname + "' and Tid = '" + teacher.id + "' and class = '" + course._class + "'";
        System.out.println(sql);
        if(statement.executeUpdate(sql) >= 1){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
//        5.释放连接
        statement.close();
        connection.close();
    }
}
