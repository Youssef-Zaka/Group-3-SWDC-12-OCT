package com.youssefzaka.practice.week3.Ex1.threads.scenario_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class demonstrates creating a thread by extending the Thread class.
 * When the thread is run, it logs a message to indicate that it is running.
 */
public class ThreadTask extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ThreadTask.class);

    /**
     * The run method defines the task to be executed by the thread.
     * In this case, it simply logs a message indicating the task is running.
     */
    @Override
    public void run() {
        logger.info("Message from Thread 3 - Thread Class");
    }
}
