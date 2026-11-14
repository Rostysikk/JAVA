package ua.edu.shop.concurrency.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
public final class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger();
    @Override public Thread newThread(Runnable task) { return new Thread(task, "order-worker-" + counter.incrementAndGet()); }
}
