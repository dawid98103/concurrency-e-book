package pl.dkobylarz;

public class ThreadLocalEx {
    public static void main(String[] args) {
        ThreadLocalExample threadLocalExampleA = new ThreadLocalExample(1);
        ThreadLocalExample threadLocalExampleB = new ThreadLocalExample(2);
        new Thread(threadLocalExampleA).start();
        new Thread(threadLocalExampleB).start();
    }

    public static class ThreadLocalExample implements Runnable {
        private static final ThreadLocal<Integer> userContext = new ThreadLocal<>();
        private final int userId;

        public ThreadLocalExample(int userId) {
            this.userId = userId;
        }

        @Override
        public void run() {
            userContext.set(userId);
            System.out.println("I'm storing user with ID: " + userContext.get());
        }
    }
}