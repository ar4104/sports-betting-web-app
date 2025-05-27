package com.labpatterns;

import com.labpatterns.builder.DeliveryBuilder;
import com.labpatterns.builder.StandardDeliveryBuilder;
import com.labpatterns.composite.ProductComponent;
import com.labpatterns.composite.ProductLeaf;
import com.labpatterns.composite.ProductComposite;
import com.labpatterns.facade.WarehouseFacade;
import com.labpatterns.factory.ProductFactory;
import com.labpatterns.visitor.ReportVisitor;

public class Main {
    public static void main(String[] args) {
        // Используем Фабрику для создания продуктов
        ProductFactory productFactory = new ProductFactory();
        ProductComponent product1 = productFactory.createProduct("Laptop", 10);
        ProductComponent product2 = productFactory.createProduct("Smartphone", 20);

        // Используем Компоновщик для создания иерархии продуктов
        ProductComposite warehouseProducts = new ProductComposite("Warehouse Products");
        warehouseProducts.add(product1);
        warehouseProducts.add(product2);

        // Используем Строитель для создания поставки
        DeliveryBuilder builder = new StandardDeliveryBuilder();
        builder.setSupplier("Supplier A");
        builder.addProduct(product1);
        builder.addProduct(product2);
        var delivery = builder.build();

        // Используем Фасад для управления складом
        WarehouseFacade warehouseFacade = new WarehouseFacade();
        warehouseFacade.receiveDelivery(delivery);
        warehouseFacade.showInventory();

        // Используем Посетителя для создания отчета
        ReportVisitor reportVisitor = new ReportVisitor();
        warehouseProducts.accept(reportVisitor);
        System.out.println("Report:\n" + reportVisitor.getReport());
    }
}
