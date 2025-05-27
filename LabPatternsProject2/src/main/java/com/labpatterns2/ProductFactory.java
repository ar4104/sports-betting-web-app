package com.labpatterns2;

public class ProductFactory {
    public Product createProduct(String type, String name) {
        if ("Laptop".equalsIgnoreCase(type)) {
            return new Laptop(name);
        } else if ("Smartphone".equalsIgnoreCase(type)) {
            return new Smartphone(name);
        }
        return null;
    }
}

abstract class Product {
    protected String name;
    public Product(String name) {
        this.name = name;
    }
    public abstract String getDescription();
}

class Laptop extends Product {
    public Laptop(String name) {
        super(name);
    }
    @Override
    public String getDescription() {
        return "Laptop: " + name;
    }
}

class Smartphone extends Product {
    public Smartphone(String name) {
        super(name);
    }
    @Override
    public String getDescription() {
        return "Smartphone: " + name;
    }
}
