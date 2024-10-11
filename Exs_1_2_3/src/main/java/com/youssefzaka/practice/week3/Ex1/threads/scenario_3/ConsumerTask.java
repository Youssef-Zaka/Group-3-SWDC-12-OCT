package com.youssefzaka.practice.week3.Ex1.threads.scenario_3;

import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;

/**
 * This class represents the Consumer in the Producer-Consumer problem.
 * It consumes items from a shared buffer in a loop.
 */
public class ConsumerTask implements Runnable {
    private final Buffer buffer;

    /**
     * Constructor to inject the shared buffer.
     *
     * @param buffer The shared buffer from which the consumer will consume items.
     */
    public ConsumerTask(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * The run method consumes items from the buffer.
     * The consumer retrieves 10 items from the buffer, pausing between consumptions.
     * The consumed items are logged for reference.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int item = buffer.consume();  // Consume an item from the buffer
                // Use the return value by logging the consumed item
                System.out.println("Consumed: " + item);  // Or use a logger
                Thread.sleep(150);  // Simulate consumption time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restore interrupt status
        }
    }
}
