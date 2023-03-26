package io.example.eventloop.v1;

import io.example.eventloop.EventLoop;
import java.util.function.Consumer;

public class Subscription<T> {

    private final EventLoop eventLoop;
    private final Consumer<T> consumer;

    public Subscription(EventLoop eventLoop, Consumer<T> consumer) {
        this.eventLoop = eventLoop;
        this.consumer = consumer;
    }

    public void publish(T data) {
        eventLoop.execute(() -> consumer.accept(data));
    }
}