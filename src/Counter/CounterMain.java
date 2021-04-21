package Counter;

import BasicThreads.ThreadColor;

public class CounterMain {
    public static void main(String[] args) {

        // Object instances are stored in the heap (Shared among threads unlike the stack.)
        CountDown countDown = new CountDown();

        CountDownThread t1 = new CountDownThread(countDown);
        t1.setName("Thread 1");

        CountDownThread t2 = new CountDownThread(countDown);
        t2.setName("Thread 2");

        // Thread interference occurs because both threads share the same read object.
        // More common term is race condition if two or more threads are writing or updating the same resource (it's okay if reading).
        t1.start();
        t2.start();

    }

}

class CountDown {
    // adding i as a member variable makes the looped i's not duplicated.
    // When it was defined in the for loop it was allocated in the stack so each object had it's own range of i.
    // When it is defined as a member variable then it is allocated in the heap which is shared among threads.

    private int i;

    public void doCountDown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }


        // thread could be suspended before decrementing i,
        // thread could be suspended before checking the conditional,
        // thread could be suspended before checking i,
        // thread could be suspended could be suspended after all the conditions are checked but before the print line.

        for (i = 10; i> 0; i--){
            // Thread could be suspended within print line!
            System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
        }
    }
}

class CountDownThread extends Thread {
    private CountDown threadCountdown;

    public CountDownThread(CountDown countDown){
        threadCountdown = countDown;
    }

    public void run(){
        threadCountdown.doCountDown();
    }

}
