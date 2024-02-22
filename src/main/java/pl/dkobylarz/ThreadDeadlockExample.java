package pl.dkobylarz;

class ThreadDeadlockExample {
    static final String R1 = "Hello World!";
    static final String R2 = "Hello J-labs!";
    public static void main(String[] args) {
        Thread T1 = new Thread(() -> {
            synchronized (R1){
                System.out.println("Thread T1 locked -> Resource R1");
                synchronized (R2){
                    System.out.println("Thread T1 locked -> Resource R2");
                }
            }
        });
        Thread T2 = new Thread(() -> {
            synchronized (R2){
                System.out.println("Thread T2 locked -> Resource R2");
                synchronized (R1){
                    System.out.println("Thread T1 locked -> Resource R1");
                }
            }
        });
        T1.start();
        T2.start();
    }
}
