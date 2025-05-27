package com.labpatterns.builder;

import com.labpatterns.composite.ProductComponent;
import com.labpatterns.composite.ProductComposite;

import java.util.ArrayList;
import java.util.List;

public class StandardDeliveryBuilder implements DeliveryBuilder {

    private String supplier;
    private List<ProductComponent> products = new ArrayList<>();

    @Override
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public void addProduct(ProductComponent product) {
        products.add(product);
    }

    @Override
    public ProductComposite build() {
        ProductComposite delivery = new ProductComposite("Delivery from " + supplier);
        for (ProductComponent product : products) {
            delivery.add(product);
        }
        return delivery;
    }
}
