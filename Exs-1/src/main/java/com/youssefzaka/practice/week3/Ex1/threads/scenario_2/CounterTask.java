package com.youssefzaka.practice.week3.Ex1.threads.scenario_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents a task that increments two counters concurrently:
 * 1. An AtomicInteger counter that ensures atomic operations without synchronization.
 * 2. A synchronized counter to demonstrate thread-safe increments using synchronization.
 */
public class CounterTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(CounterTask.class);

    private final AtomicInteger counter;
    private final AtomicInteger synchronizedCounter;

    /**
     * Constructor to inject both counters.
     *
     * @param counter           The AtomicInteger counter for lock-free increments.
     * @param synchronizedCounter The synchronized counter for thread-safe increments.
     */
    public CounterTask(AtomicInteger counter, AtomicInteger synchronizedCounter) {
        this.counter = counter;
        this.synchronizedCounter = synchronizedCounter;
    }

    /**
     * The run method increments both counters.
     * - The counter is incremented using AtomicInteger for lock-free concurrency.
     * - The synchronizedCounter is incremented within a synchronized block to ensure sequential access.
     */
    @Override
    public void run() {
        // Increment AtomicInteger (lock-free)
        counter.incrementAndGet();
        logger.info("Counter: {}", counter);

        // Increment synchronized counter (thread-safe using synchronized block)
        synchronized (synchronizedCounter) {
            synchronizedCounter.incrementAndGet();
            logger.info("Synchronized Counter: {}", synchronizedCounter);
        }
    }
}
