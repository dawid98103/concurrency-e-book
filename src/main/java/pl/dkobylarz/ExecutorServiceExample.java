package pl.dkobylarz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> System.out.println("I'm inside: " + Thread.currentThread().getName());
        Future future = executorService.submit(runnable);
        executorService.shutdown();
    }
}
