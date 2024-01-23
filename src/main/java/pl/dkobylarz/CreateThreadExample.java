package pl.dkobylarz;

public class CreateThreadExample {
    public static void main(String[] args) {
        System.out.println("I'm Inside: " + Thread.currentThread());
        Thread thread = new ThreadExample();
        thread.start();
    }

    static class ThreadExample extends Thread{
        @Override
        public void run() {
            System.out.println("I'm Inside: " + Thread.currentThread().getName());
        }
    }
}
