package com.ams.mysql;

import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.*;

public class AddPeople {

    public AddPeople(People p) throws SQLException {
        //        1.创建连接对象
        Conn c = new Conn();
        String people = "";
        String s = "','";
        String l;
        if(p instanceof Student){
            people = "student";
            l = p.id + s + p.password + s + p.name + s + p.age + s + p.gender + s + p.college + s + ((Student) p)._class;
        } else if (p instanceof Teacher) {
            people = "teacher";
            l = p.id + s + p.password + s + p.name + s + p.age + s + p.gender + s + p.college;
        }else {
            return;
        }
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集

        String sql = "insert into " + people + " values ('" + l + "')";
        System.out.println(sql);
        if(statement.executeUpdate(sql) >= 1){
            System.out.println("添加成功！");
        }else{
            System.out.println("添加失败！");
        }
//        5.释放连接
        statement.close();
        connection.close();
    }

}

