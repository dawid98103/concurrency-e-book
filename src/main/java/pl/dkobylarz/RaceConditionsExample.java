package pl.dkobylarz;

class RaceConditionsExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500);

        Thread T1 = new Thread(new WithdrawalTask(account, 400));
        Thread T2 = new Thread(new WithdrawalTask(account, 400));

        T1.start();
        T2.start();
    }

    static class BankAccount {
        private double balance;

        public BankAccount(double balance) {
            this.balance = balance;
        }

        public void withdraw(double amount) {
            if (balance >= amount) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                balance -= amount;
            }
        }

        public double getBalance() {
            return balance;
        }
    }

    static class WithdrawalTask implements Runnable {
        private final BankAccount account;
        private final double amount;

        public WithdrawalTask(BankAccount account, double amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Withdrawal of " + amount + " successful. Remaining balance: " + account.getBalance());
            } else {
                System.out.println("Not enough balance for withdrawal of " + amount);
            }
        }
    }
}

