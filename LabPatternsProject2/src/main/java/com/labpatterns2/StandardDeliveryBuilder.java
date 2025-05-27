package com.labpatterns2;

import java.util.ArrayList;
import java.util.List;

public class StandardDeliveryBuilder {
    private List<Product> products = new ArrayList<>();
    private String supplier;

    public StandardDeliveryBuilder setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public StandardDeliveryBuilder addProduct(Product product) {
        products.add(product);
        return this;
    }

    public Delivery build() {
        return new Delivery(supplier, products);
    }
}

class Delivery {
    private String supplier;
    private List<Product> products;

    public Delivery(String supplier, List<Product> products) {
        this.supplier = supplier;
        this.products = products;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Delivery from ").append(supplier).append(":\n");
        for (Product p : products) {
            sb.append("- ").append(p.getDescription()).append("\n");
        }
        return sb.toString();
    }
}
