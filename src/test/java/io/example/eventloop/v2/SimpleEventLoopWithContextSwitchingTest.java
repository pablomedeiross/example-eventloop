package io.example.eventloop.v2;

import org.junit.jupiter.api.Test;

public class SimpleEventLoopWithContextSwitchingTest {

    private SimpleEventLoopWithFewContextSwitching eventLoop = new SimpleEventLoopWithFewContextSwitching();

    @Test
    void test() throws InterruptedException {

        SimplePublisher publisher = new SimplePublisher(eventLoop);
        SimpleSubscriber subscriber = new SimpleSubscriber(eventLoop);
        publisher.subscribe(subscriber);
    }

}
