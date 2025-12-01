# Producer-Consumer Java Assignment

## Overview

This project demonstrates the **classic Producer–Consumer problem** using **Java multithreading, thread synchronization, and wait/notify mechanism**.  
A producer thread reads items from a source container and places them into a shared bounded buffer.  
A consumer thread retrieves items from the buffer and stores them into a destination container.

The implementation uses:
- `synchronized` blocks
- `wait()` and `notifyAll()`
- Custom **bounded buffer** (not `BlockingQueue`, to demonstrate manual thread coordination)

---


## Prerequisites
- Java 11+ (JDK installed)
- Maven 3.x
- JUnit 5

## Build & Run
1. Build and run tests:
```bash
mvn clean test
1. Run the demo application (prints sample output to console):
mvn compile exec:java -Dexec.mainClass="com.example.producerconsumer.App"
(Or build a package and run the main class with java -cp target/classes
com.example.producerconsumer.App)
What is included
• BoundedBuffer — custom bounded buffer using synchronized , wait() and notifyAll()
(no BlockingQueue used here to explicitly demonstrate wait/notify)
9
• Producer and Consumer — implement Runnable and operate on the buffer
• Unit tests in BoundedBufferTest.java using JUnit 5
Sample Output
Source: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Destination: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Items transferred: 10
Demo finished.
Note: order may vary when multiple producers/consumers are used. Tests compare 
sorted multisets for correctness.
```