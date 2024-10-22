/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import myLib.DBHelper;

/**
 *
 * @author DELL
 */
public class OrderDAO implements Serializable {

    public boolean CheckOut(String phoneNumber, int tableId, int total) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int effectRows;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO Orders"
                    + "(Phonenumber, TableId, Total, OrderDate, Status"
                    + ") VALUES("
                    + "?, ?, ?, ?, ?"
                    + ")";

            stm = con.prepareStatement(sql);
            stm.setString(1, phoneNumber);
            stm.setInt(2, tableId);
            stm.setInt(3, total);
            java.sql.Date orderDate = new java.sql.Date(System.currentTimeMillis());
            stm.setDate(4, orderDate);
            stm.setBoolean(5, true);
            

            effectRows = stm.executeUpdate();

            if (effectRows > 0) {
                result = true;
            }
            
        
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

}
