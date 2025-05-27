package com.labpatterns2;

import java.util.ArrayList;
import java.util.List;

public class WarehouseFacade {
    private List<Product> inventory = new ArrayList<>();

    public void receiveDelivery(Delivery delivery) {
        inventory.addAll(delivery.products);
    }

    public String showInventory() {
        StringBuilder sb = new StringBuilder("Warehouse Inventory:\n");
        for (Product p : inventory) {
            sb.append("- ").append(p.getDescription()).append("\n");
        }
        return sb.toString();
    }
}
