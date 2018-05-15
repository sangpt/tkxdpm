/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author quangns
 */
public class StoreBookcp {
    private static String CPID;
    private static String CID;
    private static String BID;
    private static String Status;

    public static String getCPID() {
        return CPID;
    }

    public static void setCPID(String CPID) {
        StoreBookcp.CPID = CPID;
    }

    public static String getCID() {
        return CID;
    }

    public static void setCID(String CID) {
        StoreBookcp.CID = CID;
    }

    public static String getBID() {
        return BID;
    }

    public static void setBID(String BID) {
        StoreBookcp.BID = BID;
    }

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String Status) {
        StoreBookcp.Status = Status;
    }
    
    
}
