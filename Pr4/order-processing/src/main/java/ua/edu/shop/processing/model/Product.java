package ua.edu.shop.processing.model;

public abstract class Product {
    private final String id;
    private final String name;
    private final double price;

    protected Product(String id, String name, double price) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Product ID cannot be empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Product name cannot be empty");
        if (!Double.isFinite(price) || price <= 0) throw new IllegalArgumentException("Product price must be positive");
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public abstract String getCategory();
    @Override public String toString() { return getCategory() + " [" + id + "] " + name + " (" + price + " UAH)"; }
}
