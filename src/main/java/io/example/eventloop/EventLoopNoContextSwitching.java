package io.example.eventloop;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 This is a simple example of how is an event loop that execute requests and tasks in the same thread.
 In this example we have no context switching.
 */
public class EventLoopNoContextSwitching {

    private final Queue<Runnable> taskQueue = new ArrayDeque<>();

    public void execute(Runnable task) {
        taskQueue.add(task);
    }

    public void start() {
        while (true) {
            Runnable task = taskQueue.poll();
            if (task != null) {
                task.run();
            }
        }
    }
}
