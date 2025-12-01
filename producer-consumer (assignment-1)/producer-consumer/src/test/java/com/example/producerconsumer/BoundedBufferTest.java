package com.example.producerconsumer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoundedBufferTest {

    @Test
    public void testSingleProducerSingleConsumer() throws InterruptedException {
        List<Integer> source = Arrays.asList(1,2,3,4,5);
        List<Integer> destination = Collections.synchronizedList(new ArrayList<>());
        BoundedBuffer buffer = new BoundedBuffer(2);

        Producer producer = new Producer(source, buffer, 1);
        Consumer consumer = new Consumer(buffer, destination);

        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);

        p.start();
        c.start();

        p.join();
        c.join();

        // Since consumer may receive items in non-deterministic order in multi-threaded environment,
        // compare sorted lists
        List<Integer> sortedDest = new ArrayList<>(destination);
        List<Integer> sortedSource = new ArrayList<>(source);
        Collections.sort(sortedDest);
        Collections.sort(sortedSource);

        assertEquals(sortedSource, sortedDest);
    }

    @Test
    public void testMultipleProducersMultipleConsumers() throws InterruptedException {
        List<Integer> part1 = Arrays.asList(1,2,3);
        List<Integer> part2 = Arrays.asList(4,5,6);
        List<Integer> destination = Collections.synchronizedList(new ArrayList<>());
        BoundedBuffer buffer = new BoundedBuffer(3);

        // p1 sends sentinel for both consumers (2 total)
        Producer p1 = new Producer(part1, buffer, 2);
        Producer p2 = new Producer(part2, buffer, 0);

        Consumer c1 = new Consumer(buffer, destination);
        Consumer c2 = new Consumer(buffer, destination);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread cth1 = new Thread(c1);
        Thread cth2 = new Thread(c2);

        t1.start();
        t2.start();
        cth1.start();
        cth2.start();

        t1.join();
        t2.join();
        cth1.join();
        cth2.join();

        // Destination should eventually contain 6 produced items
        assertEquals(6, destination.size());

        List<Integer> expected = Arrays.asList(1,2,3,4,5,6);
        List<Integer> sortedDest = new ArrayList<>(destination);
        Collections.sort(sortedDest);

        assertEquals(expected, sortedDest);
    }
}
