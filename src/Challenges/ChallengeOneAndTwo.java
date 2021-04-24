package Challenges;

public class ChallengeOneAndTwo {

    public static void main(String[] args) {
        // Challenge 1, run two threads off the same account and withdraw and deposit on both.

        // challenge 2, make the Bank Account safe with synchronize.

        BankAccount account = new BankAccount(1000.00, "12345-678");

        // can run as two anonymous threads
//        Thread trThread1 = new Thread(){
//            public void run(){
//                account.deposit(300.00);
//                account.withdraw(50.00);
//            }
//        };
//
//        Thread trThread2 = new Thread(){
//            public void run(){
//                account.deposit(203.75);
//                account.withdraw(100.00);
//            }
        // };

        // or as two runnables

        Thread trThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300.00);
                account.withdraw(50.00);
            }
        });

        Thread trThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                account.withdraw(100.00);
                System.out.println(account.getBalance());
            }
        });


        trThread1.start();
        trThread2.start();

    }
}
