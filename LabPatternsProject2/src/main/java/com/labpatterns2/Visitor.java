package com.labpatterns2;

public interface Visitor {
    void visit(Laptop laptop);
    void visit(Smartphone smartphone);
}
