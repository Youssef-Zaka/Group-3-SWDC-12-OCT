package com.youssefzaka.practice.week3.Ex1.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Custom Thread class by extending Thread
public class AsyncThreadTask extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(AsyncThreadTask.class);

    @Override
    public void run() {
        logger.info("Message from Thread 3 - Thread Class");
    }
}