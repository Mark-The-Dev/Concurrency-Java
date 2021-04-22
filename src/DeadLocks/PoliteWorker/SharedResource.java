package DeadLocks.PoliteWorker;

public class SharedResource {
    private Worker owner;

    public SharedResource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    // synchronized because it's changing data, don't want any interference!
    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }
}
