package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;
/**
 * @author ahmed molahez
 *
 */
public class Node {
    /**
     * @serialField
     *
     */
	public Object data; //holds data item
	 /**
     * @serialField
     *
     */
	public Node next; //ref for the next Node
	 /**
     * @serialField
     *
     */
	public Node previous; //ref for the previous nodev
	 /**
     * @param element g
     *
     */
	public Node(final Object element) { //constructor
		data = element;
	}

}
