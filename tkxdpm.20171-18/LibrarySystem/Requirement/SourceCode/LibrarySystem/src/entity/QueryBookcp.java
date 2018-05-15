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
import java.text.ParseException;
import java.util.ArrayList;
/**
 *
 * @author quangns
 */
public class QueryBookcp {
    
    
    /**
     * them cac ban sao cua sach vao database
     * @param number so luong ban sao cua sach dua vao
     * @param bid ma cua sach chinh trong thu vien
     * @throws SQLException 
     */
    public static void InsertCopy(int number, int bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            for(int i=0; i<number;i++){
                System.out.println(i);
                st.executeUpdate("INSERT INTO bookcp(BID) VALUES ("+bid+ ")");
            }
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * cap nhat trang thai muon sach, status cua ban sao la false: da duoc muon
     * @param cid ma the da muon sach nay
     * @param expdate han phai tra sach
     * @param cpid ma ban sao sach muon
     * @throws SQLException 
     */
    public static void UpdateBorrow(String cid, String expdate, String cpid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE bookcp SET CID = ?, ExpDate = ?, Status = false WHERE CPID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            ps.setString(2, expdate);
            ps.setString(3, cpid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * cap nhat trang thai da tra sach
     * @param cpid ma ban sao da duoc tra
     * @throws SQLException 
     */
    public static void UpdateReturn(int cpid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE bookcp SET CID = NULL, ExpDate = NULL, Status = true WHERE CPID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cpid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * dem tong so ban sao sach co trong database
     * @param bid ma sach can dem
     * @return
     * @throws SQLException 
     */
    public static ResultSet CountTotalBook(int bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(CPID) AS number FROM bookcp WHERE BID =" + bid);
//            while (rs.next()) {
//                String lastName = rs.getString("number");
//                System.out.println(lastName + "\n");
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    
    /**
     * dem tong so ban sao sach cua quyen sach do co the muon trong database
     * @param bid ma sach muon' muon
     * @return
     * @throws SQLException 
     */
    public static int CountBookFree(String bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            int count = 0;
            ResultSet rs = st.executeQuery("SELECT COUNT(CPID) AS number FROM bookcp WHERE BID =" + bid + " AND Status = true");
            while (rs.next()) {
                count = rs.getInt("number");
//                String lastName = rs.getString("number");
//                System.out.println(lastName + "\n");
            }
            conn.close();
            return count;
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return 0;
    }
    
    
    /**
     * dem tong so sach da muon cua mot tai khoan
     * @param cid ma the da muon sach
     * @return
     * @throws SQLException 
     */
    public static ResultSet CountBookBorrowed(int cid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(CPID) AS number FROM bookcp WHERE CID =" + cid);
//            while (rs.next()) {
//                String lastName = rs.getString("number");
//                System.out.println(lastName + "\n");
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    public static ArrayList<String> SearchBookcp(String bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ArrayList<String> bookcp = new ArrayList<String>();
            ResultSet rs = st.executeQuery("SELECT * FROM bookcp WHERE BID =" + bid + " AND Status = true");
            while (rs.next()) {
                String cpid = rs.getString("CPID");
                String cid = rs.getString("CID");
                String bookid = rs.getString("BID");
                String status = rs.getString("Status");
                bookcp.add(cpid);
                bookcp.add(cid);
                bookcp.add(bid);
                bookcp.add(status);
            }
            conn.close();
            return bookcp;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    public static ArrayList<String> SearchBookBorrowed(String cid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ArrayList<String> bookcp = new ArrayList<String>();
            ResultSet rs = st.executeQuery("SELECT * FROM bookcp WHERE CID =" + cid);
            while (rs.next()) {
                String cpid = rs.getString("CPID");
                String bookid = rs.getString("BID");
                bookcp.add(bookid);
                bookcp.add(cid);
                bookcp.add(cpid);
            }
            conn.close();
            return bookcp;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
}
