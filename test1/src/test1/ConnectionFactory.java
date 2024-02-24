/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;
public class ConnectionFactory {
    static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String url = "jdbc:sqlserver://localhost:1433;databaseName=test;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";
    static String username = "sa";
    static String password = "nam42160";

    Connection conn = null;

    public ConnectionFactory() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Connected successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
