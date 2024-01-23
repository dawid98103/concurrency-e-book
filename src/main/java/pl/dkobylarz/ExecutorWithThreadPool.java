package pl.dkobylarz;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorWithThreadPool {
    public static void main(String[] args) {
        System.out.println("I'm inside: " + Thread.currentThread().getName());

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(1, 4).forEach(value -> executorService.submit(() -> {
            System.out.println("Started executing TASK-" + value + " in: " + Thread.currentThread().getName());
            try {
                Thread.sleep(Duration.ofSeconds(value));
                System.out.println("Finished executing TASK-" + value + " in: " + Thread.currentThread().getName());
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        }));
        executorService.shutdown();
    }
}
