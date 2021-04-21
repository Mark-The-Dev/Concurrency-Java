package BasicThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static BasicThreads.ThreadColor.*;


public class PCMainTP {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducerTP producer = new MyProducerTP(buffer, ANSI_YELLOW, bufferLock);
        MyConsumerTP consumer1 = new MyConsumerTP(buffer, ANSI_PURPLE, bufferLock);
        MyConsumerTP consumer2 = new MyConsumerTP(buffer, ANSI_CYAN, bufferLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ANSI_WHITE + "I'm being printed for Callable class.");
                return "This is the callable result.";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e){
            System.out.println("Something went wrong");
        } catch (InterruptedException e){
            System.out.println("Thread running the task was interrupted");
        }

        // waits for executing tasks to finish. shutdownNow can be used to ignore tasks.
        executorService.shutdown();


    }

}
