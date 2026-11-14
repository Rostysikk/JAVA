package ua.edu.shop.storage.api;

import ua.edu.shop.processing.model.Order;
import ua.edu.shop.processing.model.Product;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    <T extends Product> void save(Order<T> order);
    Optional<Order<? extends Product>> findById(String id);
    List<Order<? extends Product>> findAll();
}
