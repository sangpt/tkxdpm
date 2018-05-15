/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sangpt
 * @param rs
 * 
 */
public class Helper {
    public static DefaultTableModel createTableModel(String sql){
        DefaultTableModel model = new DefaultTableModel();
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNumber = rsmd.getColumnCount();
            String[] arr = new String[colNumber];
            for (int i = 0; i < colNumber; i++) {
                arr[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(arr);
            while(rs.next()){
                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rs.getString(i+1);
                }
                model.addRow(arr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println("Error when createTableModel");
        } 
        return model;
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(createTableModel("SELECT * FROM book"));
    }
}
