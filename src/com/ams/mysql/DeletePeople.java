package com.ams.mysql;

import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletePeople {
    public DeletePeople(People p) throws SQLException {
        Conn c = new Conn();
        String people;
        if(p instanceof Student){
            people = "student";
        } else if (p instanceof Teacher) {
            people = "teacher";
        }else {
            return;
        }
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root","money","ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集

        String sql = "delete from " + people + " where id = '" + p.id + "' and name = '" + p.name + "'";
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
