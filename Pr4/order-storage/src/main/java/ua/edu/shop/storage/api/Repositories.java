package ua.edu.shop.storage.api;

import ua.edu.shop.storage.internal.InMemoryOrderRepository;
public final class Repositories {
    private Repositories() { }
    public static OrderRepository inMemory() { return new InMemoryOrderRepository(); }
}
