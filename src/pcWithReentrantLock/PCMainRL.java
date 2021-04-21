package pcWithReentrantLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static BasicThreads.ThreadColor.*;


public class PCMainRL {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        MyProducerRL producer = new MyProducerRL(buffer, ANSI_YELLOW, bufferLock);
        MyConsumerRL consumer1 = new MyConsumerRL(buffer, ANSI_PURPLE, bufferLock);
        MyConsumerRL consumer2 = new MyConsumerRL(buffer, ANSI_CYAN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();


    }

}
