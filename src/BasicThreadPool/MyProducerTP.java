package BasicThreadPool;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static BasicThreads.ThreadColor.ANSI_RESET;

public class MyProducerTP implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducerTP(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run(){
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num: nums){
            try {
                System.out.println(color +"Adding..." + num);

                bufferLock.lock();
                // uses try finally to add to buffer so it always unlocks.
                try {
                    buffer.add(num);
                } finally {
                    bufferLock.unlock();
                }


                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e){
                System.out.println("Producer was interrupted.");
            }
        }

        System.out.println(ANSI_RESET +"Adding EOF and exiting...");

            bufferLock.lock();
            try {
                buffer.add("EOF");
            } finally {
                bufferLock.unlock();
            }

    }
}
