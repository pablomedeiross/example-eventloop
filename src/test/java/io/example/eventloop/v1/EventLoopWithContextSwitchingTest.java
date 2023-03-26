package io.example.eventloop.v1;

import org.junit.jupiter.api.Test;

class EventLoopWithContextSwitchingTest {

    @Test
    void testEventLoopExecution() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " Thread main name");

        EventLoopWithFewContextSwitching eventLoop = new EventLoopWithFewContextSwitching();
        eventLoop.start();

        Publisher<String> publisher = new Publisher<>(eventLoop);



        publisher.publish("Publication 1");
        publisher.publish("Publication 2");
        publisher.publish("Publication 3");

        publisher.subscribe(data -> {
            System.out.println("Data received: " + data);
            System.out.println("Name of thread that is performing data processing in the consumer " + Thread.currentThread().getName());
        });

        Thread.sleep(4000);
        eventLoop.stop();
        publisher.publish("Publication 4 - should not be printed");
    }
}
