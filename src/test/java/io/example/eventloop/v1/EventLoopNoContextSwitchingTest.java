package io.example.eventloop.v1;

import io.example.eventloop.v1.EventLoopNoContextSwitching;
import io.example.eventloop.v1.Publisher;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

class EventLoopNoContextSwitchingTest {

    @Test
    void testEventLoopExecution() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " Thread main name");

        EventLoopNoContextSwitching eventLoop = new EventLoopNoContextSwitching();
        Executors.newSingleThreadExecutor().execute(eventLoop::start);
        Publisher<String> publisher = new Publisher<>(eventLoop);

        publisher.publish("Publication 1");
        publisher.publish("Publication 2");
        publisher.publish("Publication 3");

        publisher.subscribe(data -> {
            System.out.println("Data received: " + data);
            System.out.println("Name of thread that is performing data processing in the consumer " + Thread.currentThread().getName());
        });

        Thread.sleep(4000);
    }

}
