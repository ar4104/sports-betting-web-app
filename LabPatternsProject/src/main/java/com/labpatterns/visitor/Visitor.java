package com.labpatterns.visitor;

import com.labpatterns.composite.ProductComposite;
import com.labpatterns.composite.ProductLeaf;

public interface Visitor {
    void visit(ProductLeaf leaf);
    void visit(ProductComposite composite);
}
