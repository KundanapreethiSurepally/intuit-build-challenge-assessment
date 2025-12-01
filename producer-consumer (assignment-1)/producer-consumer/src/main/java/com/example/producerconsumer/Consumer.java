package com.example.producerconsumer;

import java.util.List;

/**
 * Consumer takes items from the buffer and appends them to the destination list.
 * It stops when it reads the sentinel value (-1).
 */
public class Consumer implements Runnable {
    private final BoundedBuffer buffer;
    private final List<Integer> destination;
    private final int sentinel = -1;

    public Consumer(BoundedBuffer buffer, List<Integer> destination) {
        this.buffer = buffer;
        this.destination = destination;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = buffer.take();
                if (item == sentinel) {
                    // Send sentinel for other consumers as well: push it back and exit.
                    buffer.put(sentinel);
                    break;
                }
                synchronized (destination) {
                    destination.add(item);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}