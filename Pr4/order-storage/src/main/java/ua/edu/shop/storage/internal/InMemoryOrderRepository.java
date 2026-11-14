package ua.edu.shop.storage.internal;

import ua.edu.shop.processing.model.Order;
import ua.edu.shop.processing.model.Product;
import ua.edu.shop.storage.api.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class InMemoryOrderRepository implements OrderRepository {
    private final ConcurrentMap<String, Order<? extends Product>> orders = new ConcurrentHashMap<>();
    @Override public <T extends Product> void save(Order<T> order) {
        if (orders.putIfAbsent(order.getId(), order) != null) {
            throw new IllegalArgumentException("Duplicate order ID: " + order.getId());
        }
    }
    @Override public Optional<Order<? extends Product>> findById(String id) { return Optional.ofNullable(orders.get(id)); }
    @Override public List<Order<? extends Product>> findAll() { return new ArrayList<>(orders.values()); }
}
