package io.example.eventloop.v1;

import io.example.eventloop.EventLoop;
import java.util.function.Consumer;

public class Publisher<T> {

    private Subscription<T> subscription;
    private final EventLoop eventLoop;

    public Publisher(EventLoop eventLoop) {
        this.eventLoop = eventLoop;
    }

    public void subscribe(Consumer<T> consumer) {
        subscription = new Subscription<>(eventLoop, consumer);
    }

    public void publish(T data) {
        subscription.publish(data);
    }
}
