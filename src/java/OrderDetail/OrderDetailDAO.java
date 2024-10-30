/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import myLib.DBHelper;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO {
     public void addOrderDetail(int orderId, int itemId, int quantity) {
    Connection con = null;
    PreparedStatement stm = null;

    try {
        con = DBHelper.getConnection();
        String sql = "INSERT INTO OrderDetails (OrderID, ItemID, Quantity) VALUES (?, ?, ?)";
        
        stm = con.prepareStatement(sql);
        stm.setInt(1, orderId);
        stm.setInt(2, itemId);
        stm.setInt(3, quantity);

        int rowsAffected = stm.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);

    } catch (SQLException e) {
        System.err.println("SQL error: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.err.println("Class not found: " + e.getMessage());
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close resources: " + e.getMessage());
        }
    }
}

}
