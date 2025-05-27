package com.labpatterns.composite;

import java.util.ArrayList;
import java.util.List;

import com.labpatterns.visitor.Visitor;

public class ProductComposite extends ProductComponent {

    private List<ProductComponent> children = new ArrayList<>();

    public ProductComposite(String name) {
        super(name);
    }

    @Override
    public void add(ProductComponent component) {
        children.add(component);
    }

    @Override
    public void remove(ProductComponent component) {
        children.remove(component);
    }

    @Override
    public void show(int depth) {
        System.out.println("-".repeat(depth) + " Composite: " + name);
        for (ProductComponent child : children) {
            child.show(depth + 2);
        }
    }

    @Override
    public void accept(com.labpatterns.visitor.Visitor visitor) {
        visitor.visit(this);
        for (ProductComponent child : children) {
            child.accept(visitor);
        }
    }
}
