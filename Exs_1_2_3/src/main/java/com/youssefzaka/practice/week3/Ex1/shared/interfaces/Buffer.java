package com.youssefzaka.practice.week3.Ex1.shared.interfaces;

/**
 * The Buffer interface defines the contract for a shared buffer used in the Producer-Consumer problem.
 * Implementations of this interface handle thread-safe production and consumption of items.
 * This allows producers to add items to the buffer and consumers to retrieve them.
 */
public interface Buffer {

    /**
     * The produce method allows a producer to add an item to the buffer.
     * Implementations should handle cases where the buffer is full and block the producer until space is available.
     *
     * @param item The item to be added to the buffer.
     * @throws InterruptedException if the thread is interrupted while waiting to add the item.
     */
    void produce(int item) throws InterruptedException;

    /**
     * The consume method allows a consumer to retrieve an item from the buffer.
     * Implementations should handle cases where the buffer is empty and block the consumer until an item is available.
     *
     * @return The item retrieved from the buffer.
     * @throws InterruptedException if the thread is interrupted while waiting to retrieve the item.
     */
    int consume() throws InterruptedException;
}
