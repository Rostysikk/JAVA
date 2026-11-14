package ua.edu.shop.processing.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Electronics extends Product {
    private final String brand;
    private final int warrantyMonths;

    @Builder
    public Electronics(String id, String name, double price, String brand, int warrantyMonths) {
        super(id, name, price);
        if (brand == null || brand.isBlank()) throw new IllegalArgumentException("Brand is required");
        if (warrantyMonths < 0) throw new IllegalArgumentException("Warranty cannot be negative");
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }
    @Override public String getCategory() { return "Electronics"; }
}
