/**
 * A simple program demonstrating file locking.
 * This program acquires an exclusive lock on the
 * first half of the file and a shared lock on the second half.
 *
 * Usage
 *	java LockingExample <input file>
 *
 * Figure 13.2
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts - Tenth Edition
 * Copyright John Wiley & Sons - 2018.
 */

import java.io.*;
import java.nio.channels.*;

public class LockingExample {
	public static final boolean EXCLUSIVE = false;
	public static final boolean SHARED = true;

   public static void main(String args[]) throws IOException {
	if (args.length != 1) {
		System.err.println("Usage: java LockingExample <input file>");
		System.exit(0);
	}

	FileLock sharedLock = null;
	FileLock exclusiveLock = null;

	try {
     		RandomAccessFile raf = new RandomAccessFile(args[0], "rw"); 

		// get the channel for the file
     		FileChannel channel = raf.getChannel();

		System.out.println("trying to acquire lock ...");
		// this locks the first half of the file - exclusive
		exclusiveLock = channel.lock(0, raf.length()/2, EXCLUSIVE);
		System.out.println("lock acquired ...");

		/**
		 * Now modify the data  . . .
		 */

		try {
			// sleep for 10 seconds
			Thread.sleep(10000);
		}
		catch (InterruptedException ie) { }

		// release the lock
		exclusiveLock.release();
		System.out.println("lock released ...");

		// this locks the second half of the file - shared 
		sharedLock = channel.lock(raf.length()/2 + 1, raf.length(), SHARED);
		
		/**
		 * Now read the data  . . .
		 */

		// release the lock
		exclusiveLock.release();
	} catch (java.io.IOException ioe) {
		System.err.println(ioe);
	}
      	finally {
		if (exclusiveLock != null)
       			exclusiveLock.release();
		if (sharedLock != null)
       			sharedLock.release();
     	}
   }
}

