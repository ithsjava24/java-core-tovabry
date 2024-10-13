package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final String name;
    private final List<ProductRecord> productList = new ArrayList<>();
    private final List<ProductRecord> changedProducts = new ArrayList<>();
    private static final Map<String, Warehouse> warehouseMap = new HashMap<>();

    private Warehouse() {
    this("MyStore");
    }

    private Warehouse(String warehouseName) {
        this.name = warehouseName;
    }

    public static Warehouse getInstance(String warehouseName) {
        return warehouseMap.computeIfAbsent(warehouseName, Warehouse::new);
    }

    public static Warehouse getInstance() {
        return new Warehouse();
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return productList.isEmpty();
    }

    private boolean productExists(UUID existingId) {
        return productList.stream()
                .anyMatch(product -> product.productId().equals(existingId));
    }

    public ProductRecord addProduct(UUID productId, String productName, Category categoryName, BigDecimal price) {
        if (productExists(productId)) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord addedProduct = new ProductRecord(productId, productName, categoryName, price);
        productList.add(addedProduct);
        return addedProduct;
    }


    public void updateProductPrice(UUID productId, BigDecimal newPrice) {
        getProductById(productId).ifPresentOrElse(product -> {
            ProductRecord updatedProduct = product.setPrice(newPrice);
            productList.set(productList.indexOf(product), updatedProduct);
            changedProducts.add(product);
        }, () -> {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        });
    }

    public Optional<ProductRecord> getProductById(UUID productId) {
        return productList.stream()
                .filter(product -> product.productId().equals(productId))
                .findFirst();
    }

    public List<ProductRecord> getChangedProducts() {
        return Collections.unmodifiableList(changedProducts);
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(productList);
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return productList.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return productList.stream()
                .filter(product -> product.category().equals(category))
                .toList();
    }

}


