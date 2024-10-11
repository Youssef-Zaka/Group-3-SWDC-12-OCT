package com.youssefzaka.practice.week3.Ex1.shared.buffers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Buffer implementation using Java's BlockingQueue for thread-safe operations.
 * The producer adds items to the buffer, and the consumer removes them.
 * <p>
 * BlockingQueue provides built-in thread safety and blocking methods:
 * - When the buffer is full, the producer blocks until space is available.
 * - When the buffer is empty, the consumer blocks until an item is available.
 */
public class BufferUsingBlockingQueue implements Buffer {
    private final BlockingQueue<Integer> queue;
    private static final Logger logger = LoggerFactory.getLogger(BufferUsingBlockingQueue.class);

    /**
     * Constructor to initialize the BlockingQueue with a given capacity.
     *
     * @param capacity The maximum number of items the buffer can hold.
     */
    public BufferUsingBlockingQueue(int capacity) {
        // Initialize the BlockingQueue with the given capacity
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    /**
     * Adds an item to the buffer. If the buffer is full, the producer blocks until space is available.
     *
     * @param item The item to add to the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    @Override
    public void produce(int item) throws InterruptedException {
        // BlockingQueue's put method blocks if the queue is full
        queue.put(item);
        logger.info("Produced: {}", item);
    }

    /**
     * Removes and returns an item from the buffer. If the buffer is empty, the consumer blocks until an item is available.
     *
     * @return The item removed from the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    @Override
    public int consume() throws InterruptedException {
        // BlockingQueue's take method blocks if the queue is empty
        int item = queue.take();
        logger.info("Consumed: {}", item);
        return item;
    }
}
