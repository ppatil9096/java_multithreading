package com.ppp.multithreading.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new TaskA(), 1000, 1000, TimeUnit.MILLISECONDS);

        try {
            if (!service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            throw new RuntimeException(e);
        }

    }
}

class TaskA implements Runnable {

    @Override
    public void run() {
        System.out.println("Running scheduled executor...");
    }
}
