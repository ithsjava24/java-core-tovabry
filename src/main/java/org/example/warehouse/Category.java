package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Category {

    private final String categoryName;
    private static final Map<String, Category> instances = new HashMap<>();


    private Category(String categoryName) {
        this.categoryName = categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        return instances.computeIfAbsent(name, Category::new);
    }

    public String getName() {
        return categoryName;
    }

}
