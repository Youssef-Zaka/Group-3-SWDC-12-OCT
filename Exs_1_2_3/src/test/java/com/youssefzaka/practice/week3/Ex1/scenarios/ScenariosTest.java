package com.youssefzaka.practice.week3.Ex1.scenarios;

import com.youssefzaka.practice.week3.Ex1.shared.buffers.BufferUsingBlockingQueue;
import com.youssefzaka.practice.week3.Ex1.shared.buffers.BufferUsingLocks;
import com.youssefzaka.practice.week3.Ex1.shared.buffers.BufferUsingWaitNotify;
import com.youssefzaka.practice.week3.Ex1.shared.interfaces.Buffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Unit tests for the Scenarios class that runs different threading scenarios.
 * <p>
 *     The tests are run with a timeout to ensure that the scenarios run within a reasonable time, and
 *     to make sure no deadlocks or infinite loops are present in the code.
 */
class ScenariosTest {

    @Test
    void testFirstScenario() {
        // Test the first scenario which runs three types of threads
        assertTimeout(java.time.Duration.ofSeconds(1), Scenarios::runFirstScenario);
    }

    @Test
    void testSecondScenario() {
        // Test the second scenario with synchronization and thread pools
        assertTimeout(java.time.Duration.ofSeconds(1), Scenarios::runSecondScenario);
    }

    @Test
    void testThirdScenario() {
        // Test the producer-consumer problem scenario with different buffer implementations
        assertTimeout(java.time.Duration.ofSeconds(10), Scenarios::runThirdScenario);
    }
}
