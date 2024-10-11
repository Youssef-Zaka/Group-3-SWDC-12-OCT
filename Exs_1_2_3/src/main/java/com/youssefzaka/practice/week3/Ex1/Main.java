package com.youssefzaka.practice.week3.Ex1;

public class Main {
    public static void main(String[] args) {
        // Thread 1: Using Lambda Expression
        Thread thread1 = new Thread(() -> System.out.println("Message from Thread 1 - Lambda Expression"));

        // Thread 2: Using Runnable Interface
        Thread thread2 = new Thread(new RunnableTask());

        // Thread 3: Extending Thread Class
        Thread thread3 = new CustomThread();

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
            e.printStackTrace();
        }

        System.out.println("All threads have finished execution.");
    }
}


// Task class using Runnable Interface
class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Message from Thread 2 - Runnable Interface");
    }
}

// Custom Thread class by extending Thread
class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("Message from Thread 3 - Thread Class");
    }
}