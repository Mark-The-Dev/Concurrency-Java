package Counter;

import ThreadColor;

public class Counter2 {

    public static void main(String[] args) {

        // added distinct objects to prevent interference, not practical in real applications.
        // CountDown countDown1 = new CountDown();
        // CountDown countDown2 = new CountDown();

        CountDown countDown = new CountDown();

        CountDownThread t1 = new CountDownThread(countDown);
        t1.setName("Thread 1");

        CountDownThread t2 = new CountDownThread(countDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }



static class CountDown {

    private int i;

    // could add synchronized st the method level to make sure one thread runs a method at a time.
    // IE: public synchronized void doCountDown(){};
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



        // causes interference when synchronized off of a local variable color
        // can synchronize off this to allow each object to be locked to a specific thread at a time.
        // Can prevent race conditions by synchronizing critical pieces of code.
        synchronized (this){
            for (i = 10; i> 0; i--){
                // Thread could be suspended within print line!
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }


    }
}

static class CountDownThread extends Thread {
    private CountDown threadCountdown;

    public CountDownThread(CountDown countDown){
        threadCountdown = countDown;
    }

    public void run(){
        threadCountdown.doCountDown();
    }

}

}
