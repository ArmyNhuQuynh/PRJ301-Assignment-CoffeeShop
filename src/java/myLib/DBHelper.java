/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myLib;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class DBHelper implements Serializable{
    public static Connection getConnection()throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        String url="jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=DBJAVAWEB;"
                + "instanceName=DESKTOP-9GJRFGM";
        
        Connection con = DriverManager.getConnection(url,"sa" ,"12345");
        return con;
    }
    
}
