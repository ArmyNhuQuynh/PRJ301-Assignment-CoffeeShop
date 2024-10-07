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
    public void ShowAllItems () throws ClassNotFoundException, SQLException{
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
      
      public ItemsDTO SearchItemsFromCart (String ItemsID) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ItemsDTO result = null;
        try{
            con = DBHelper.getConnection();
            String sql = "select ItemName, Price, "
                    + "from Items "
                    + "where ItemId = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1,ItemsID) ;
            
            rs = stm.executeQuery();
            
            if(rs.next()){
                String ItemName = rs.getString("ItemName");
                int Price = rs.getInt("Price");
                
                result = new ItemsDTO(ItemsID, ItemName, Price, null, null);
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
        return result;
       }    
}
