package com.ams.people;

import com.ams.course.Course;
import com.ams.course.Grade;
import com.ams.mysql.AlterPass;
import com.ams.port.AlterPassword;
import com.ams.port.CheckGrade;

import java.sql.SQLException;

public class Student extends People implements CheckGrade, AlterPassword {
    public String _class;   //班级
    public double aver(){
        return 1.1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_class='" + _class + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
    @Override
    public Grade[] checkGrade(Course course) throws SQLException {
        return new com.ams.mysql.CheckGrade().checkGrade(this,course);
    }

    @Override
    public People alterPassword(People people) throws SQLException {
         return (Student) new AlterPass().alterPass(people);
    }

}
