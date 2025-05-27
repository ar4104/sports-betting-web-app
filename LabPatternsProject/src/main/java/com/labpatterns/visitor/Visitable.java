package com.labpatterns.visitor;

public interface Visitable {
    void accept(Visitor visitor);
}
