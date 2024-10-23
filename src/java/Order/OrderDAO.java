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

    public int CheckOut(String phoneNumber, int tableId, int total) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int orderId = 0;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO Orders"
                    + "(Phonenumber, TableId, Total, OrderDate, Status"
                    + ") VALUES("
                    + "?, ?, ?, ?, ?"
                    + ")";

            stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, phoneNumber);
            stm.setInt(2, tableId);
            stm.setInt(3, total);
            java.sql.Date orderDate = new java.sql.Date(System.currentTimeMillis());
            stm.setDate(4, orderDate);
            stm.setString(5, "pending");

            stm.executeUpdate();

            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
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
        return orderId;
    }

    public OrderDTO CheckStatus(String orderId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO result = null;
        try {
            con = DBHelper.getConnection();
            String sql = "select OrderID , OrderDate, Status, Total, Phonenumber, TableId "
                    + "from Orders "
                    + "where OrderID = ?";

            stm = con.prepareStatement(sql);
            stm.setString(1, orderId);

            rs = stm.executeQuery();

            if (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String OrderDate = rs.getString("OrderDate");
                String Status = rs.getString("Status");
                int Total = rs.getInt("Total");
                String Phonenumber = rs.getString("Phonenumber");
                int TableId = rs.getInt("TableId");

                result = new OrderDTO(OrderID, OrderDate, Status, Total, Phonenumber, TableId);
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
    public void addOrderDetail(int orderId, int itemId, int quantity) throws ClassNotFoundException, SQLException {
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
        System.out.println("Rows affected: " + rowsAffected); // In ra số hàng đã bị ảnh hưởng

    } finally {
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
}



    }
