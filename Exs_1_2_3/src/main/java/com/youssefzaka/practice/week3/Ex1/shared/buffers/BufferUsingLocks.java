package com.youssefzaka.practice.week3.Ex1.shared.buffers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Buffer implementation using explicit Locks and Conditions.
 * This buffer is shared between producers and consumers in a multithreaded environment.
 * The producer adds items to the buffer, and the consumer removes them.
 * <p>
 * The buffer uses Lock and Condition variables to coordinate producer-consumer operations:
 * - When the buffer is full, the producer waits.
 * - When the buffer is empty, the consumer waits.
 */
public class BufferUsingLocks implements Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private static final Logger logger = LoggerFactory.getLogger(BufferUsingLocks.class);

    /**
     * Constructor to initialize the buffer with a given capacity.
     *
     * @param capacity The maximum number of items the buffer can hold.
     */
    public BufferUsingLocks(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds an item to the buffer. If the buffer is full, the producer thread waits.
     *
     * @param item The item to add to the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    @Override
    public void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                logger.info("Buffer is full, producer is waiting.");
                notFull.await();  // Wait until there is space in the buffer
            }
            queue.add(item);
            logger.info("Produced: {}", item);
            notEmpty.signal();  // Signal that the buffer is not empty
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes and returns an item from the buffer. If the buffer is empty, the consumer thread waits.
     *
     * @return The item removed from the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    @Override
    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                logger.info("Buffer is empty, consumer is waiting.");
                notEmpty.await();  // Wait until there is an item in the buffer
            }
            int item = queue.poll();
            logger.info("Consumed: {}", item);
            notFull.signal();  // Signal that the buffer is not full
            return item;
        } finally {
            lock.unlock();
        }
    }
}
