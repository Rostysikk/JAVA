package ua.edu.shop.processing.service;

import ua.edu.shop.processing.exception.OrderProcessingException;
import ua.edu.shop.processing.model.Order;
import ua.edu.shop.processing.model.OrderStatus;
import ua.edu.shop.processing.model.Product;

public final class OrderProcessor<T extends Product> {
    public void processOrder(Order<T> order) {
        if (!order.changeStatus(OrderStatus.NEW, OrderStatus.PROCESSING)) {
            throw new OrderProcessingException("Order " + order.getId() + " is not NEW");
        }
        try {
            System.out.printf("[%s] Processing %s: %s, total %.2f UAH%n",
                Thread.currentThread().getName(), order.getId(), order.getProduct().getName(), order.getTotal());
            // Imitation of external payment / warehouse service latency.
            Thread.sleep(120);
            if (order.getTotal() > 100_000) {
                throw new OrderProcessingException("Manual review required for expensive order " + order.getId());
            }
            order.changeStatus(OrderStatus.PROCESSING, OrderStatus.COMPLETED);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            order.changeStatus(OrderStatus.PROCESSING, OrderStatus.FAILED);
            throw new OrderProcessingException("Processing interrupted: " + order.getId(), ex);
        } catch (RuntimeException ex) {
            order.changeStatus(OrderStatus.PROCESSING, OrderStatus.FAILED);
            throw ex instanceof OrderProcessingException ? ex : new OrderProcessingException("Unexpected error", ex);
        }
    }
}
