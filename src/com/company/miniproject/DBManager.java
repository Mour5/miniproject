package com.company.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    Connection connection;
    public void connectToDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?useUnicode=true&serverTimezone=UTC", "root", "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
