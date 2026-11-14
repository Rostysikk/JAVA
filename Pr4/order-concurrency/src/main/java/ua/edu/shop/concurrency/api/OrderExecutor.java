package ua.edu.shop.concurrency.api;

import ua.edu.shop.concurrency.internal.NamedThreadFactory;
import ua.edu.shop.processing.exception.OrderProcessingException;
import ua.edu.shop.processing.model.Order;
import ua.edu.shop.processing.model.Product;
import ua.edu.shop.processing.service.OrderProcessor;
import ua.edu.shop.storage.api.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public final class OrderExecutor implements AutoCloseable {
    private final ExecutorService executor;
    private final OrderRepository repository;
    public OrderExecutor(int threads, OrderRepository repository) {
        if (threads <= 0) throw new IllegalArgumentException("threads must be positive");
        this.repository = java.util.Objects.requireNonNull(repository);
        this.executor = Executors.newFixedThreadPool(threads, new NamedThreadFactory());
    }
    public <T extends Product> Future<?> submit(Order<T> order, Consumer<Order<T>> handler) {
        repository.save(order);
        // Lambda: background task; handler can be a method reference.
        return executor.submit(() -> {
            try { handler.accept(order); }
            catch (OrderProcessingException ex) { System.err.println("Order failed: " + ex.getMessage()); }
        });
    }
    public <T extends Product> Future<?> submit(Order<T> order, OrderProcessor<T> processor) {
        return submit(order, processor::processOrder); // Method reference
    }
    public void awaitAll(List<Future<?>> tasks) {
        for (Future<?> task : tasks) {
            try { task.get(); }
            catch (InterruptedException ex) { Thread.currentThread().interrupt(); throw new OrderProcessingException("Interrupted while waiting", ex); }
            catch (ExecutionException ex) { throw new OrderProcessingException("Background task failed", ex.getCause()); }
        }
    }
    @Override public void close() {
        executor.shutdown();
        try { if (!executor.awaitTermination(10, TimeUnit.SECONDS)) executor.shutdownNow(); }
        catch (InterruptedException ex) { executor.shutdownNow(); Thread.currentThread().interrupt(); }
    }
}
