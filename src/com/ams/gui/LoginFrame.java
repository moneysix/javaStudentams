package com.ams.gui;

import com.ams.mysql.LoginPeople;
import com.ams.people.Admin;
import com.ams.people.Student;
import com.ams.people.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;


public class LoginFrame extends JFrame implements ActionListener {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JLabel statusLabel;
    private String j = "管理员";

    public LoginFrame() {
        super("登录");

        // 创建组件
        JComboBox<String> jComboBox = new JComboBox<>(new String[]{"管理员","教师","学生"});

        JLabel usernameLabel = new JLabel("用户名:");
        JLabel passwordLabel = new JLabel("密  码:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("登录");
        statusLabel = new JLabel("");

        // 设置输入文本框和输入按钮大小和居中
        jComboBox.setPreferredSize(new Dimension(80,30));
        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));
        loginButton.setPreferredSize(new Dimension(100, 30));

        JPanel inputPanel = new JPanel();

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(jComboBox);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 10, 150));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 设置登录按钮居中
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 设置布局
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(inputPanel);
        panel.add(buttonPanel);
        panel.add(statusLabel);
        add(panel);

        // 添加事件监听器
        loginButton.addActionListener(this);

        jComboBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                j = (String)e.getItem();
            }
        });

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
//        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = new String(usernameField.getText());
        String password = new String(passwordField.getPassword());
        // 验证用户名和密码
        if (j.equals("管理员")){
            Admin admin = new Admin();
            admin.id = username;
            admin.password = password;
            try {
                admin =(Admin) new LoginPeople().LoginPeo(admin);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (admin != null){
                System.out.println("登陆成功！");
                System.out.println(admin.toString());
                statusLabel.setText(admin.toString());
                new AdminFrame(admin);
                setVisible(false);
            }else{
                statusLabel.setText("密码或用户名错误，请检查是否正确选择登录的身份！");
                validate();
            }

        } else if (j.equals("教师")) {
            Teacher teacher = new Teacher();
            teacher.id = username;
            teacher.password = password;
            try {
                teacher =(Teacher) new LoginPeople().LoginPeo(teacher);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (teacher != null){
                System.out.println("登陆成功！");
                System.out.println(teacher.toString());
                statusLabel.setText(teacher.toString());
                new TeacherFrame(teacher);
                setVisible(false);
            }else{
                statusLabel.setText("密码或用户名错误，请检查是否正确选择登录的身份！");
                validate();
            }
        } else if (j.equals("学生")) {
            Student student = new Student();
            student.id = username;
            student.password = password;
            try {
                student =(Student) new LoginPeople().LoginPeo(student);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (student != null){
                System.out.println("登陆成功！");
                System.out.println(student.toString());
                statusLabel.setText(student.toString());
                new StudentFrame(student);
                setVisible(false);
            }else{
                statusLabel.setText("密码或用户名错误，请检查是否正确选择登录的身份！");
                validate();
            }
        }
    }

}
