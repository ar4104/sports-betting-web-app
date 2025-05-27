package com.labpatterns.facade;

import com.labpatterns.composite.ProductComponent;

public class WarehouseFacade {

    public void receiveDelivery(ProductComponent delivery) {
        System.out.println("Receiving delivery: " + delivery.getName());
        delivery.show(2);
    }

    public void showInventory() {
        System.out.println("Showing inventory...");
        // Здесь можно добавить логику отображения склада
    }
}
