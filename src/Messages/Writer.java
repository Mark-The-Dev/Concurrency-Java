package Messages;

import java.util.Random;

// Basic Writer class.

public class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run(){
        // haha couldn't help myself here
        String messages[]= {
                "Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again."
        };

        Random random = new Random();
        for ( int i =0; i < messages.length; i++){
            message.write(messages[i]);
            try {
                // sleep to simulate how threading works!
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e){
                e.printStackTrace();

            }

        }

        message.write("Finished");
    }

}
