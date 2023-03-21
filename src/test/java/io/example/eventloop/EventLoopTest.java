package io.example.eventloop;

import org.junit.jupiter.api.Test;

class EventLoopTest {

    @Test
    void testeExecucaoEventLoopExemplo() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " Thread main name");

        EventLoop eventLoop = new EventLoop();
        eventLoop.start();

        Publisher<String> publisher = new Publisher<>(eventLoop);

        publisher.subscribe(data -> {
            System.out.println("Data received: " + data);
            System.out.println("Name of thread that is performing data processing in the consumer " + Thread.currentThread().getName());
        });

        publisher.publish("Publication 1");
        publisher.publish("Publication 2");
        publisher.publish("Publication 3");

        Thread.sleep(4000);
        eventLoop.stop();
        publisher.publish("Publication 4 - should not be printed");
    }
}
