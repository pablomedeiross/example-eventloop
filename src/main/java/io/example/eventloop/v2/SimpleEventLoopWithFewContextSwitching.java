package io.example.eventloop.v2;

import io.example.eventloop.EventLoop;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleEventLoopWithFewContextSwitching implements EventLoop {

    private final Thread eventLoopThread;
    private final Queue<Runnable> taskQueue = new LinkedList<>();
    private volatile boolean running = true;

    public SimpleEventLoopWithFewContextSwitching() {
        eventLoopThread = new Thread(() -> {
            while (running) {
                Runnable task = null;
                synchronized (taskQueue) {
                    if (!taskQueue.isEmpty()) {
                        task = taskQueue.poll();
                    }
                }
                if (task != null) {
                    task.run();
                }
            }
        });
        eventLoopThread.start();
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.offer(task);
        }
    }

    public void stop() {
        this.running = false;
        this.eventLoopThread.interrupt();
    }
}

