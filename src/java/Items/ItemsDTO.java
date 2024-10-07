/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ItemsDTO implements Serializable{
    private int ItemId;
    private String ItemName;
    private int Price;
    private boolean Status;
    private String Image;

    public ItemsDTO() {
    }

    public ItemsDTO(int ItemId, String ItemName, int Price, boolean Status, String Image) {
        this.ItemId = ItemId;
        this.ItemName = ItemName;
        this.Price = Price;
        this.Status = Status;
        this.Image = Image;
    }

    ItemsDTO(String ItemsID, String ItemName, int Price, Object object, Object object0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int ItemId) {
        this.ItemId = ItemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    
}
