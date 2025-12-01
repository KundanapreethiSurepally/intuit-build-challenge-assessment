package com.example.producerconsumer;

/**
 * A bounded buffer implemented with a circular array and wait/notify.
 * This demonstrates explicit lock/wait/notify synchronization (not using BlockingQueue).
 */
public class BoundedBuffer {
    private final int[] buffer;
    private int head = 0; // next take position
    private int tail = 0; // next put position
    private int count = 0; // number of elements

    public BoundedBuffer(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be > 0");
        this.buffer = new int[capacity];
    }

    /**
     * Put an item into the buffer. If the buffer is full, wait until space is available.
     */
    public synchronized void put(int item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        // Notify waiting takers
        notifyAll();
    }

    /**
     * Take an item from the buffer. If the buffer is empty, wait until an item is
     * available.
     */
    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int item = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        // Notify waiting putters
        notifyAll();
        return item;
    }

    public synchronized int getCount() {
        return count;
    }

    public int capacity() {
        return buffer.length;
    }
}