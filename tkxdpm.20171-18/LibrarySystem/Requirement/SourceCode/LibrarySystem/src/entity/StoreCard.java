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
public class StoreCard {
    private static String CID;
    private static String Status;
    private static String NumActive;

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        StoreCard.Status = Status;
    }

    public String getNumActive() {
        return NumActive;
    }

    public void setNumActive(String NumActive) {
        StoreCard.NumActive = NumActive;
    }
    
}
