package ProducerAndConsumer;

public class Message {
    private String message;
    private boolean empty = true;

    // Read and Write methods must be synchronized.

    // Reads message
    public synchronized String read() {
        // Loop until there is a message to read.
        while (empty){

        }
        empty = true;
        return message;
    }

    // writes message
    public synchronized void write(String message){
        // Check if message is empty so it's written when not.
        while (!empty){

        }
        empty = false;
        this.message = message;
    }


}
