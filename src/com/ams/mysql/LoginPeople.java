package com.ams.mysql;

import com.ams.people.Admin;
import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPeople {
    public People LoginPeo(People p) throws SQLException {
        Conn c = new Conn();
        String sql = "";
        if (p instanceof Student){
            sql = "select * from student where id = '" + p.id + "' and password = '" + p.password + "'";
        } else if (p instanceof Teacher) {
            sql = "select * from teacher where id = '" + p.id + "' and password = '" + p.password + "'";
        }else if(p instanceof Admin){
            sql = "select * from admin where id = '" + p.id + "' and password = '" + p.password + "'";
        }
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集
        ResultSet resultSet = statement.executeQuery(sql);
        int n = 0;
        while(resultSet.next()){
            n = 1;
            if (p instanceof Admin){
                System.out.println("登陆成功！");
            }else{
                System.out.println("登陆成功！");
                p.name = resultSet.getString(3);
                p.age = resultSet.getInt(4);
                p.gender = resultSet.getString(5);
                p.college = resultSet.getString(6);
            }
            if (p instanceof Student){
                ((Student)p)._class = resultSet.getString(7);
            }
        }
//        5.释放连接
        resultSet.close();
        statement.close();
        connection.close();
        if(n == 1){
            return p;
        }else{
            return null;
        }
    }
}
