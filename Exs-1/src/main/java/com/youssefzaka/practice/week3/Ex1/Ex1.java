package com.youssefzaka.practice.week3.Ex1;
import com.youssefzaka.practice.week3.Ex1.scenarios.Scenarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.youssefzaka.practice.week3.Ex1.scenarios.Scenarios.logSeparator;

/**
 * Main class for running multi-threading scenarios in EX1.
 * This class orchestrates the execution of different threading scenarios,
 * such as multithreading basics, synchronization, and producer-consumer problem.
 */
public class Ex1 {
    // SLF4J Logger instance for logging the progress of the application
    private static final Logger logger = LoggerFactory.getLogger(Ex1.class);

    /**
     * The main method where the program starts execution.
     * It sequentially runs various threading scenarios and logs the progress.
     */
    public static void main(String[] args) {

        // Log the start of the multi-threading scenarios testing
        logger.info("Starting testing scenarios for Multi-Threading (EX1)");
        logSeparator();  // Log a separator to improve readability of logs

        // Running the first threading scenario (basic multithreading)
        logger.info("Running the first scenario");
        Scenarios.runFirstScenario();  // Execute the first scenario
        logSeparator();  // Log a separator after the scenario finishes

        // Running the second threading scenario (synchronization, thread pools, Lock-Free Algorithms)
        logger.info("Running the second scenario");
        Scenarios.runSecondScenario();  // Execute the second scenario
        logSeparator();  // Log a separator after the scenario finishes

        // Running the third threading scenario (Producer-Consumer problem)
        logger.info("Running the third scenario");
        Scenarios.runThirdScenario();  // Execute the third scenario
        logSeparator();  // Log a separator after the scenario finishes

        // Log the completion of all threading scenarios
        System.out.println("All Scenarios have finished execution.");

    }
}



