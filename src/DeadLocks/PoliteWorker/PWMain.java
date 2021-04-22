package DeadLocks.PoliteWorker;

public class PWMain {

    public static void main(String[] args) {

        // set up workers.
        final Worker worker1 = new Worker("Worker1", true);
        final Worker worker2 = new Worker("Worker2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        // non lambda threads

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                worker1.work(sharedResource, worker2);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                worker2.work(sharedResource, worker1);
//            }
//        }).start();


        // replaced with lambda's
        new Thread(() -> worker1.work(sharedResource, worker2)).start();

        new Thread(() -> worker2.work(sharedResource, worker1)).start();

    }

}
