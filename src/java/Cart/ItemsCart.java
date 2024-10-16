package Cart;

import Items.ItemsDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ItemsCart implements Serializable {
    private Map<ItemsDTO, Integer> items = new HashMap<>();

    public Map<ItemsDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(ItemsDTO item) {
        if (item == null) {
            return; 
        }
        
        items.put(item, items.getOrDefault(item, 0) + 1);
    }
    
    public void updateCart(ItemsDTO item, Integer quantity) {
        if (item == null || quantity == null || quantity < 0) {
            return;
        }
        
        if (quantity == 0) {
            items.remove(item);
        } else {
            items.put(item, quantity);
        }
    }
    
    public void removeItemFromCart(ItemsDTO item) {
        if (item == null || items.isEmpty()) {
            return; 
        }
        
        items.remove(item);
    }
}
