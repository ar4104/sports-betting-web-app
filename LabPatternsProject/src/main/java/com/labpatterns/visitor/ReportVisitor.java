package com.labpatterns.visitor;

import com.labpatterns.composite.ProductComposite;
import com.labpatterns.composite.ProductLeaf;

public class ReportVisitor implements Visitor {

    private StringBuilder report = new StringBuilder();

    @Override
    public void visit(ProductLeaf leaf) {
        report.append("Product: ").append(leaf.getName())
              .append(", Quantity: ").append(leaf.getQuantity()).append("\\n");
    }

    @Override
    public void visit(ProductComposite composite) {
        report.append("Composite: ").append(composite.getName()).append("\\n");
    }

    public String getReport() {
        return report.toString();
    }
}
