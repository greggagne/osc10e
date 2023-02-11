/**
 * Demonstration of interrupting a Java thread.
 */
 
class Worker implements Runnable
{
    /**
     * Method invoked by workers ...
     */
    public void doWork() throws InterruptedException {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie) {
            throw ie;
        }
    }

    /**
     * The thread may be interrupted either when in the
     * doWork() method, or it checks its interruption 
     * status with the isInterrupted() method.
     */
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                doWork();
                System.out.println("I am a thread\n");
            }
        }
        catch (InterruptedException ie) {
            // caught exception thrown from doWork()
        }
    }
}

public class TestCancel
{
    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Worker();
        Thread worker = new Thread(task);

        worker.start();

        Thread.sleep(3000); 

        // sets the interruption status of the worker thread
        worker.interrupt();
    }
}
