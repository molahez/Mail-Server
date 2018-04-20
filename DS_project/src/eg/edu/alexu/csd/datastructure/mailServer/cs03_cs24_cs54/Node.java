package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

/**
 * @author ahmed molahez
 *
 */
public class Node {
    /**
     * @serialField
     *
     */
    public Object data; // holds data item
    /**
     * @serialField
     *
     */

    public Node next; // ref for the next Node
    /**
     * @serialField
     *
     */
    public Node previous; // ref for the previous nodev
    /**
     * @serialField
     */
    int key = 0;

    /**
     * @param element
     *            g
     *
     */
    /**
     *
     * @throws IOException
     */
    public Node() {
        data = 0;
        key = 0;

    }
    /**
    *
    * @param element k
    * @param k few
    *
    */
    public Node(final Object element, final int k) { // constructor
        data = element;
        key = k;
    }

}
