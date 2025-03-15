package com.ppp.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {
    private final Lock lockA = new ReentrantLock();
    private final Lock lockB = new ReentrantLock();


    private void workerOne() {
        lockA.lock();
        System.out.println("Worker one is acquired lockA");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lockB.lock();
        System.out.println("Worker one is acquired lockB");
        lockA.unlock();
        lockB.unlock();
    }

    private void workerTwo() {
        lockB.lock();
        System.out.println("Worker two is acquired lockB");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lockA.lock();
        System.out.println("Worker two is acquired lockA");
        lockA.unlock();
        lockB.unlock();
    }

    public static void main(String[] args) {
        DeadlockDemo deadlockDemo = new DeadlockDemo();
        new Thread(deadlockDemo::workerOne, "Worker One").start();
        new Thread(deadlockDemo::workerTwo, "Worker Two").start();
    }

    /*
    To prevent deadlock

    - Use timeouts
    - Global ordering of locks
    - Avoid nesting of locks
    - Use Thread safe alternatives
     */
}
