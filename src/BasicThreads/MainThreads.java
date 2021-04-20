package BasicThreads;

import static BasicThreads.ThreadColor.ANSI_GREEN;
import static BasicThreads.ThreadColor.ANSI_PURPLE;

public class MainThreads {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread!");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");

        // can only start each instance once!
        anotherThread.start();


        // Anonymous Thread!
        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN +"Hello from a anonymous thread! ");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        // this might hit before anotherThread.
        System.out.println(ANSI_PURPLE + "Hello again from the main thread.");


    }

}
