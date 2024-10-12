package org.example.warehouse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final String name;
    private final List<ProductRecord> addedProducts = new ArrayList<>();
    private static final Map<String, Warehouse> instances = new HashMap<>();
    private final List<ProductRecord> changedProducts = new ArrayList<>();

    private Warehouse() {
    this.name = "MyStore";
    }

    private Warehouse(String warehouseName) {
        this.name = warehouseName;
    }

    public static Warehouse getInstance(String name) {
        if (instances.containsKey(name)) {
            return instances.get(name);
        } else {
            Warehouse warehouse = new Warehouse(name);
            instances.put(name, warehouse);
            return warehouse;
        }
    }

    public String getName() {
        return name;
    }

    public static Warehouse getInstance() {
        return new Warehouse();
    }

    public boolean isEmpty() {
        return addedProducts.isEmpty();
    }

    //Lägger in produkten i listan, samt returnerar produkten
    public ProductRecord addProduct(UUID uuidName, String name, Category categoryName, BigDecimal price) {
        boolean productExists = addedProducts.stream()
                .anyMatch(product -> product.UUID_value().equals(uuidName)
                );
        if (productExists) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        ProductRecord addedProduct = new ProductRecord(uuidName, name, categoryName, price);
        addedProducts.add(addedProduct);
        return addedProduct;
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return addedProducts.stream() // Skapa en stream av addedProducts
                .filter(product -> product.UUID_value().equals(uuid)) // Filtrera efter det givna UUID
                .findFirst();// Hämta den första produkten som matchar eller returnera tomt
    }

    public void updateProductPrice(UUID uuid, BigDecimal newPrice) {
        getProductById(uuid).ifPresentOrElse(product -> {
            ProductRecord updatedProduct = product.setPrice(newPrice);
            addedProducts.set(addedProducts.indexOf(product), updatedProduct);
            changedProducts.add(product);
        }, () -> {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        });
    }

    public List<ProductRecord> getChangedProducts() {
        return Collections.unmodifiableList(changedProducts);
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(addedProducts);
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return addedProducts.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return addedProducts.stream()
                .filter(product -> product.category().equals(category))
                .toList();
    }

}


