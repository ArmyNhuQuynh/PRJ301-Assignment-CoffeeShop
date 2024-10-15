/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ItemsCart implements Serializable{
   private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String item) {
        if (item == null || item.trim().isEmpty()) {
            return; // No null or empty items allowed
        }
        
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        
        // Increase quantity if item exists, or add new item
        items.put(item, items.getOrDefault(item, 0) + 1);
        System.out.println(item);
    }
    
    public void updateCart(String item, Integer value) {
        if (item == null || item.trim().isEmpty()) {
            return; // No null or empty items allowed
        }
        
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        if (value == 0) {
            items.remove(item);
        } else {
            items.put(item, value);
            System.out.println("vo roi ne");
        }
        // Increase quantity if item exists, or add new item
       
    }
    public void removeItemFromCart(String item) {
        if (item == null || item.trim().isEmpty() || items.isEmpty()) {
            return; // Invalid item or empty cart
        }

        // Check if the item exists in the cart
      
                items.remove(item);

            if (items.isEmpty()) {
                items = null;
            }
    }
}
