package pl.dkobylarz;

import java.util.Arrays;
import java.util.List;

public class ThreadLocalVariableExample {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();
    }

    public static class ThreadA extends Thread {
        private final List<Integer> numbers = Arrays.asList(1, 2, 3);

        @Override
        public void run() {
            numbers.forEach(System.out::println);
        }
    }

    public static class ThreadB extends Thread {
        private final List<String> letters = Arrays.asList("A", "B", "C");

        @Override
        public void run() {
            letters.forEach(System.out::println);
        }
    }
}
