package com.youssefzaka.practice.week3.Ex1.threads.scenario_3;

import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;

/**
 * This class represents the Producer in the Producer-Consumer problem.
 * It produces items and places them into a shared buffer.
 */
public class ProducerTask implements Runnable {
    private final Buffer buffer;

    /**
     * Constructor to inject the shared buffer.
     *
     * @param buffer The shared buffer where the producer will add items.
     */
    public ProducerTask(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * The run method produces items and adds them to the buffer.
     * The producer adds 10 items to the buffer, pausing between productions.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.produce(i + 1);  // Produce an item and add it to the buffer
                Thread.sleep(100);  // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restore interrupt status
        }
    }
}
