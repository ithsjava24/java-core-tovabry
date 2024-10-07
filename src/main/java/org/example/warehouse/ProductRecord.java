package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecord (UUID UUID_value, String UUID_name, Category categoryName, BigDecimal price) {

public UUID uuid() {
    if (UUID_value == null) {
        return UUID.randomUUID();
    }
    return UUID_value;
}

public BigDecimal price() {
    if (price == null) {
        return BigDecimal.ZERO;
    }
    return price;
}
//
//
//public Category categoryName() {
//    return categoryName;
//}
//
//public BigDecimal bigDecimal() {
//    return bigDecimal;
//}

}
