package com.example.producerconsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Main demonstration application. Creates a producer and consumer, runs them and prints results.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        // Source data
        List<Integer> src = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Destination container (shared among consumers)
        List<Integer> dest = Collections.synchronizedList(new ArrayList<>());

        // Bounded buffer capacity 3 to force blocking behavior in the demo
        BoundedBuffer buffer = new BoundedBuffer(3);

        // One producer and one consumer
        Producer producer = new Producer(src, buffer, 1); // one consumer sentinel
        Consumer consumer = new Consumer(buffer, dest);

        Thread prodThread = new Thread(producer, "Producer-1");
        Thread consThread = new Thread(consumer, "Consumer-1");

        prodThread.start();
        consThread.start();

        // Wait for completion
        prodThread.join();
        consThread.join();

        System.out.println("src: " + src);
        System.out.println("Destination: " + dest);
        System.out.println("Items transferred: " + dest.size());

        System.out.println("Process finished.");
    }
}