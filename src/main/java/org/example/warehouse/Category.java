package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {

    private final String categoryName;
    private static final Map<String, Category> instances = new HashMap<>();


    private Category(String categoryName) {
        this.categoryName = formatCategoryName(categoryName);
    }

    public static Category of(String name) {
        Objects.requireNonNull(name, "Category name can't be null");
        return instances.computeIfAbsent(name, Category::new);
    }

    private static String formatCategoryName(String name) {
        name = name.trim().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getName() {
        return categoryName;
    }

}
