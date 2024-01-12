package pl.dkobylarz;

public class NoAtomicVariableExample {
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

        private int counter = 0;

        public void incrementCounter() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }
    }
}

