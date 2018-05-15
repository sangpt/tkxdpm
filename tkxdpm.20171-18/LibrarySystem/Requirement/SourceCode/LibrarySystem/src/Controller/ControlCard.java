/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.QueryCard;
import entity.StoreCard;
import entity.StoreUser;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author quangns
 */
public class ControlCard {
    private static StoreUser account = new StoreUser();
    private static StoreCard cardinfo = new StoreCard();
    
    public ControlCard() {
    }
    
    private void GetInfo(String num) throws SQLException {
        ArrayList<String> card = (ArrayList<String>) new QueryCard().GetCard(num);
        cardinfo.setCID(card.get(0));
        cardinfo.setStatus(card.get(1));
        cardinfo.setNumActive(card.get(2));
    }
    
    private void GetCard(String cid) throws SQLException {
        ArrayList<String> card = (ArrayList<String>) new QueryCard().SearchCard(cid);
        cardinfo.setCID(cid);
        cardinfo.setStatus(card.get(0));
    }
    
    private boolean CheckStoreCard(String num) throws SQLException {
        ArrayList<String> card = (ArrayList<String>) new QueryCard().GetCard(num);
        return (!card.isEmpty());
    }
    
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
    
    public boolean activecard(String num) throws SQLException {
        try{
            if(CheckHaveSpace(num)) 
                if(CheckStoreCard(num)) {
                    GetInfo(num);
                    new QueryCard().UpdateCardNA(num);
                    new ControlAccount().InsertCard(cardinfo.getCID());
                    return true;
                }
        }
        catch(Exception e) {
            System.err.println("error: " + e);
        }
        return false;
    }
    
    public String GetStatus(String cid) throws SQLException {
        new ControlCard().GetCard(cid);
        return cardinfo.getStatus();
    }
}
