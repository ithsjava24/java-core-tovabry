package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Warehouse {
    private String name;
    private List<ProductRecord> productRecords = new ArrayList<>();
    private UUID uuid;

    private Warehouse() {
    }

    //Konstruktor
    private Warehouse(String warehouseName) {
        this.name = warehouseName;
        this.productRecords = new ArrayList<>();
    }

    public static Warehouse getInstance(String warehouseName) {
        return new Warehouse(warehouseName);
    }

    public static Warehouse getInstance() {
        return new Warehouse();
    }

    public boolean isEmpty() {
        return this.name == null;
    }


    //Lägger in produkten i listan, samt returnerar produkten
    public ProductRecord addProduct(UUID uuidName, String name, Category categoryName, BigDecimal bigDecimal) {
        var addedProduct = new ProductRecord(uuidName, name, categoryName, bigDecimal);
        this.productRecords.add(addedProduct);
        return addedProduct;
    }

    public List<ProductRecord> getProducts() {
        return this.productRecords;
    }

    public Optional<ProductRecord> getProductById(UUID id) {

        return this.productRecords.stream()
                .filter(product -> product.UUID_value().equals(id))
                .findFirst();
    }





}


