/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderDetail;
import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private int detailId;   // Unique identifier for the order detail
    private int itemId;     // Identifier for the item
    private int orderId;    // Identifier for the associated order
    private int quantity;    // Quantity of the item ordered

    // Default constructor
    public OrderDetailDTO() {
    }

    // Constructor with parameters
    public OrderDetailDTO(int detailId, int itemId, int orderId, int quantity) {
        this.detailId = detailId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}