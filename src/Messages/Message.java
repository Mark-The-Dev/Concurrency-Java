package Messages;

public class Message {
    private String message;
    private boolean empty = true;

    // Read and Write methods must be synchronized.

    // Reads message
    public synchronized String read() {
        // Loop until there is a message to read.
        while (empty){

            // call wait within a loop to ensure the condition is correct.

            try{
                wait();
            } catch (InterruptedException e){

            }

        }
        empty = true;
        // notify all is used unless there are a significant number of threads all waiting to perform a similar task.
        notifyAll();
        return message;
    }

    // writes message
    public synchronized void write(String message){
        // Check if message is empty so it's written when not.
        while (!empty){

            try{
                wait();
            } catch (InterruptedException e){

            }

        }
        empty = false;
        this.message = message;
        notifyAll();
    }


}
