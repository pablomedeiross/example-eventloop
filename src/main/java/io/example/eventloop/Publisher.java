package io.example.eventloop;

import java.util.function.Consumer;

public class Publisher<T> {

    private Subscription<T> subscription;
    private final EventLoopWithContextSwitching eventLoop;

    public Publisher(EventLoopWithContextSwitching eventLoop) {
        this.eventLoop = eventLoop;
    }

    public void subscribe(Consumer<T> consumer) {
        subscription = new Subscription<>(eventLoop, consumer);
    }

    public void publish(T data) {
        subscription.publish(data);
    }
}
