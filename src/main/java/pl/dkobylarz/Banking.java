package pl.dkobylarz;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Banking {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        for (int i = 0; i < Bank.N; ++i) {
            bank.deposit(i, 100);
        }
        Thread [] threads = new Thread[10];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Accountant(bank);
        }
        for (Thread t : threads) { t.start(); }
        for (Thread t : threads) { t.join(); }
        int total = 0;
        for (int i = 0; i < Bank.N; ++i) {
            int b = bank.getBalance(i);
            total += b;
            System.out.printf("Account [%d] balance: %d\n", i, b);
        }
        System.out.printf("Total balance is %d\tvalid value is %d\n",
                total, Bank.N * 100);
    }
}

class Bank {
    public static final int N = 10;
    private int[] balances = new int[N];
    private ReentrantLock[] locks = new ReentrantLock[N];

    public Bank() {
        for (int i = 0; i < locks.length; ++i) {
            locks[i] = new ReentrantLock();
        }
    }

    public void deposit(int accountId, int amount) {
        balances[accountId] += amount;
    }

    public int getBalance(int accountId) {
        return balances[accountId];
    }

    public boolean transfer(int fromAccount, int toAccount, int amount) {
        while (true) {
            if (locks[fromAccount].tryLock()) {
                if (locks[toAccount].tryLock()) {
                    try {
                        if (balances[fromAccount] >= amount) {
                            balances[fromAccount] -= amount;
                            balances[toAccount] += amount;
                        }
                    } finally {
                        locks[fromAccount].unlock();
                        locks[toAccount].unlock();
                    }
                    break;
                }
                locks[fromAccount].unlock();
            }
        }

        return true;
    }


    public void equalize(int accountA, int accountB) {
        while (true) {
            if (locks[accountA].tryLock()) {
                if (locks[accountB].tryLock()) {
                    try {
                        int total = balances[accountA] + balances[accountB];
                        balances[accountA] = total / 2 + total % 2;
                        balances[accountB] = total / 2;
                    } finally {
                        locks[accountB].unlock();
                        locks[accountA].unlock();
                    }
                    break;
                }
                locks[accountA].unlock();
            }
        }
    }
}

class Accountant extends Thread {
    Bank bank;

    public Accountant(Bank bank) { this.bank = bank; }
    @Override
    public void run() {
        Random rng = ThreadLocalRandom.current();
        for (int i = 0; i < 1000; ++i) {
            int fromAccount = rng.nextInt(Bank.N-1);
            int toAccount = rng.nextInt(Bank.N-1);
            while (toAccount == fromAccount) {
                toAccount = rng.nextInt(Bank.N-1);
            }
            if (rng.nextBoolean()) {
                bank.transfer(fromAccount, toAccount, rng.nextInt(100));
            } else {
                bank.equalize(fromAccount, toAccount);
            }
        }
    }
}