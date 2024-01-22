package pl.dkobylarz;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class IterationServiceTest {

    @Test
    void givenMultiThread_whenStaticSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        IntStream.range(0, 1000).forEach(count -> service.submit(IterationService::iterate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, IterationService.getCurrentIteration());
    }
}