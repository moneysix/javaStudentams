package com.ams.mysql;

import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindPeople {
    public People find(People p) throws SQLException {
        //        1.创建连接对象
        Conn c = new Conn();
        String sql = "";
        if(p instanceof Student){
            sql = "select * from student where id = " + p.id;
        } else if (p instanceof Teacher) {
            sql = "select * from teacher where id = " + p.id;
        }else {
            return null;
        }
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集

        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            p.id = resultSet.getString(1);
            p.password = resultSet.getString(2);
            p.name = resultSet.getString(3);
            p.age = resultSet.getInt(4);
            p.gender = resultSet.getString(5);
            p.college = resultSet.getString(6);
            if(p instanceof Student) {
                ((Student) p)._class = resultSet.getString(7);
            }
        }
//        5.释放连接
        resultSet.close();
        statement.close();
        connection.close();
        return p;
    }
}
