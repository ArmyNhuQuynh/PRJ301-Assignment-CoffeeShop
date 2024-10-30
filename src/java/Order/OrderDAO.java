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
import java.util.ArrayList;
import java.util.List;
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
  
     public boolean updateOrderStatus(String orderId, String newStatus) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean isUpdated = false;

        try {
            con = DBHelper.getConnection();
            String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";

            stm = con.prepareStatement(sql);
            stm.setString(1, newStatus);
            stm.setString(2, orderId);

            int rowsAffected = stm.executeUpdate();
            isUpdated = (rowsAffected > 0);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return isUpdated;
    }

    public List<OrderDTO> listAllOrders() throws ClassNotFoundException, SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.getConnection();
            String sql = "SELECT OrderID, OrderDate, Status, Total, Phonenumber, TableId FROM Orders";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String OrderDate = rs.getString("OrderDate");
                String Status = rs.getString("Status");
                int Total = rs.getInt("Total");
                String Phonenumber = rs.getString("Phonenumber");
                int TableId = rs.getInt("TableId");

                OrderDTO order = new OrderDTO(OrderID, OrderDate, Status, Total, Phonenumber, TableId);
                orders.add(order);
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
        return orders;
    }

 public List<OrderReportDTO> reportOrder(String periodType, String startDate, String endDate) throws ClassNotFoundException, SQLException {
    List<OrderReportDTO> reportList = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        con = DBHelper.getConnection();
        String sql;

        if ("day".equalsIgnoreCase(periodType)) {
            sql = "SELECT OrderDate, COUNT(OrderID) AS OrderCount, SUM(Total) AS TotalRevenue "
                  + "FROM Orders "
                  + "WHERE OrderDate BETWEEN ? AND ? "
                  + "GROUP BY OrderDate "
                  + "ORDER BY OrderDate";
        } else if ("month".equalsIgnoreCase(periodType)) {
            sql = "SELECT FORMAT(OrderDate, 'yyyy-MM') AS OrderMonth, COUNT(OrderID) AS OrderCount, SUM(Total) AS TotalRevenue "
                  + "FROM Orders "
                  + "WHERE OrderDate BETWEEN ? AND ? "
                  + "GROUP BY FORMAT(OrderDate, 'yyyy-MM') "
                  + "ORDER BY OrderMonth";
        } else {
            throw new IllegalArgumentException("Invalid period type. Use 'day' or 'month'.");
        }

        stm = con.prepareStatement(sql);
        stm.setString(1, startDate);
        stm.setString(2, endDate);
        rs = stm.executeQuery();

        while (rs.next()) {
            String period = periodType.equalsIgnoreCase("day") ? rs.getString("OrderDate") : rs.getString("OrderMonth");
            int orderCount = rs.getInt("OrderCount");
            int totalRevenue = rs.getInt("TotalRevenue");

            OrderReportDTO report = new OrderReportDTO(period, orderCount, totalRevenue);
            reportList.add(report);
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

    return reportList;
}
  }
