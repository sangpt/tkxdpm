/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.ArrayList;

/**
 *
 * @author quang.ns.01
 */
public class QueryBook {
    /**
    * truy vấn sách trong hệ thống
    * @param Title tên sách truy vấn trong hệ thống
    * @param Publisher tên nhà xuất bản
    * @param Author tên tác giả
    * @param Price giá của quyển sách 
    */
    public QueryBook(String Title, String Publisher, String Author, int Price){
        
    }
    
    
    /**
     * Thêm sách vào hệ thống
     * @param Title tên sách thêm vào hệ thống
     * @param  Publisher tên nhà xuất bản quyển sách đó
     * @param Author tên tác giả của quyển sách
     * @param Price giá sách
     */
    public void InsertBook(String Title, String Publisher, String Author, int Price) {
        
    }
    
    
    /**
     * Tìm kiếm sách trong hệ thống
     * @param Str đưa vào một chuỗi kí tự, hệ thống sẽ tìm kiếm dựa trên
     * chuỗi kí tự đó.
     * @return đưa ra kết quả tìm kiếm được trên hệ thống.
     */
    public ArrayList SearchBook(String Str){
        return null;
    }
    
    
    /**
     * Xóa một loại sách trong hệ thống
     * @param ID nhập vào ID của quyển sách sau đó hệ thống sẽ xóa sách đó
     * dựa trên ID của sách.    
     */
    public void DelBook(int ID){
        
    }
    
    
    /**
     * Sửa đổi gía của một loại sách
     * @param ID của sách cần sửa giá
     * @param Num giá sách cần sửa lại.
     */
    public void UpdatePrice(int ID, int Num) {
        
    }
}
