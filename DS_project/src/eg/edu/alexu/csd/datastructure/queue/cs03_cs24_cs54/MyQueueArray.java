package eg.edu.alexu.csd.datastructure.queue.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 * @author ahmed molahez
 *
 */
public class MyQueueArray implements IQueue, IArrayBased {
	/**
     * @serialField
     */
    Object[] x;
    /**
     * @serialField
     */
    int size, count, r = 0, f = 0;
    /**
     * @param max size
     */
    MyQueueArray(final int max) {
        size = max;
        x = new Object[size];

    }

    @Override
    public void enqueue(final Object item) {
        if (size == count) {
            throw new RuntimeException();

        } else {
            x[r] = item;
            r = ((r + 1) % size);
            count++;
        }
    }

    @Override
    public Object dequeue() {
        Object temp;
        if (isEmpty()) {
            throw new RuntimeException();
        } else {
            temp = x[f];
            x[f] = null;
            f = (f + 1) % size;
            count--;

        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;

    }

    @Override
    public int size() {

        return count;
    }

}
