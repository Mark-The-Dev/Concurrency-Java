package DeadLocks;

public class DLMainFix {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();


    public static void main(String[] args) {

        // This fixes a deadlock by using the locks in the same order!
        new Thread1().start();
        new Thread2().start();


    }

    private static class Thread1 extends Thread {
        public void run(){
            synchronized (lock1){
                System.out.println("Thread1: has lock1");
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e){

                }
                System.out.println("Thread 1: waiting for lock 2");

                synchronized (lock2){
                    System.out.println("Thread 1: has lock1 and lock2");
                }
                System.out.println("Thread1: has released lock2");
            }
            System.out.println("Thread 1: has released lock1. Exiting...");
        }


    }

    private static class Thread2 extends Thread{
        public void run(){
            synchronized (lock1){
                System.out.println("Thread2: has lock1");
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e){

                }
                System.out.println("Thread 2: waiting for lock 2");

                synchronized (lock2){
                    System.out.println("Thread 2: has lock1 and lock2");
                }
                System.out.println("Thread2: has released lock2");
            }
            System.out.println("Thread 2: has released lock1. Exiting...");

        }

    }
}
