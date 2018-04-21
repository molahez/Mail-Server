package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.mailServer.IPriorityQueue;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;

/**
 * @author ahmed molahez
 *
 */
public class PriorityQ implements IPriorityQueue {
    /**
     * @serialField
     */
    DLinkedList pqueue = new DLinkedList();

    /**
     * @serialField
     */
    int j;

    @Override
    public void insert(final Object item, final int key) {
        if (key < 1) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(item, key);
        Node temp = new Node();
        if (pqueue.size() == 0) {
            pqueue.add(0, newNode);

        } else {
            for (j = (pqueue.size() - 1); j >= 0; j--) {
                if (key >= ((Node) pqueue.get(j)).key) {
                    break;
                } else {
                    temp = new Node(((Node) pqueue.get(j)).data,
                            ((Node) pqueue.get(j)).key);
                    pqueue.add(j + 1, temp);
                    pqueue.remove(j);

                }
            }
            pqueue.add(j + 1, newNode);

        }

    }

    @Override
    public Object removeMin() {
        Node temp = new Node();
        if (pqueue.size() == 0) {
            throw new IndexOutOfBoundsException();
        } else {
            temp.data = ((Node) pqueue.get(0)).data;
            pqueue.remove(0);
            return temp.data;
        }
    }

    @Override
    public Object min() {
        Node temp = new Node();
        if (pqueue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        } else {
            temp.data = ((Node) pqueue.get(0)).data;
            return temp.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return pqueue.isEmpty();
    }

    @Override
    public int size() {

        return pqueue.size();
    }

}
