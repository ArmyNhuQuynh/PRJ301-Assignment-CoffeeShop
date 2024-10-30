/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import myLib.DBHelper;

/**
 *
 * @author DELL
 */
public class ItemsDAO implements Serializable{
    private List<ItemsDTO> products;
    public List<ItemsDTO> getProducts(){        
    return products;
    }
    
     public void ShowAllItemsSearchNull () throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelper.getConnection();
            String sql = "select ItemId, ItemName, Price, Status, Image "
                    + "from Items";
            stm = con.prepareStatement(sql);
            
            rs = stm.executeQuery();
            while(rs.next()){
                int ItemId = rs.getInt("ItemId");
                String ItemName = rs.getString("ItemName");
                int Price = rs.getInt("Price");
                boolean Status = rs.getBoolean("Status");
                String Image = rs.getString("Image");
                
                ItemsDTO dto = new ItemsDTO(ItemId, ItemName, Price, Status, Image);
                
                if (this.products == null){
                    this.products = new ArrayList<>();
                }
                if( Status == true){
                this.products.add(dto);
            }
            }
            
        }finally{
        if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
    }
    }
    public List<ItemsDTO> ShowAllItems() throws ClassNotFoundException, SQLException {
    List<ItemsDTO> products1 = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        con = DBHelper.getConnection();
        String sql = "SELECT ItemId, ItemName, Price, Status, Image FROM Items";
        stm = con.prepareStatement(sql);
        rs = stm.executeQuery();
        
        while (rs.next()) {
            int itemId = rs.getInt("ItemId");
            String itemName = rs.getString("ItemName");
            int price = rs.getInt("Price");
            boolean status = rs.getBoolean("Status");
            String image = rs.getString("Image");

            ItemsDTO dto = new ItemsDTO(itemId, itemName, price, status, image);
            if (status == true) {
                products1.add(dto);
            }
        }
    } finally {
        if (rs != null) rs.close();
        if (stm != null) stm.close();
        if (con != null) con.close();
    }
    return products1;
}    
    
      public void SearchItems (String SearchValue) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelper.getConnection();
            String sql = "select ItemId, ItemName, Price, Status, Image "
                    + "from Items "
                    + "where ItemName like ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%"+SearchValue+"%");
            
            rs = stm.executeQuery();
            while(rs.next()){
                int ItemId = rs.getInt("ItemId");
                String ItemName = rs.getString("ItemName");
                int Price = rs.getInt("Price");
                boolean Status = rs.getBoolean("Status");
                String Image = rs.getString("Image");
                
                ItemsDTO dto = new ItemsDTO(ItemId, ItemName, Price, Status, Image);
                
                if (this.products == null){
                    this.products = new ArrayList<>();
                }
                if( Status == true){
                this.products.add(dto);
            }
            }
            
        }finally{
        if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
    }
    }
        

    public boolean createItem(String itemName, int price, boolean status, String image) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        int rowsAffected = 0; 
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO Items (ItemName, Price, Status, Image) VALUES (?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, itemName);
            stm.setInt(2, price);
            stm.setBoolean(3, status);
            stm.setString(4, image);

             rowsAffected = stm.executeUpdate();
             if(rowsAffected >0 )
             {result = true;}
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    // Phương thức cập nhật sản phẩm
    public boolean updateItem(int itemId, String itemName, int price, boolean status, String image) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        int rowsAffected = 0; 
        try {
            con = DBHelper.getConnection();
            String sql = "UPDATE Items SET ItemName = ?, Price = ?, Status = ?, Image = ? WHERE ItemId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, itemName);
            stm.setInt(2, price);
            stm.setBoolean(3, status);
            stm.setString(4, image);
            stm.setInt(5, itemId);

            rowsAffected = stm.executeUpdate();
             if(rowsAffected >0 )
             {result = true;}
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    // Phương thức xem chi tiết sản phẩm theo ID
    public ItemsDTO viewItemById(int itemId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT ItemId, ItemName, Price, Status, Image FROM Items WHERE ItemId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, itemId);

            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ItemId");
                String itemName = rs.getString("ItemName");
                int price = rs.getInt("Price");
                boolean status = rs.getBoolean("Status");
                String image = rs.getString("Image");

                return new ItemsDTO(id, itemName, price, status, image);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null; // Không tìm thấy sản phẩm
    }
    
    public ItemsDTO GetItems(String ItemsID) throws ClassNotFoundException, SQLException {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    ItemsDTO result = null;
    try {
        if (ItemsID == null || ItemsID.trim().isEmpty()) {
            throw new IllegalArgumentException("ItemsID cannot be null or empty");
        }

        con = DBHelper.getConnection();
        String sql = "SELECT Image, Price, ItemName FROM Items WHERE ItemId = ?";
        stm = con.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(ItemsID)); // Convert to int
        
        rs = stm.executeQuery();
        
        if (rs.next()) {
            String itemName = rs.getString("ItemName");
            int price = rs.getInt("Price");
            String image = rs.getString("Image");
            
            int itemId = Integer.parseInt(ItemsID);
            result = new ItemsDTO(itemId, itemName, price, true, image);
        }
        
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    return result;
}
}
