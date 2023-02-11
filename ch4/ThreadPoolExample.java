/**
 * Creating a Java thread pool.
 *
 * Figure 4.15
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018
 */

import java.util.concurrent.*;

class Task implements Runnable
{
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class ThreadPoolExample
{
    public static void main(String[] args) {
        int numTasks = Integer.parseInt(args[0].trim());
        
        ExecutorService pool = Executors.newCachedThreadPool();
        
        for (int i = 0; i < numTasks; i++)
            pool.execute(new Task());

            // The following invovles using a lambda function
            // pool.execute( () -> {System.out.println(Thread.currentThread().getName());});
            
        pool.shutdown();
    }
}
