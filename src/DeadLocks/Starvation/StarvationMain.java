package DeadLocks.Starvation;

import java.util.concurrent.locks.ReentrantLock;

import static BasicThreads.ThreadColor.*;

public class StarvationMain {

    // setting reentrant lock to true means it's a fair lock. (first come, first serve lock)
    // fair locks means it's only fairness in acquiring the lock, not in thread scheduling.
    // -- lock may execute a task that takes a long time
    // -- only guarantee's first come first serve for acquiring the lock
    // -- try lock method does not honor the fair lock setting (will not be first come first serve.)
    // -- fair locks used with a lot of threads will performance will be impacted (needs an extra layer of processing to determine)


    // Starvation may not always be the worst thing to happen, depends on what the program requires, could be more ideal than fair locking if its processed faster.
    
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Worker(ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ANSI_BLUE), "Priority 8");
        Thread t3 = new Thread(new Worker(ANSI_GREEN), "Priority 6");
        Thread t4 = new Thread(new Worker(ANSI_CYAN), "Priority 4");
        Thread t5 = new Thread(new Worker(ANSI_PURPLE), "Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i =0; i < 100; i++){

                // Thread compete more fairly, starvation is removed with lock vs synchronized block.
                lock.lock();
                try{
                    System.out.format(threadColor + "%s: runCount = %d\n" , Thread.currentThread().getName(), runCount++);
                    // execute critical section of code.

                } finally {
                    lock.unlock();
                }



            }
        }
    }

}
