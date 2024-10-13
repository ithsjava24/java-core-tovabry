package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record ProductRecord (UUID productId, String name, Category categoryName, BigDecimal price) {

    public ProductRecord {
        productId = Objects.requireNonNullElseGet(productId, UUID::randomUUID);

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (categoryName == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }

        price = (price != null) ? price : BigDecimal.ZERO;
    }

    public UUID uuid() {
        return productId;
    }

    public ProductRecord setPrice(BigDecimal newPrice) {
        return new ProductRecord(this.productId, this.name, this.categoryName, newPrice);
    }

    public Category category() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecord that)) return false;
        return Objects.equals(productId, that.productId) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, categoryName, price);
    }

}
