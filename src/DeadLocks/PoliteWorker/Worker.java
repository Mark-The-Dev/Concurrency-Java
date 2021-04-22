package DeadLocks.PoliteWorker;

public class Worker {
    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }


    // this will create a live lock where the threads are constantly live on the tasks.
    public synchronized void work(SharedResource sharedResource, Worker otherWorker){

        while (active){
            // checks if it's the owner, if not wait 10ms, try again.
            if (sharedResource.getOwner() != this){
                try {
                    wait(10);
                } catch (InterruptedException e){

                }
                continue;
            }
            // if it's true, it checks to see if other thread is active.
            // if other is active, will give share resource to other thread, if not will use the shared resource
            if (otherWorker.isActive()){
                System.out.println(getName() + " : give the resource to the worker " + otherWorker.getName());
                sharedResource.setOwner(otherWorker);
                continue;
            }

            // will only get here if it owns the shared resource and the other worker isn't active!
            System.out.println(getName() + ": working on the common resource");
            active = false;
            sharedResource.setOwner(otherWorker);

        }

    }
}
