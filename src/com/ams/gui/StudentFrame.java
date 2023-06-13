package com.ams.gui;

import com.ams.course.Course;
import com.ams.course.Grade;
import com.ams.people.People;
import com.ams.people.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentFrame extends JFrame implements ActionListener {
    private JButton checkAllGrade,alterPassword;
    private JLabel statusLabel;
    private JTable table;
    private Student student;
    public StudentFrame(Student student){
        this.student = student;
        init();
        setSize(800,1000);
        setTitle("学生界面");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        checkAllGrade.addActionListener(this);
//        checkOneGrade.addActionListener(this);
        alterPassword.addActionListener(this);
    }
    void init(){
        JPanel panel = new JPanel();
        checkAllGrade = new JButton("查询所有成绩");
//        checkOneGrade = new JButton("查询单科成绩");
        alterPassword = new JButton("修改密码");
        statusLabel = new JLabel("");
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        checkAllGrade.setPreferredSize(new Dimension(200,30));
//        checkOneGrade.setPreferredSize(new Dimension(200,30));
        alterPassword.setPreferredSize(new Dimension(200,30));
        panel.add(checkAllGrade);
//        panel.add(checkOneGrade);
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
        if (e.getSource() == checkAllGrade){
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"学号","课程","分数"});
            try {
                Grade[] grades = student.checkGrade(new Course());
                if (grades[0] == null){
                    statusLabel.setText("查询无结果！可能是您还没有被添加任何学习课程！");
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
        } else if (e.getSource() == alterPassword) {
            String s = alter();
            if(s != null){
                student.password = s;
                System.out.println(student.toString());
                try {
                    student = (Student) student.alterPassword(student);
                    if(student != null){
                        statusLabel.setText("修改密码成功！新密码：" + s);
                        validate();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
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
