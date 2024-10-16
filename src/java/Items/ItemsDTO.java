package Items;

import java.io.Serializable;
import java.util.Objects;

public class ItemsDTO implements Serializable {
    private int itemId;      // Use camelCase for variable names
    private String itemName;
    private int price;
    private boolean status;
    private String image;

    // Default constructor
    public ItemsDTO() {
        this.itemId = 0;         // Initialize to default values
        this.itemName = "";      // Initialize to an empty string
        this.price = 0;          // Initialize to zero
        this.status = false;      // Initialize status as false
        this.image = "";         // Initialize to an empty string
    }

    // Parameterized constructor
    public ItemsDTO(int itemId, String itemName, int price, boolean status, String image) {
        this.itemId = itemId; 
        this.itemName = itemName;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemsDTO itemsDTO = (ItemsDTO) obj;
        return itemId == itemsDTO.itemId; // Compare based on itemId
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId); // Generate hash based on itemId
    }
}
