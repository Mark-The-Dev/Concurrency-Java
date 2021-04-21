package pcWithReentrantLock;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static BasicThreads.ThreadColor.ANSI_RESET;

public class MyProducerRL implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducerRL(List<String> buffer, String color, ReentrantLock bufferLock) {
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

                // Uses Reentrant Lock to lock the task.

                bufferLock.lock();
                buffer.add(num);
                bufferLock.unlock();


                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e){
                System.out.println("Producer was interrupted.");
            }
        }

        System.out.println(ANSI_RESET +"Adding EOF and exiting...");

            bufferLock.lock();
            buffer.add("EOF");
            bufferLock.unlock();
    }
}
