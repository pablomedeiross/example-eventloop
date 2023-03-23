package io.example.eventloop;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 This is a simple example of how is an event loop that delegates the tasks execution to another thread.
 In this example we have more context switching than processing all requests and tasks in the same thread.
 */
public class EventLoopWithContextSwitching {

    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public void run() {


        while (isRunning.get()) {
            Runnable task = taskQueue.poll();
            if (task != null) {
                task.run();
            }
        }
    }

    public void execute(Runnable task) {
        System.out.println(
                "Thread that run the execute method in the EventLoop: " + Thread.currentThread().getName());
        taskQueue.add(task);
        executor.execute(this::run);
    }

    public void stop() {
        this.isRunning.set(false);
    }

    public void start() {
        this.isRunning.set(true);
    }
}