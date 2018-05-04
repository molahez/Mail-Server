package eg.edu.alexu.csd.datastructure.stack.cs03;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.Node;
import eg.edu.alexu.csd.datastructure.stack.IStack;

/**
 * @author ahmed molahez
 *
 */
public class MyStack implements IStack {
    /**
     * @serialField
     */
    DLinkedList stack = new DLinkedList();
    /**
     * @serialField
     */
    public int count = 0;
    /**
     * @serialField
     */
    public MyStack() { // constructs an
    }

    @Override
    public Object pop() {
        Object temp;
        temp = stack.get(count - 1);
        if (count == 1) {
            stack.remove(0);
        } else {
            stack.remove(count - 1);
        }
        count--;
        return temp;
    }

    @Override
    public Object peek() {
        if (stack.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stack.get(count - 1);
    }

    @Override
    public void push(final Object element) {
        Node newNode = new Node(element);
        stack.add(newNode.data);
        count++;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return count;
    }

}
