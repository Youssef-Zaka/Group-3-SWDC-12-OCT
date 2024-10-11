package com.youssefzaka.practice.week3.Ex1.scenarios;

import com.youssefzaka.practice.week3.Ex1.shared.buffers.BufferUsingBlockingQueue;
import com.youssefzaka.practice.week3.Ex1.shared.buffers.BufferUsingLocks;
import com.youssefzaka.practice.week3.Ex1.shared.buffers.BufferUsingWaitNotify;
import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;
import com.youssefzaka.practice.week3.Ex1.threads.scenario_2.CounterTask;
import com.youssefzaka.practice.week3.Ex1.threads.scenario_1.RunnableTask;
import com.youssefzaka.practice.week3.Ex1.threads.scenario_1.ThreadTask;
import com.youssefzaka.practice.week3.Ex1.threads.scenario_3.ConsumerTask;
import com.youssefzaka.practice.week3.Ex1.threads.scenario_3.ProducerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Scenarios class orchestrates various multi-threading scenarios.
 * It demonstrates basic threading, synchronization using locks and atomic variables,
 * and the producer-consumer problem using different synchronization mechanisms.
 */
public class Scenarios {
    // SLF4J Logger instance for logging scenario progress
    private static final Logger logger = LoggerFactory.getLogger(Scenarios.class);

    /**
     * Logs a separator line for better log readability between scenarios.
     */
    public static void logSeparator() {
        logger.info("---------------------------------------------");
    }

    /**
     * Runs the first scenario, which demonstrates three different ways of creating and running threads:
     * <ol>
     *   <li>Lambda Expression</li>
     *   <li>Runnable Interface</li>
     *   <li>Extending the Thread class</li>
     * </ol>
     */
    public static void runFirstScenario() {
        // Thread 1: Using Lambda Expression
        Thread thread1 = new Thread(() -> logger.info("Message from Thread 1 - Lambda Expression"));

        // Thread 2: Using Runnable Interface
        Thread thread2 = new Thread(new RunnableTask());

        // Thread 3: Extending Thread Class
        Thread thread3 = new ThreadTask();

        // Starting threads
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            // Joining threads back to the main thread
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            // Log the exception with an error level
            logger.error("Thread interrupted: ", e);
        }
    }

    /**
     * Runs the second scenario, which demonstrates the use of synchronization, thread pools, and lock-free algorithms.
     * It uses AtomicInteger for lock-free increments and a synchronized counter for thread-safe increments.
     */
    public static void runSecondScenario() {
        // Use AtomicInteger to handle the counters
        AtomicInteger counter = new AtomicInteger(0);
        AtomicInteger synchronizedCounter = new AtomicInteger(0);

        // Create a thread pool with 5 threads
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {

            // Submit 10 tasks to the thread pool
            for (int i = 0; i < 10; i++) {
                executorService.submit(new CounterTask(counter, synchronizedCounter));
            }

            // Shutdown the executor service and wait for tasks to complete
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                logger.error("Executor service interrupted: ", e);
            }
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
        }

        // Log final values
        logger.info("Final value of counter: {}", counter);
        logger.info("Final value of synchronizedCounter: {}", synchronizedCounter);
    }

    /**
     * Runs the third scenario, which demonstrates the producer-consumer problem.
     * This scenario is executed three times with different buffer implementations:
     * <ol>
     *   <li>Buffer using Locks</li>
     *   <li>Buffer using Wait/Notify</li>
     *   <li>Buffer using BlockingQueue</li>
     * </ol>
     */
    public static void runThirdScenario() {
        // Run the test with a Locks-based buffer
        logger.info("Running the test with a Locks-based buffer");
        Buffer bufferWithLocks = new BufferUsingLocks(2);
        RunProducerConsumer(bufferWithLocks);
        logger.info(" ");

        // Run the test with a wait-notify buffer
        logger.info("Running the test with a Wait-Notify buffer");
        Buffer bufferWithWaitNotify = new BufferUsingWaitNotify(2);
        RunProducerConsumer(bufferWithWaitNotify);
        logger.info(" ");

        // Run the test with a blocking queue buffer
        logger.info("Running the test with a Blocking-Queue buffer");
        Buffer bufferWithBlockingQueue = new BufferUsingBlockingQueue(2);
        RunProducerConsumer(bufferWithBlockingQueue);
    }

    /**
     * Runs a generic producer-consumer scenario with the given buffer implementation.
     * This method is used by the third scenario to run the test with different buffer types.
     *
     * @param buffer The buffer to be used by the producer and consumer.
     */
    static void RunProducerConsumer(Buffer buffer) {
        // Create producer and consumer tasks
        ProducerTask producerTask = new ProducerTask(buffer);
        ConsumerTask consumerTask = new ConsumerTask(buffer);

        // Create threads for producer and consumer tasks
        Thread producerThread = new Thread(producerTask);
        Thread consumerThread = new Thread(consumerTask);

        // Start producer and consumer threads
        producerThread.start();
        consumerThread.start();

        try {
            // Join producer and consumer threads back to the main thread
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            // Log the exception with an error level
            logger.error("Thread interrupted: ", e);
        }
    }
}
