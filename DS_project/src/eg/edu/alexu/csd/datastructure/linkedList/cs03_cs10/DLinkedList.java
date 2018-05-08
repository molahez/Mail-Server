package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
/**
 * @author ahmed molahez
 *
 */
public class DLinkedList implements ILinkedList {
    /**
     * @serialField
     */
    public Node first;
    /**
     * @serialField
     */
    public Node last;
    /**
     * @serialField
     */
    public int counttt = 0;
    /**
     * @serialField
     */
    public DLinkedList() { // constructs an
    }
    /**
     * Inserts a specified element at the specified position in the list.
     *
     * @param index dv
     * @param element dv
     * @throws IndexOutOfBoundsException d
     */
    public void add(final int index, final  Object element) {

        Node newNode = new Node(element);
        Node temp = first;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0 && size() == 0) {
            first = newNode;
            last = newNode;
            counttt++;
        } else if (index == 0) {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
            counttt++;
        } else if (index == counttt) {
            add(element);
        } else {
            for (int i = 1; i < index; i++) {

                temp = temp.next;
                if (temp == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            newNode.next = temp.next;
            newNode.previous = temp;
            temp.next.previous = newNode;
            temp.next = newNode;
            counttt++;
        }
    }

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param element d
     */
    public void add(final Object element) { // working
        Node newNode = new Node(element);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
        counttt++;
    }

    /**
     * @param index sc
     * @return the element at the specified position in this list.
     */
    public Object get(final int index) { // working

        if (first == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index > (size() - 1) || index < 0) {
        	
            throw new IndexOutOfBoundsException();
        }

        Node temp;
        temp = first;
        if (index == size() - 1) {
            temp = last;
        } else {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }

        return temp.data;

    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index s
     * @param element cs
     */
    public void set(final int index, final  Object element) { // working

        Node temp;
        temp = first;

        if (first == null) {
            throw new NullPointerException();
        }

        if (index > (size() - 1)) {
            throw new NullPointerException();
        }

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = element;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {

        first = null;
        last = null;
        counttt = 0;
    }

    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty() {

        return (first == null);

    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index cs
     */
    public void remove(final int index) { // working

        Node temp;
        temp = first;

        if (index > (size() - 1) || (index == 0 && size() == 0) || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0 && size() == 1) {
            first = first.next;
            last = null;
        } else if (index == 0) {
            first = first.next;
            first.previous = null;
        } else if (index < size() - 1) {
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            if (index != size() - 2) {
                temp.next = temp.next.next;
                temp.next.next.previous = temp;
            } else {
                temp.next = temp.next.next;

            }
        } else if (index == size() - 1) {
            temp = last.previous;
            last = temp;
            temp.next.previous = null;
            temp.next = null;
        }
        counttt--;
    }

    /**
     * @return the number of elements in this list.
     */
    public int size() { // working

        if (isEmpty()) {
            return 0;
        } else {
            return counttt;
        }
    }

    /**
     * @param fromIndex c
     * @param toIndex sc
     * @return a view of the portion of this list between the specified
     *         fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(final int fromIndex, final int toIndex) {

        DLinkedList subList = new DLinkedList();
        for (int i = fromIndex; i <= toIndex; i++) {

            subList.add(get(i));

        }
        return subList;

    }

    /**
     * @param o sc
     * @return true if this list contains an element with the same value as the
     *         specified element.
     */
    public boolean contains(final Object o) {
        if (first == null) {
            return false;
        }
        Node temp = first;
        while (!o.equals(temp.data) && temp.next != null) {
            temp = temp.next;
        }
        return o.equals(temp.data);
    }
}
