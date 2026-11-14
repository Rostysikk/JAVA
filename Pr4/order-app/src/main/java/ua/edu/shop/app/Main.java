package ua.edu.shop.app;

import ua.edu.shop.concurrency.api.OrderExecutor;
import ua.edu.shop.processing.model.*;
import ua.edu.shop.processing.service.OrderProcessor;
import ua.edu.shop.storage.api.OrderRepository;
import ua.edu.shop.storage.api.Repositories;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public final class Main {
    public static void main(String[] args) {
        DemoDataFactory factory = new DemoDataFactory();
        OrderRepository repository = Repositories.inMemory();
        OrderProcessor<Electronics> electronicsProcessor = new OrderProcessor<>();
        OrderProcessor<Clothing> clothingProcessor = new OrderProcessor<>();
        List<Future<?>> tasks = new ArrayList<>();

        System.out.println("=== MULTITHREADED ORDER MANAGEMENT ===");
        try (OrderExecutor executor = new OrderExecutor(4, repository)) {
            factory.electronics(6).forEach(order -> tasks.add(executor.submit(order, electronicsProcessor::processOrder)));
            factory.clothing(6).forEach(order -> tasks.add(executor.submit(order, clothingProcessor::processOrder)));
            executor.awaitAll(tasks);
        }
        List<Order<? extends Product>> all = repository.findAll();
        all.sort(Comparator.comparing(Order::getId));
        System.out.println("\n=== ORDER REPORT ===");
        all.forEach(System.out::println); // Method reference
        long completed = all.stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED).count();
        double revenue = all.stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED)
            .mapToDouble(Order::getTotal).sum();
        System.out.printf("Completed: %d/%d; revenue: %.2f UAH%n", completed, all.size(), revenue);
        System.out.println("Electronics orders: " + all.stream()
            .filter(order -> order.getProduct() instanceof Electronics).count());
        System.out.println("Clothing orders: " + all.stream()
            .filter(order -> order.getProduct() instanceof Clothing).count());
    }
}
