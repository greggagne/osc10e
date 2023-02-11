/**
 * Bounded buffer using Java synchronization
 *
 * Figures 7.9 and 7.11
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018.
 */

@SuppressWarnings("unchecked")

public class BoundedBuffer<E>
{
    private static final int BUFFER_SIZE = 5;

    private int count, in, out;
    private E[] buffer;

    public BoundedBuffer() {
        count = 0;
        in = 0;
        out = 0;
        buffer = (E[]) new Object[BUFFER_SIZE];
    }

    public synchronized void insert(E item) {
        while (count == BUFFER_SIZE) {
            try {
                wait();
            }
            catch (InterruptedException ie) { }
        }

        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;

        notify();
    }

    public synchronized E remove() {
        E item;

        while (count == 0) {
            try {
                wait();
            }
            catch (InterruptedException ie) { }
        }

        item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;

        notify();

        return item;
    }
}
