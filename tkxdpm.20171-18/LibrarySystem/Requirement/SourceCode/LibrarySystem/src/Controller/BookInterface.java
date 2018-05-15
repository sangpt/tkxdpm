/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author quangns
 */
public interface BookInterface {
    /**
     * lop interface de hien thi sach tim kiem
     * @param bid ma cua sach
     * @param title ten cua sach
     * @param author tac gia
     * @param publisher nha xuat ban sach
     * @param price gia cua sach
     */
    public void ShowBooks(String bid, String title, String author, String publisher, String price);
    public void ShowErr();
}
