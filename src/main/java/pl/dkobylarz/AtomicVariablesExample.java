package pl.dkobylarz;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementCounter();
            }
        }); //ThreadA
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementCounter();
            }
        }); //ThreadB
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(counter.getCounter());
    }

    public static class Counter {

        private AtomicInteger counter = new AtomicInteger(0);

        public void incrementCounter() {
            counter.incrementAndGet();
        }

        public int getCounter() {
            return counter.get();
        }
    }
}

