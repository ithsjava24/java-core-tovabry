package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record ProductRecord (UUID UUID_value, String UUID_name, Category categoryName, BigDecimal price) {

    public ProductRecord {
        if (UUID_value == null) {
            UUID_value = UUID.randomUUID();
        }
        if (UUID_name == null || UUID_name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (categoryName == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        price = (price != null) ? price : BigDecimal.ZERO;
    }

    public UUID uuid() {
        return UUID_value; // returnerar den redan satta UUID
    }

    public ProductRecord setPrice(BigDecimal newPrice) {
        return new ProductRecord(this.UUID_value, this.UUID_name, this.categoryName, newPrice);
    }

    public Category category() {
        return categoryName;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecord that)) return false;
        return Objects.equals(UUID_value, that.UUID_value) && Objects.equals(UUID_name, that.UUID_name) && Objects.equals(price, that.price) && Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UUID_value, UUID_name, categoryName, price);
    }

}
