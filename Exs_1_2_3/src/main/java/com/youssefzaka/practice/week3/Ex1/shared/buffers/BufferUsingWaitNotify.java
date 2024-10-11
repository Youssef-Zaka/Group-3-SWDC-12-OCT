package com.youssefzaka.practice.week3.Ex1.shared.buffers;

import java.util.LinkedList;
import java.util.Queue;

import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Buffer implementation using the wait/notify mechanism for synchronization.
 * This buffer is shared between producers and consumers in a multi-threaded environment.
 * The producer adds items to the buffer, and the consumer removes them.
 * <p>
 * - When the buffer is full, the producer waits.
 * - When the buffer is empty, the consumer waits.
 */
public class BufferUsingWaitNotify implements Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private static final Logger logger = LoggerFactory.getLogger(BufferUsingWaitNotify.class);

    /**
     * Constructor to initialize the buffer with a given capacity.
     *
     * @param capacity The maximum number of items the buffer can hold.
     */
    public BufferUsingWaitNotify(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds an item to the buffer. If the buffer is full, the producer thread waits.
     *
     * @param item The item to add to the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    @Override
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            logger.info("Buffer is full, producer is waiting.");
            wait();  // Wait until there is space in the buffer
        }

        queue.add(item);
        logger.info("Produced: {}", item);
        notify();  // Notify waiting consumers
    }

    /**
     * Removes and returns an item from the buffer. If the buffer is empty, the consumer thread waits.
     *
     * @return The item removed from the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    @Override
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            logger.info("Buffer is empty, consumer is waiting.");
            wait();  // Wait until there is an item in the buffer
        }

        int item = queue.poll();
        logger.info("Consumed: {}", item);
        notify();  // Notify waiting producers
        return item;
    }
}
