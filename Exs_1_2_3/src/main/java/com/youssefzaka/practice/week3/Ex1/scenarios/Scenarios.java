package com.youssefzaka.practice.week3.Ex1.scenarios;
import com.youssefzaka.practice.week3.Ex1.threads.AsyncRunnableTask;
import com.youssefzaka.practice.week3.Ex1.threads.AsyncThreadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenarios {
    // SLF4J Logger instance
    private static final Logger logger = LoggerFactory.getLogger(Scenarios.class);

    public  static void logSeparator() {
        logger.info("---------------------------------------------");
    }

    public static void runFirstScenario() {
        // Thread 1: Using Lambda Expression
        Thread thread1 = new Thread(() -> logger.info("Message from Thread 1 - Lambda Expression"));

        // Thread 2: Using Runnable Interface
        Thread thread2 = new Thread(new AsyncRunnableTask());

        // Thread 3: Extending Thread Class
        Thread thread3 = new AsyncThreadTask();

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
}
