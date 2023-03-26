package io.example.eventloop;

public interface EventLoop {

    void execute(Runnable runnable);
    void stop();
}
