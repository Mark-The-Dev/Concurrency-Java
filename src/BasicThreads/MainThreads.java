package BasicThreads;

import static ThreadColor.*;

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

        Thread myRunnableThread = new Thread(new MyRunnable(){

            @Override
            public void run(){
                System.out.println(ANSI_RED + "Hello form anonymous class's implementation.");

                try {
                    anotherThread.join();
                    System.out.println(ANSI_RED + "Another thread terminated, or timed out so I'm running again.");

                } catch (InterruptedException e){
                    System.out.println(ANSI_RED + "I couldn't wait after all, I was interrupted!");
                }

            }

        });


        myRunnableThread.start();

        // can interrupt each thread.
        //anotherThread.interrupt();



        // this might hit before anotherThread.
        System.out.println(ANSI_PURPLE + "Hello again from the main thread.");


    }

}
