package com.ppp.multithreading.executorService;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ExecutorService service = Executors.newFixedThreadPool(4)) {
            Future<Boolean> value = service.submit(new TaskB());
            System.out.println(value.get());
        }
    }
}

class TaskB implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
