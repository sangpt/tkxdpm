/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author quangns
 */
class ConnectSQL {
    static Connection connectsql(){
        try {
//          To load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
//          Create Connection variable in order to connect to database
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/librarysystem", "root", "");
            System.out.println("Database in connected!");
            return conn;
//            Statement st = conn.createStatement();
//            st.executeUpdate("INSERT INTO test " + "VALUES ('1002', 'quangns', '')" );
//            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        ConnectSQL con = new ConnectSQL();
        con.connectsql();
    }
}
