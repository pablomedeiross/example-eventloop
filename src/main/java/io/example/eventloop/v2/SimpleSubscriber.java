package io.example.eventloop.v2;

import io.example.eventloop.EventLoop;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;

public class SimpleSubscriber<T> implements Subscriber<T> {
    private final EventLoop eventLoop;

    public SimpleSubscriber(EventLoop eventLoop) {
        this.eventLoop = eventLoop;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Subscribed!");
        System.out.println("Executed in thread " + Thread.currentThread().getName());
        eventLoop.execute(() -> subscription.request(5));
    }

    @Override
    public void onNext(T t) {
        eventLoop.execute(() -> System.out.println("Received: " + t + " on thread: " + Thread.currentThread().getName()));
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        eventLoop.execute(() -> {
            System.out.println("Completed!");
            eventLoop.stop();
        });
    }
}
