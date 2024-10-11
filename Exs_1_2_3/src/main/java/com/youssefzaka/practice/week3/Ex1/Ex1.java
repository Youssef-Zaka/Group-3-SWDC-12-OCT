package com.youssefzaka.practice.week3.Ex1;
import com.youssefzaka.practice.week3.Ex1.scenarios.Scenarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.youssefzaka.practice.week3.Ex1.scenarios.Scenarios.logSeparator;


public class Ex1 {
    // SLF4J Logger instance
    private static final Logger logger = LoggerFactory.getLogger(Ex1.class);

    public static void main(String[] args) {

        //Log start of testing Scenarios for Threading EX1
        logger.info("Starting testing scenarios for Multi-Threading (EX1)");
        logger.info("Running the first scenario");
        logSeparator();

        // Running the first threading scenario
        Scenarios.runFirstScenario();
        logSeparator();

        //Log end of testing Scenarios for Threading EX1
        logSeparator();
        System.out.println("All Scenarios have finished execution.");
    }
}



