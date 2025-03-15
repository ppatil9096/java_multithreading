package com.ppp.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * There are other Atomic classes available in java.util.concurrent.atomic.* packages, explore
 */
public class AtomicVariablesDemo {
    //    private static int count = 0;
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                count++;
                counter.incrementAndGet();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                count++;
                counter.incrementAndGet();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        System.out.println("Count value is: " + count);// results different values for each execution,
        // no surprises if we get 20000 value frequently
        System.out.println("Counter value is: " + counter);
    }
}
