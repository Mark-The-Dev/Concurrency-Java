package TryFinally;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static ProducerConsumer.PCMain.EOF;

public class MyConsumerTF implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumerTF(List<String> buffer, String color, ReentrantLock bufferlock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferlock;
    }


    public void run() {

        // counter to see how often tryLock is false.
        int counter = 0;

        while (true){
            // uses try lock -- not needed here but good for learning!
            // could also use lock + try finally, both ways allow the entire sequence to be synchronized with locks!
            if (bufferLock.tryLock()){
                try {
                    if(buffer.isEmpty()){
                        continue;
                    }
                    // adds only if buffer is not empty, then resets.
                    System.out.println(color +"The counter = "+ counter);
                    counter = 0;

                    if(buffer.get(0).equals(EOF)){
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            } else {
                counter++;
            }

        }
    }
}
