package Messages;

public class MessagesMain {
    public static void main(String[] args) {

        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();


    }
}
