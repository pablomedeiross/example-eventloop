package io.example.eventloop.v2;

import io.example.eventloop.EventLoop;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Publisher;

public class SimplePublisher implements Publisher<Integer> {
    private final EventLoop eventLoop;

    public SimplePublisher(EventLoop eventLoop) {
        this.eventLoop = eventLoop;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(new Subscription() {
            int count = 0;

            @Override
            public void request(long n) {
                for (int i = 0; i < n; i++) {
                    eventLoop.execute(() -> subscriber.onNext(count++));
                }
            }

            @Override
            public void cancel() {
            }
        });
    }
}
