package com.ams.mysql;

import com.ams.course.Course;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCourse {
    public AddCourse(Course course) throws SQLException {
        if (course.Cname == null){
            System.out.println("不能为空！");
            return;
        }
        //        1.创建连接对象
        Conn c = new Conn();
        String s = "','";
        String l = course.Tid + s + course.Cname + s + course._class;
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root", "money", "ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集

//       把新加的课程加入到数据库course表中
        String sql1 = "insert into course values ('" + l + "')";
        String sql2 = "select id from student where class = '" + course._class + "'";
        ResultSet resultSet = statement1.executeQuery(sql2);
        System.out.println(sql1);
        String sql3;
        if (statement.executeUpdate(sql1) >= 1) {
            System.out.println("添加成功！");

        } else {
            System.out.println("添加失败！");
        }
        while (resultSet.next()) {
            sql3 = "insert into grade(Sid,Cname) values ('" + resultSet.getString(1) + "','" + course.Cname + "')";
            if(statement.executeUpdate(sql3) >= 1){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        }

//        5.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
