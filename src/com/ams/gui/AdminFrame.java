package com.ams.gui;

import com.ams.people.Admin;
import com.ams.people.People;
import com.ams.people.Student;
import com.ams.people.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminFrame extends JFrame implements ActionListener {
    private JButton addStudent,deleteStudent,findStudent,addTeacher,deleteTeacher,findTeacher;
    private JLabel statusLabel;
    private Admin admin;
    private Teacher teacher;
    private Student student;
    public AdminFrame(Admin admin){
        this.admin = admin;
        init();
        setSize(800,1000);
        setTitle("管理员界面");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addStudent.addActionListener(this);
        deleteStudent.addActionListener(this);
        findStudent.addActionListener(this);
        addTeacher.addActionListener(this);
        deleteTeacher.addActionListener(this);
        findTeacher.addActionListener(this);
    }
    void init(){
//        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        addStudent = new JButton("添加学生用户！");
        addTeacher = new JButton("添加教师用户！");
        deleteStudent = new JButton("删除学生用户！");
        deleteTeacher = new JButton("删除教师用户！");
        findStudent = new JButton("查询学生用户！");
        findTeacher = new JButton("查询教师用户！");
        statusLabel = new JLabel("");
        addStudent.setPreferredSize(new Dimension(200,30));
        addTeacher.setPreferredSize(new Dimension(200,30));
        deleteStudent.setPreferredSize(new Dimension(200,30));
        deleteTeacher.setPreferredSize(new Dimension(200,30));
        findStudent.setPreferredSize(new Dimension(200,30));
        findTeacher.setPreferredSize(new Dimension(200,30));
        panel.add(addStudent);
        panel.add(addTeacher);
        panel.add(deleteStudent);
        panel.add(deleteTeacher);
        panel.add(findStudent);
        panel.add(findTeacher);
        panel.add(statusLabel);
        add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        statusLabel.setText("");
        validate();
        if (e.getSource() == addStudent){
            student = (Student) addition(new Student());
            try {
                if (admin.addPeople(student).equals("success")){
                    statusLabel.setText("学生 " + student.name + "添加成功！默认密码为：123456");
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == addTeacher) {
            teacher = (Teacher) addition(new Teacher());
            try {
                if (admin.addPeople(teacher).equals("success")){
                    statusLabel.setText("教师 " + teacher.name + " 添加成功！默认密码为：123456");
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == deleteStudent) {
            student =(Student) delete(new Student());
            try {
                if(admin.deletePeople(student).equals("success")){
                    statusLabel.setText("学生 " + student.id + " " + student.name + "删除成功！");
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == deleteTeacher) {
            teacher =(Teacher) delete(new Teacher());
            try {
                if(admin.deletePeople(teacher).equals("success")){
                    statusLabel.setText("教师 " + teacher.id + " " + teacher.name + "删除成功！");
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == findStudent) {
            student =(Student) find(new Student());
            try {
                student = (Student) admin.findPeople(student);
                if(student != null){
                    statusLabel.setText(student.toString());
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == findTeacher) {
            teacher =(Teacher) find(new Teacher());
            try {
                teacher = (Teacher) admin.findPeople(teacher);
                if(teacher != null){
                    statusLabel.setText(teacher.toString());
                    validate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private People addition(People people){
        if (people instanceof Student){
            people.id = JOptionPane.showInputDialog(this,"学号");
            people.name = JOptionPane.showInputDialog(this,"名字");
            people.age = Integer.parseInt(JOptionPane.showInputDialog(this,"年龄"));
            people.gender = JOptionPane.showInputDialog(this,"性别");
            people.college = JOptionPane.showInputDialog(this,"学院");
            ((Student) people)._class = JOptionPane.showInputDialog(this,"班级");
        } else if (people instanceof Teacher) {
            people.id = JOptionPane.showInputDialog(this,"教工号");
            people.name = JOptionPane.showInputDialog(this,"名字");
            people.age = Integer.parseInt(JOptionPane.showInputDialog(this,"年龄"));
            people.gender = JOptionPane.showInputDialog(this,"性别");
            people.college = JOptionPane.showInputDialog(this,"学院");
        }
        people.password = "123456";
        return people;
    }
    private People delete(People people){
        if (people instanceof Student){
            people.id = JOptionPane.showInputDialog(this,"请输入需要删除学生的学号");
            people.name = JOptionPane.showInputDialog(this,"请输入需要删除学生的名字");
        } else if (people instanceof Teacher) {
            people.id = JOptionPane.showInputDialog(this,"请输入需要删除教师的教工号");
            people.name = JOptionPane.showInputDialog(this,"请输入需要删除教师的名字");
        }
        return people;
    }
    private People find(People people){
        if (people instanceof Student){
            people.id = JOptionPane.showInputDialog(this,"请输入需要查询学生的学号");
        } else if (people instanceof Teacher) {
            people.id = JOptionPane.showInputDialog(this,"请输入需要查询教师的教工号");
        }
        return people;
    }
}
