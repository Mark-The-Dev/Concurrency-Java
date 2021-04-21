package pcWithReentrantLock;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static ProducerConsumer.PCMain.EOF;

public class MyConsumerRL implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumerRL(List<String> buffer, String color, ReentrantLock bufferlock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferlock;
    }


    public void run() {
       // basic example, normally would not be this simple
        while (true){

            // uses buffer lock to create synchronization.
            // Using locks this way creates a need to end locks for every outcome.

            bufferLock.lock();
            if(buffer.isEmpty()){
                bufferLock.unlock();
                continue;
            }
            if(buffer.get(0).equals(EOF)){
                System.out.println(color + "Exiting");
                bufferLock.unlock();
                break;
            } else {
                System.out.println(color + "Removed " + buffer.remove(0));
            }
            bufferLock.unlock();

        }
    }
}
