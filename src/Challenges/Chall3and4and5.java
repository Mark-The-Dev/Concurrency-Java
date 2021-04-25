package Challenges;

import java.util.concurrent.locks.ReentrantLock;

public class Chall3and4and5 {

    public static void main(String[] args) {
        // challenge 4, use a reentrant lock for synchronization.

        // challenge 5, use a trylock() to synchronize code.

        ReentrantLock lock = new ReentrantLock();
        BankAccount account = new BankAccount(1000.00, "12345-678", lock);


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
                account.printBalance();
            }
        });


        trThread1.start();
        trThread2.start();

    }

}
