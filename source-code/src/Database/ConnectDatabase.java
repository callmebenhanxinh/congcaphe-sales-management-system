/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
        
/**
 *
 * @author ThanhNhan
 */
public class ConnectDatabase {
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static String dbURL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=CongCaPhe;"
            + "integratedSecurity=true";
    
    public static String dbUser = "";
    public static String dbPass = "";

    public Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName(ConnectDatabase.driverName);
        Connection conn = DriverManager.getConnection(ConnectDatabase.dbURL, ConnectDatabase.dbUser, ConnectDatabase.dbPass);
        return conn;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ConnectDatabase conn = new ConnectDatabase();
        try {
            conn.getConnect();
            System.out.print("Connected");
        }
        catch (SQLException ex) {
            System.out.println("Cannot connect database, " + ex);
        }
    }
}
