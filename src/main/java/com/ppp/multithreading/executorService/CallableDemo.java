package com.ppp.multithreading.executorService;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        try (ExecutorService service = Executors.newFixedThreadPool(4)) {
            Future<Boolean> value = service.submit(new TaskB());
            System.out.println(value.get());
//            value.get(1000, TimeUnit.MILLISECONDS); //throws TimeoutException if we didn't get results within specified time
//            value.cancel(true);
//            value.isCancelled();
//            value.isDone();

        }
    }
}

class TaskB implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
