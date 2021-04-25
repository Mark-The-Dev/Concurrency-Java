package Challenges;

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

//    public synchronized void deposit(double amount){
//        balance += amount;
//    }
//
//    public synchronized void withdraw(double amount){
//        balance -= amount;
//    }

    public void deposit(double amount){
        bufferLock.lock();
        try {

            balance += amount;
        } finally {
            bufferLock.unlock();
        }



    }

    public void withdraw(double amount){
        bufferLock.lock();
        try {

            balance -= amount;
        } finally {
            bufferLock.unlock();
        }

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
