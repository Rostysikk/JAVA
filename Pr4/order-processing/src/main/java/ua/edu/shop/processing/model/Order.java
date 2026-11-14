package ua.edu.shop.processing.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public final class Order<T extends Product> {
    private final String id;
    private final T product;
    private final int quantity;
    private final LocalDateTime createdAt;
    private final AtomicReference<OrderStatus> status = new AtomicReference<>(OrderStatus.NEW);

    public Order(String id, T product, int quantity) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Order ID is required");
        this.product = Objects.requireNonNull(product, "Product is required");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.id = id;
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
    }
    public String getId() { return id; }
    public T getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderStatus getStatus() { return status.get(); }
    public boolean changeStatus(OrderStatus expected, OrderStatus updated) { return status.compareAndSet(expected, updated); }
    public double getTotal() { return product.getPrice() * quantity; }
    @Override public String toString() { return "Order " + id + ": " + product.getName() + " x" + quantity + " / " + getStatus(); }
}
