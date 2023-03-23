package io.example.eventloop;

import java.util.function.Consumer;

public class Subscription<T> {

    private final EventLoopWithContextSwitching eventLoop;
    private final Consumer<T> consumer;

    public Subscription(EventLoopWithContextSwitching eventLoop, Consumer<T> consumer) {
        this.eventLoop = eventLoop;
        this.consumer = consumer;
    }

    public void publish(T data) {
        eventLoop.execute(() -> consumer.accept(data));
    }
}