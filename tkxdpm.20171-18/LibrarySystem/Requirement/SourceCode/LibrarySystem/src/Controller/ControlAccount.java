/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.QueryUser;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.StoreUser;

/**
 *
 * @author quangns
 */
public class ControlAccount {
    private static StoreUser account = new StoreUser();
    
    
    /**
     * lay du lieu nguoi dung theo username trong lan kiem tra dau tien, 
     * luu lai de khong bi truy van nhieu lan
     * @param st username cua nguoi dung nhap vao
     * @throws SQLException 
     */
    private void GetInfo(String username) throws SQLException {
        ArrayList<String> user = new QueryUser().SearchUserName(username);
        account.setUsername(user.get(0));
        account.setPassword(user.get(1));
        account.setRole(user.get(2));
        account.setCardID(user.get(3));
//        System.out.println(account.getPassword());
    }
    
    
    /**
     * kiem tra username da trong database chua
     * @param username ten tai khoan can kiem tra
     * @return false la chua co trong database | true la da co trong database
     * @throws SQLException 
     */
    private boolean CheckStoreUsername(String username) throws SQLException {
        ArrayList<String> user = new QueryUser().SearchUserName(username);
        return (!user.isEmpty());
    }

    private boolean CheckPassword(String password) throws SQLException {
        return password.equals(account.getPassword());
    }
    
    private boolean CheckRole(String role) {
        return role.equals(account.getRole());
    }
    
    
    /**
     * kiem tra trong doan text co khoang trang khong
     * @param st doan text can kiem tra
     * @return false la doan text co khoang trang hoac trong rong | 
     * true la doan text khong co khoang trang
     */
    protected boolean CheckHaveSpace(String st) {
        if(st == null || st.equals(""))
            return false;
        String [] array = st.split("");
        for(int i =0; i<array.length; i++) {
            if(array[i].equals(" "))
                return false;
        }
        return true;
    }
    
    
    /**
     * lay cac du lieu lien quan de nguoi su dung, 
     * kiem tra co phu hop trong database khong
     * @param username username nguoi dung da nhap
     * @param password password nguoi dung da nhap
     * @return sau khi da xac thuc dang nhap vao he thong
     * @throws SQLException
     */
    public boolean CheckSignIn(String username, String password) throws SQLException {
        if(CheckHaveSpace(username) && CheckHaveSpace(password)) {
            if(CheckStoreUsername(username)) {
                GetInfo(username);
                boolean checkpw = new ControlAccount().CheckPassword(password);
                if(checkpw)
                    return true;
            }
        }
        return false;
    }
    
    
    /**
     * kiem tra username da co trong database chua,
     * neu chua co thi moi duoc dang ki them vao database
     * @param firstname ten cua nguoi dung dang ki
     * @param lastname ho cua nguoi dung dang ki
     * @param username ten tai khoan nguoi dung muon dang ki
     * @param password mat khau nguoi dung dang ki
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean Register(String firstname, String lastname, String username, String password) throws SQLException {
        if(CheckHaveSpace(username)) {
            if(!CheckStoreUsername(username)) {
                QueryUser.InsertUser(firstname, lastname, username, password);
                return true;
            }
        }
        return false;        
    }
    
    public void InsertCard(String num) throws SQLException {
        String username = account.getUsername();
        new QueryUser().UpdateCardID(num, username);
    }
    
    public String GetCard() throws SQLException {
        String cid = account.getCardID();
        return cid;
    }
    
    public static void main(String[] args) throws SQLException {
        String st = "minh";
        ControlAccount string = new ControlAccount();
//        System.out.println(string.CheckSignIn("1234", "43"));
        System.out.println(string.Register("hong", "hoa", "hoahong", "159"));
    }
}
