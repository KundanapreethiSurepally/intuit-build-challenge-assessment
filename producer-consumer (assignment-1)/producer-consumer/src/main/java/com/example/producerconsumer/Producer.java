package com.example.producerconsumer;

import java.util.List;

/**
 * Producer reads from a source list and puts items into the shared buffer.
 * After producing all items it sends one or more sentinel values (-1) to signal consumers to stop.
 */
public class Producer implements Runnable {
    private final List<Integer> source;
    private final BoundedBuffer buffer;
    private final int sentinelCount; // how many sentinel values to send
    private final int sentinel = -1;

    public Producer(List<Integer> source, BoundedBuffer buffer, int sentinelCount) {
        this.source = source;
        this.buffer = buffer;
        this.sentinelCount = sentinelCount;
    }

    @Override
    public void run() {
        try {
            // Produce all items
            for (Integer item : source) {
                buffer.put(item);
            }
            // Send sentinel values so consumers will stop
            for (int i = 0; i < sentinelCount; i++) {
                buffer.put(sentinel);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}