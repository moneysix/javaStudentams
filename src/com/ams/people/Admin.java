package com.ams.people;

import com.ams.mysql.AddPeople;
import com.ams.mysql.DeletePeople;
import com.ams.mysql.FindPeople;

import java.sql.SQLException;
import java.util.Scanner;


public class Admin extends People {
    public String addPeople(People p) throws SQLException {
        new AddPeople(p);
        return "success";
    }
    public String deletePeople(People p) throws SQLException {
        new DeletePeople(p);
        return "success";
    }
    public People findPeople(People p) throws SQLException {
        p = new FindPeople().find(p);
        return p;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
