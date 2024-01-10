package pl.dkobylarz;

public class Main {
    public static void main(String[] args) {
        System.out.println("I'm Inside: " + Thread.currentThread());
        Thread thread = new ThreadExample();
        thread.start();
    }
}

class ThreadExample extends Thread{
    @Override
    public void run() {
        System.out.println("I'm Inside: " + Thread.currentThread().threadId());
    }
}
