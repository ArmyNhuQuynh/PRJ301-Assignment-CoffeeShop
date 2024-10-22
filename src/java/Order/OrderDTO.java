/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class OrderDTO implements Serializable{
    private int OrderId;
    private String OrderDate;
    private boolean Status;
    private int Total;
    private String Phonenumber;
    private int Tablenumber;

    public OrderDTO() {
    }

    public OrderDTO(int OrderId, String OrderDate, boolean Status, int Total, String Phonenumber, int Tablenumber) {
        this.OrderId = OrderId;
        this.OrderDate = OrderDate;
        this.Status = Status;
        this.Total = Total;
        this.Phonenumber = Phonenumber;
        this.Tablenumber = Tablenumber;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String Phonenumber) {
        this.Phonenumber = Phonenumber;
    }

    public int getTablenumber() {
        return Tablenumber;
    }

    public void setTablenumber(int Tablenumber) {
        this.Tablenumber = Tablenumber;
    }
    
    
}
