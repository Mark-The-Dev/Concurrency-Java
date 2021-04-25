package Challenges.SixandUp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private ReentrantLock bufferLock;

    public BankAccount(double balance, String accountNumber, ReentrantLock bufferLock) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.bufferLock = bufferLock;
    }

// challenge six, add a tread safe boolean value to deposit and withdraw.

    // setting a boolean is thread safe because it's stored on the stack.

    public void deposit(double amount){

        boolean status = false;

        try {
            if (bufferLock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance += amount;
                    status = true;
                } finally {
                    bufferLock.unlock();
                }
            } else {
                System.out.println("Could not get the lock!");
            }
        } catch (InterruptedException e){
            // catch if needed
        }

        System.out.println("Transaction status = " + status);


    }

    public void withdraw(double amount){

        boolean status = false;

        try {
            if (bufferLock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    bufferLock.unlock();
                }
            } else {
                System.out.println("Could not get the lock!");
            }
        } catch (InterruptedException e){
            // catch if needed
        }

        System.out.println("Transaction status = " + status);
    }

    public double getBalance() {

            return balance;

    }

    public void printBalance(){
        System.out.println("The Account balance is: "+ getBalance());
    }

    public String getAccountNumber() {

            return accountNumber;


    }

    public void printAccountNumber(){

            System.out.println("Account number = " + this.accountNumber);


    }
}
