package com.ams.gui;


import com.ams.course.Course;
import com.ams.course.Grade;
import com.ams.mysql.Conn;
import com.ams.people.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TeacherFrame extends JFrame implements ActionListener {
    private JButton addCourse,deleteCourse,findCourse,findGrade,enterGrade,alterGrade,alterPassword;
    private JLabel statusLabel;
    private JTable table;
    private Teacher teacher;
    public TeacherFrame(Teacher teacher){
        this.teacher = teacher;
        init();
        setSize(800,1000);
        setTitle("教师界面");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addCourse.addActionListener(this);
        deleteCourse.addActionListener(this);
        findCourse.addActionListener(this);
        findGrade.addActionListener(this);
        enterGrade.addActionListener(this);
        alterGrade.addActionListener(this);
        alterPassword.addActionListener(this);
    }
    void init(){
        JPanel panel = new JPanel();
        addCourse = new JButton("添加课程");
        deleteCourse = new JButton("删除课程");
        findCourse = new JButton("查询负责的课程");
        findGrade = new JButton("查询学生成绩");
        enterGrade = new JButton("录入学生成绩");
        alterGrade = new JButton("修改学生成绩");
        alterPassword = new JButton("修改密码");
        statusLabel = new JLabel("");
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        addCourse.setPreferredSize(new Dimension(200,30));
        deleteCourse.setPreferredSize(new Dimension(200,30));
        findCourse.setPreferredSize(new Dimension(200,30));
        findGrade.setPreferredSize(new Dimension(200,30));
        enterGrade.setPreferredSize(new Dimension(200,30));
        alterGrade.setPreferredSize(new Dimension(200,30));
        alterPassword.setPreferredSize(new Dimension(200,30));
        panel.add(addCourse);
        panel.add(deleteCourse);
        panel.add(findCourse);
        panel.add(findGrade);
        panel.add(enterGrade);
        panel.add(alterGrade);
        panel.add(alterPassword);
        panel.add(statusLabel);
        panel.add(scrollPane);
        add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        statusLabel.setText("");
        validate();
//        添加课程
        if (e.getSource() == addCourse){
            Course course = new Course();
            try {
                teacher.add(addC(course));
                statusLabel.setText("添加成功,新添加的课程为" + course.toString());
                validate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        删除课程
        else if (e.getSource() == deleteCourse) {
            Course course = new Course();
            try {
                teacher.delete(deleteC(course));
                statusLabel.setText("删除成功,删除的课程为" + course.toString());
                validate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        查询课程
        else if (e.getSource() == findCourse) {
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"教师号","课程","班级"});
            try {
                Course[] courses = teacher.find();
                if (courses[0] == null){
                    statusLabel.setText("查询无结果！可能是您还没有添加任何教学课程！");
                    validate();
                }
                for (Course c : courses) {
                    if (c != null) {
                        model.addRow(new Object[]{c.Tid, c.Cname, c._class});
                        System.out.println(c.Tid + c.Cname + c._class);
                    }
                }
                table.setModel(model);
                setVisible(true);
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        查询成绩
        else if (e.getSource() == findGrade) {
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"学号","课程","分数"});
            try {
                Grade[] grades = teacher.checkGrade(checkG(new Course()));
                if (grades[0] == null){
                    statusLabel.setText("查询无结果！可能是您还没有添加任何教学课程！");
                    validate();
                }
                for(Grade g : grades){
                    if(g != null){
                        model.addRow(new Object[]{g.id,g.name,g.score});
                        System.out.println(g.id+g.name+g.score);
                    }

                }
                table.setModel(model);
                setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        录入成绩
        else if (e.getSource() == enterGrade) {
            Course course;
            course = enterG(new Course());
//        1.创建连接对象
            Conn c = new Conn();
//        先查询这个老师有没有这个课
            String sql1 = "select * from course where Tid = '" + teacher.id + "' and Cname = '" + course.Cname + "' and class = '" + course._class + "'";
            String sql2 = "select Sid,name from grade,student where id = Sid and class = '" + course._class + "' and cname = '" + course.Cname + "'";
//        2.连接成功，数据库对象 Connection
            Connection connection = c.getConnection("root", "money", "ams");
//        3.执行SQL对象Statement，执行SQL的对象
            Statement statement1;
            try {
                statement1 = connection.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Statement statement2;
            try {
                statement2 = connection.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Statement statement3;
            try {
                statement3 = connection.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
//        4.执行SQL的对象去执行SQL，返回结果集


            ResultSet resultSet1;
            try {
                resultSet1 = statement1.executeQuery(sql1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ResultSet resultSet2;
            try {
                resultSet2 = statement2.executeQuery(sql2);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet1.next()) {
                    double score;
                    while(resultSet2.next()){
                        score = Double.parseDouble(JOptionPane.showInputDialog(this,resultSet2.getString(1) + "," + resultSet2.getString(2) + "," + course.Cname + "的分数是:"));
                        String sql3 = "update grade set score = " + score + " where Sid = '" + resultSet2.getString(1) + "' and Cname = '" + course.Cname + "'";
                        if(statement3.executeUpdate(sql3) >= 1){
                            statusLabel.setText("录入成功！");
                            validate();
                        }else {
                            statusLabel.setText("录入失败！");
                            validate();
                        }
                    }
                } else {
                    statusLabel.setText("查询不到您的教学任务中有这门课或这个班！");
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

//        5.释放连接
            try {
                if (!resultSet1.isClosed()) {
                    resultSet1.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (!resultSet2.isClosed()){
                    resultSet2.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


            try {
                statement1.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                statement2.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                statement3.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        修改成绩
        else if (e.getSource() == alterGrade) {
            String sid = JOptionPane.showInputDialog(this,"请输入需要修改成绩的学生学号");
            String cname = JOptionPane.showInputDialog(this,"请输入对应的课程名");

            //        1.创建连接对象
            Conn c = new Conn();
//        先查询这个老师有没有这个学生
            String sql1 = "select * from course,student where Cname = '" + cname + "' and course.class = student.class and id = '" + sid + "'";
//        2.连接成功，数据库对象 Connection
            Connection connection = c.getConnection("root", "money", "ams");
//        3.执行SQL对象Statement，执行SQL的对象
            Statement statement1;
            try {
                statement1 = connection.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Statement statement2;
            try {
                statement2 = connection.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
//        4.执行SQL的对象去执行SQL，返回结果集

            ResultSet resultSet1;
            try {
                resultSet1 = statement1.executeQuery(sql1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet1.next()) {
                    double score;
                    score = Double.parseDouble(JOptionPane.showInputDialog(this,"请输入新的成绩"));
                    String sql2 = "update grade set score = " + score + " where Sid = '" + sid + "' and cname = '" + cname + "'" ;
                    if (statement2.executeUpdate(sql2) >= 1){
                        statusLabel.setText("修改成功！");
                        validate();
                    }else {
                        statusLabel.setText("修改失败！");
                        validate();
                    }
                } else {
                    statusLabel.setText("查询不到该学生！");
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

//        5.释放连接
            try {
                if (!resultSet1.isClosed()) {
                    resultSet1.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                statement1.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                statement2.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//        修改密码
        else if (e.getSource() == alterPassword) {
            String s = alter();
            if(s != null){
                teacher.password = s;
                System.out.println(teacher.toString());
                try {
                    teacher = (Teacher) teacher.alterPassword(teacher);
                    if(teacher != null){
                        statusLabel.setText("修改密码成功！新密码：" + s);
                        validate();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private Course addC(Course course){
        course.Tid = teacher.id;
        course.Cname = JOptionPane.showInputDialog(this,"请输入新加的课程名");
        course._class = JOptionPane.showInputDialog(this,"请输入教学的班级");
        return course;
    }
    private Course deleteC(Course course){
        course.Tid = teacher.id;
        course.Cname = JOptionPane.showInputDialog(this,"请输入删除的课程名");
        course._class = JOptionPane.showInputDialog(this,"请输入课程对应的班级");
        return course;
    }
    private Course checkG(Course course){
        course.Tid = teacher.id;
        course._class = JOptionPane.showInputDialog(this,"请输入需要查询成绩的班级");
        course.Cname = JOptionPane.showInputDialog(this,"请输入要查询的课程名");
        return course;
    }
    private Course enterG(Course course){
        course.Tid = teacher.id;
        course._class = JOptionPane.showInputDialog(this,"请输入需要录入成绩的班级");
        course.Cname = JOptionPane.showInputDialog(this,"请输入要录入的课程名");
        return course;
    }
    private String alter(){
        String s1 = JOptionPane.showInputDialog(this,"请输入新密码");
        String s2 = JOptionPane.showInputDialog(this,"请再次输入新密码");
        if(s1.equals(s2)){
            return s1;
        }else{
            return null;
        }
    }
}
