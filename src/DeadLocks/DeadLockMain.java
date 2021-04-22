package DeadLocks;

public class DeadLockMain {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();


    public static void main(String[] args) {

        // This creates a basic deadlock by using locks in opposite order (both get locked.)

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
            synchronized (lock2){
                System.out.println("Thread2: has lock2");
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e){

                }
                System.out.println("Thread 2: waiting for lock 1");

                synchronized (lock1){
                    System.out.println("Thread 2: has lock2 and lock1");
                }
                System.out.println("Thread2: has released lock1");
            }
            System.out.println("Thread 2: has released lock2. Exiting...");

        }

    }

}
