package eg.edu.alexu.csd.datastructure.queue.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.Node;
import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 * @author ahmed molahez
 *
 */

public class MyQueuelinked implements IQueue, ILinkedBased {
    /**
     * @serialField
     */
    DLinkedList queue = new DLinkedList();
    /**
     * @serialField
     */
    int count;

    @Override
    public void enqueue(final Object item) {
        Node newNode = new Node(item);
        queue.add(newNode.data);
        count++;

    }

    @Override
    public Object dequeue() {
        if (queue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else {
            Object temp;
            temp = queue.get(0);
            queue.remove(0);
            count--;
            return temp;
        }
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return count;
    }

}
