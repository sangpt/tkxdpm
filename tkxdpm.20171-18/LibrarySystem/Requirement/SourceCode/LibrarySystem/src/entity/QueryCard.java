/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author quangns
 */
public class QueryCard {
    
    
    /**
     * them ma the muon vao database
     * @param year nam het han su dung the
     * @param month thang het han su dung the
     * @param day ngay het han su dung the
     * @param numactive ma the cap cho user de active
     * @throws SQLException 
     */
    public static void InsertCard(int year, int month, int day, int numactive) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO card(ExpiredDate, NumActive) VALUES ('"+year+"-"+month+"-"+day+"'," +numactive + ")");
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * lay thong tin cua the
     * @param cid ma the can lay
     * @return
     * @throws SQLException 
     */
    public static ArrayList<String> SearchCard(String cid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> card = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM card WHERE CID = '" + cid + "'");
            while (rs.next()) {
                String stt = rs.getString("Status");
                card.add(stt);
            }
            conn.close();
            return card;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    
    /**
     * tim kiem cac the moi chua co trang thai de cap cho nguoi dung
     * @return
     * @throws SQLException 
     */
    public static ResultSet SearchCardST() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM card WHERE Status IS NULL");
            while (rs.next()) {
                String stt = rs.getString("CID");
                String num = rs.getString("NumActive");
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
     * cap nhat trang thai da cap the cho nguoi dung
     * @param numberactive ma da cap cho nguoi dung de active the
     * @throws SQLException 
     */
    public static void UpdateCardNA(String numberactive) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE card SET Status = 1, NumActive = null WHERE NumActive = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, numberactive);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    public static ArrayList<String> GetCard(String number) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> card = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM card WHERE NumActive = " + number);
            while (rs.next()) {
                String cid = rs.getString("CID");
                String status = rs.getString("Status");
                String num = rs.getString("NumActive");
                card.add(cid);
                card.add(status);
                card.add(num);
            }
            conn.close();
            return card;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    /**
     * cap nhat trang thai cua the, da tra sach hay muon sach
     * @param status trang thai cua the
     * @param cid ma the
     * @throws SQLException 
     */
    public static void UpdateCardCID(boolean status, int cid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE card SET Status = ? WHERE CID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, status);
            ps.setInt(2, cid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
    * xoa the muon
    * @param cid ma the can xoa
    * @throws SQLException 
    */
    public static void DelCard(int cid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "DELETE FROM card WHERE CID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
}
