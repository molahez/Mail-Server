package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
/**
 * @author ahmed molahez
 *
 */
public class SLinkedList implements ILinkedList {
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
    public SLinkedList() { // constructs an Empty list
    }

    /**
     * Inserts a specified element at the specified position in the list.
     *
     * @param index f
     * @param element sf
     * @throws IndexOutOfBoundsException c
     */
    public void add(final int index, final  Object element) { // working
        Node newNode = new Node(element);
        Node temp = first;

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0 && size() == 0) {
            first = newNode;
            last = newNode;

        } else if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else if (index == size()) {
            add(element);
        } else {
            for (int i = 1; i < index; i++) {

                temp = temp.next;
                if (temp == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }

    }

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param element dv
     */
    public void add(final Object element) { // working
        Node newNode = new Node(element);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
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

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.data;

    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index df
     * @param element f
     */
    public void set(final int index, final Object element) { // working

        Node temp;
        temp = first;

        if (first == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index > (size() - 1)) {
            throw new IndexOutOfBoundsException();
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
     * @param index s
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
        } else if (index < size() - 1) {
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;

        } else if (index == size() - 1) {
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            last = temp;
            temp.next = null;
        }
    }

    /**
     * @return the number of elements in this list.
     */
    public int size() { // working

        int count = 1;
        Node temp;
        temp = first;

        if (isEmpty()) {
            return 0;
        } else {
            while (temp.next != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }
    }

    /**
     * @param fromIndex c
     * @param toIndex c
     * @return a view of the portion of this list between the specified
     *         fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(final int fromIndex, final int toIndex) {

        SLinkedList subList = new SLinkedList();
        for (int i = fromIndex; i <= toIndex; i++) {

            subList.add(get(i));

        }
        return subList;

    }

    /**
     * @param o ss
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
