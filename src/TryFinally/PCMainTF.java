package TryFinally;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static BasicThreads.ThreadColor.*;


public class PCMainTF {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        MyProducerTF producer = new MyProducerTF(buffer, ANSI_YELLOW, bufferLock);
        MyConsumerTF consumer1 = new MyConsumerTF(buffer, ANSI_PURPLE, bufferLock);
        MyConsumerTF consumer2 = new MyConsumerTF(buffer, ANSI_CYAN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();


    }

}
