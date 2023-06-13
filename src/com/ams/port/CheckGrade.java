package com.ams.port;


import com.ams.course.Course;
import com.ams.course.Grade;

import java.sql.SQLException;

public interface CheckGrade {
    Grade[] checkGrade(Course course) throws SQLException;
}
