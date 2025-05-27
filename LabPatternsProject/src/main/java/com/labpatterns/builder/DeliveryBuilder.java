package com.labpatterns.builder;

import com.labpatterns.composite.ProductComponent;
import com.labpatterns.composite.ProductComposite;

public interface DeliveryBuilder {
    void setSupplier(String supplier);
    void addProduct(ProductComponent product);
    ProductComposite build();
}
