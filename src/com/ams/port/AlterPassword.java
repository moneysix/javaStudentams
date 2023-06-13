package com.ams.port;

import com.ams.people.People;

import java.sql.SQLException;

public interface AlterPassword {
    People alterPassword(People people) throws SQLException;
}
