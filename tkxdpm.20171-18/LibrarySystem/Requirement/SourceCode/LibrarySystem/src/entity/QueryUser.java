/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author quangns
 */
public class QueryUser {
    
    
    /**
     * Them tai khoan nguoi dung vao he thong
     * @param firstname ten nguoi dung dang ki
     * @param lastname ho cua nguoi dung
     * @param username ten tai khoan muon dang ki
     * @param password mat khau nguoi dung muon dat
     * @throws SQLException 
     */
    public static void InsertUser(String firstname, String lastname, String username, 
                                                String password) throws SQLException{
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO user(FirstName, LastName, UserName, PassWord, Role) VALUES "
                    + "('" + firstname+ "','" + lastname+"','"+username+"','"+password+"','user')");
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * tim kiem thong tin nguoi dung theo username
     * @param username can tim kiem
     * @return luu thong tin nguoi dung vao ArrayList
     * @throws SQLException 
     */
    public static ArrayList<String> SearchUserName(String username) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> user = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM user WHERE UserName = '" + username + "'");
            while (rs.next()) {
                String name = rs.getString("UserName");
                String password = rs.getString("PassWord");
                String role = rs.getString("Role");
                String cardID = rs.getString("CardID");
                user.add(name);
                user.add(password);
                user.add(role);
                user.add(cardID);
            }
            conn.close();
            return user;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    
    /**
     * Tim kiem thong tin nguoi dung theo ten cua nguoi do trong he thong
     * @param name ten nguoi muon tim
     * @return cac username cua cac nguoi dung co cung ten tim kiem trong he thong
     * @throws SQLException 
     */
    public static ResultSet SearchName(String name) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM user WHERE FirstName = '" + name + "'");
            while (rs.next()) {
                String Username = rs.getString("UserName");
                System.out.println(Username);
            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    
    /**
     * Thay doi mat khau
     * @param oldpassword mat khau cu cua nguoi dung
     * @param newpassword mat khau moi
     * @throws SQLException 
     */
    public static void UpdatePW(String oldpassword, String newpassword) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE user SET PassWord = ? WHERE UserName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, oldpassword);
            ps.setString(2, newpassword);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * Them ma the muon cua nguoi dung vao he thong,
     * khi nguoi dung moi duoc cap the hay lam lai the,
     * sau khi nhap ma vao the, he thong se them ID cua the vao database
     * @param cardid ma the cap cho nguoi dung
     * @param username ten tai khoan duoc cap ma the
     * @throws SQLException 
     */
    //Update CardID of a account
    public static void UpdateCardID(String cardid, String username) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE user SET CardID = ? WHERE UserName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cardid);
            ps.setString(2, username);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * Xoa mot nguoi su dung theo username
     * @param username ten tai khoan cua nguoi dung
     * @throws SQLException 
     */
    //Delete a account
    public static void DelUser(String username) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "DELETE FROM user WHERE UserName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    //Check username to register
//    public static boolean CheckRsUsername(String st) throws SQLException {
//        ArrayList<String> user = new QueryUser(st).SearchUserName();
////        return !st.equals(user.get(0));
//        try {
//            return !st.equals(user.get(0));
//        } catch (Exception e) {
//            System.out.println("error: " + e);
//        }
//        return false;
//    }
    
    
//    public static void main(String[] args) throws SQLException {
//       boolean check = QueryUser.CheckRsUsername("minh");
//       if(check)
//            System.out.println("chua duoc su dung");
//       else
//            System.out.println("da duoc su dung");
//    }
}
