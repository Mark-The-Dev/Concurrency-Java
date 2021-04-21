package BasicThreadPool;

import java.util.concurrent.ArrayBlockingQueue;

import static ProducerConsumer.PCMain.EOF;

public class MyConsumerTP implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;


    public MyConsumerTP(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;

    }


    public void run() {

        while (true) {
            // Even though ArrayBlockingQueue is thread safe synchronized is needed to prevent null peek.
            synchronized (buffer){
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }

                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
