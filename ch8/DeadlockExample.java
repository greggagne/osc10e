/**
 * This program gives an example of how deadlock can occur between threads.
 * This program differs from the book as each thread will sleep a random
 * amount of time between calls to the synchronized statement. It may require
 * several runs of the program to deadlock the threads.
 *
 * Figure 8.1
 *
 * WHAT IS INTERESTING TO DO IS GET A THREAD DUMP TO SEE
 * HOW THE JVM DETECTS DEADLOCK FROM A STACK DUMP
 *
 * TO GET A STACK DUMP:
 *
 *  <CTRL> \ (MAC/UNIX)
 *
 * <CTRL> BREAK (WINDOWS)
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018.
 */


import java.util.concurrent.locks.*;

class A implements Runnable
{
	private Lock first, second;
	
	public A(Lock first, Lock second) {
		this.first = first;
		this.second = second;
	}
	
	public void run() {
		try {
			first.lock();
			System.out.println("Thread A got first lock.");
			// do something
			
			try { 
				Thread.sleep( ((int)(3*Math.random()))*1000);
			}
			catch (InterruptedException e) {}
			
			
			second.lock();
			System.out.println("Thread A got second lock.");
			// do something
            
		}
		finally {
			first.unlock();
			second.unlock();
		}
	}
}

class B implements Runnable
{
	private Lock first, second;
	
	
	public B(Lock first, Lock second) {
		this.first = first;
		this.second = second;
	}
	
	public void run() {
		try {
			second.lock();
			System.out.println("Thread B got second lock.");
			// do something
			
			try {
                Thread.sleep( ((int)(3*Math.random()))*1000);
			}
			catch (InterruptedException e) {}
			
			first.lock();
			System.out.println("Thread B got first lock.");
			// do something
			
		}
		finally {
			second.unlock();
			first.unlock();
		}
	}
}


public class DeadlockExample 
{  
	public static void main(String arg[]) {         
		Lock lockX = new ReentrantLock();
		Lock lockY = new ReentrantLock();
		
		Thread threadA = new Thread(new A(lockX,lockY));
		Thread threadB = new Thread(new B(lockX,lockY));
		
		threadA.start();
		threadB.start();
	}
}

