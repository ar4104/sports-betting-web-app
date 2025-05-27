package com.labpatterns.factory;

import com.labpatterns.composite.ProductComponent;
import com.labpatterns.composite.ProductLeaf;

public class ProductFactory {
    public ProductComponent createProduct(String name, int quantity) {
        // Можно расширить для разных типов продуктов
        return new ProductLeaf(name, quantity);
    }
}
