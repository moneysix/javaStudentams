package com.ams.mysql;

import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlterPass {
    public People alterPass(People people) throws SQLException {
        //        1.创建连接对象
        Conn c = new Conn();
        String sql;
        if (people instanceof Student){
            sql = "update student set password = '" + people.password + "' where id = '" + people.id + "'";
        } else if (people instanceof Teacher) {
            sql = "update teacher set password = '" + people.password + "' where id = '" + people.id + "'";
        }else{
            return null;
        }
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root", "money", "ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集
        if(statement.executeUpdate(sql) >= 1){
            System.out.println("修改密码成功！新密码为：" + people.password);
        }else {
            System.out.println("修改失败！");
        }

        statement.close();
        connection.close();
        return people;
    }
}
