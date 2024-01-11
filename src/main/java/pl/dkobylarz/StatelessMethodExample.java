package pl.dkobylarz;

public class StatelessMethodExample {
    public static void main(String[] args) {
        int[] fib = FibonacciUtils.getNFibonnaciNumbers(5);
        for (int i = 0; i < fib.length; i++) {
            System.out.println(fib[i]);
        }
    }

    public class FibonacciUtils {
        static int[] getNFibonnaciNumbers(int N) {
            int[] numbers = new int[N];
            int num1 = 0, num2 = 1;
            for (int i = 0; i < N; i++) {
                numbers[i] = num1;
                int num3 = num2 + num1;
                num1 = num2;
                num2 = num3;
            }
            return numbers;
        }
    }

}