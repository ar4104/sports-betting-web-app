package com.labpatterns2;

public class ReportVisitor implements Visitor {
    private StringBuilder report = new StringBuilder();

    @Override
    public void visit(Laptop laptop) {
        report.append("Visited Laptop: ").append(laptop.getDescription()).append("\\n");
    }

    @Override
    public void visit(Smartphone smartphone) {
        report.append("Visited Smartphone: ").append(smartphone.getDescription()).append("\\n");
    }

    public String getReport() {
        return report.toString();
    }
}
