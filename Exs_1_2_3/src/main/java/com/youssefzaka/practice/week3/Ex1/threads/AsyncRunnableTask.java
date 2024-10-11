package com.youssefzaka.practice.week3.Ex1.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Task class using Runnable Interface
public class AsyncRunnableTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(AsyncRunnableTask.class);

    @Override
    public void run() {
        logger.info("Message from Thread 2 - Runnable Interface");
    }
}