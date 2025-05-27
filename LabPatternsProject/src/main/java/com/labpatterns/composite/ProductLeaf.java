package com.labpatterns.composite;

import com.labpatterns.visitor.Visitor;

public class ProductLeaf extends ProductComponent {

    private int quantity;

    public ProductLeaf(String name, int quantity) {
        super(name);
        this.quantity = quantity;
    }

    @Override
    public void add(ProductComponent component) {
        throw new UnsupportedOperationException("Leaf node doesn't support add operation");
    }

    @Override
    public void remove(ProductComponent component) {
        throw new UnsupportedOperationException("Leaf node doesn't support remove operation");
    }

    @Override
    public void show(int depth) {
        System.out.println("-".repeat(depth) + " Product: " + name + ", Quantity: " + quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public void accept(com.labpatterns.visitor.Visitor visitor) {
        visitor.visit(this);
    }
}
