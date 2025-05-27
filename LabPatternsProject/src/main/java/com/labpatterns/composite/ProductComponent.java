package com.labpatterns.composite;

import com.labpatterns.visitor.Visitable;
import com.labpatterns.visitor.Visitor;

public abstract class ProductComponent implements Visitable {
    protected String name;

    public ProductComponent(String name) {
        this.name = name;
    }

    public abstract void add(ProductComponent component);
    public abstract void remove(ProductComponent component);
    public abstract void show(int depth);

    public String getName() {
        return name;
    }
}
