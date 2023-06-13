package com.ams.mysql;

import com.ams.course.Course;
import com.ams.people.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EnterGrade {
    public EnterGrade(Teacher teacher,Course course) throws SQLException {
        //        1.创建连接对象
        Conn c = new Conn();
//        先查询这个老师有没有这个课
        String sql1 = "select * from course where Tid = '" + teacher.id + "' and Cname = '" + course.Cname + "' and class = '" + course._class + "'";
        String sql2 = "select Sid,name from grade,student where id = Sid and class = '" + course._class + "' and cname = '" + course.Cname + "'";
//        2.连接成功，数据库对象 Connection
        Connection connection = c.getConnection("root", "money", "ams");
//        3.执行SQL对象Statement，执行SQL的对象
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
//        4.执行SQL的对象去执行SQL，返回结果集



        ResultSet resultSet1 = statement1.executeQuery(sql1);
        ResultSet resultSet2 = statement2.executeQuery(sql2);
        if (resultSet1.next()) {
            System.out.println("#--请依次录入以下学生的成绩--#");
            Scanner scanner = new Scanner(System.in);
            double score;
            while(resultSet2.next()){
                System.out.print(resultSet2.getString(1) + "," + resultSet2.getString(2) + "," + course.Cname + "的分数是:");
                score = scanner.nextDouble();
                String sql3 = "update grade set score = " + score + " where Sid = '" + resultSet2.getString(1) + "' and Cname = '" + course.Cname + "'";
                if(statement3.executeUpdate(sql3) >= 1){
                    System.out.println("录入成功");
                }else {
                    System.out.println("录入失败");
                }
            }
        } else {
            System.out.println("查询不到您的教学任务中有这门课或这个班！");
        }

//        5.释放连接
        if (!resultSet1.isClosed()) {
            resultSet1.close();
        }
        if (!resultSet2.isClosed()){
            resultSet2.close();
        }


        statement1.close();
        statement2.close();
        statement3.close();
        connection.close();
    }
}
