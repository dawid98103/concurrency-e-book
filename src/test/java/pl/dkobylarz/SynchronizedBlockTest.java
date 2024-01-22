package pl.dkobylarz;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedBlockTest {

    @Test
    public void givenMultiThread_whenBlockSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        IterationService iterationService = new IterationService();

        IntStream.range(0, 1000).forEach(count -> service.submit(iterationService::iterate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, iterationService.getCurrentIteration());
    }
}