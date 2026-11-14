package ua.edu.shop.processing.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Clothing extends Product {
    private final String size;
    private final String material;

    @Builder
    public Clothing(String id, String name, double price, String size, String material) {
        super(id, name, price);
        if (size == null || size.isBlank()) throw new IllegalArgumentException("Size is required");
        if (material == null || material.isBlank()) throw new IllegalArgumentException("Material is required");
        this.size = size;
        this.material = material;
    }
    @Override public String getCategory() { return "Clothing"; }
}
